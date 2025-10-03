package org.project_kessel.api.rbac.v2;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.project_kessel.api.inventory.v1beta2.KesselInventoryServiceGrpc.KesselInventoryServiceBlockingStub;
import org.project_kessel.api.inventory.v1beta2.ResponsePagination;
import org.project_kessel.api.inventory.v1beta2.StreamedListObjectsRequest;
import org.project_kessel.api.inventory.v1beta2.StreamedListObjectsResponse;
import org.project_kessel.api.inventory.v1beta2.SubjectReference;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ListWorkspacesTest {

    @Mock
    private KesselInventoryServiceBlockingStub mockInventoryClient;

    @Captor
    private ArgumentCaptor<StreamedListObjectsRequest> requestCaptor;

    private final SubjectReference testSubject = SubjectReference.newBuilder().build();

    @Test
    void testBuildsRequestWithCorrectParameters() {
        Iterator<StreamedListObjectsResponse> pageIterator = List.of(responseWithToken("")).iterator();
        when(mockInventoryClient.streamedListObjects(any(StreamedListObjectsRequest.class)))
                .thenReturn(pageIterator)
                .thenReturn(Collections.emptyIterator());

        ListWorkspaces.listWorkspaces(mockInventoryClient, testSubject, "member").forEach(response -> {
        });

        verify(mockInventoryClient, times(2)).streamedListObjects(requestCaptor.capture());
        StreamedListObjectsRequest capturedRequest = requestCaptor.getAllValues().get(0);

        assertEquals("member", capturedRequest.getRelation());
        assertEquals(Utils.workspaceType(), capturedRequest.getObjectType());
        assertEquals(testSubject, capturedRequest.getSubject());
        assertFalse(capturedRequest.getPagination().hasContinuationToken());
    }

    @Test
    void testHandlesPagination() {
        Iterator<StreamedListObjectsResponse> firstPage = List.of(responseWithToken("next-page-token")).iterator();
        Iterator<StreamedListObjectsResponse> secondPage = List.of(responseWithToken("")).iterator();
        when(mockInventoryClient.streamedListObjects(any(StreamedListObjectsRequest.class)))
                .thenReturn(firstPage)
                .thenReturn(secondPage)
                .thenReturn(Collections.emptyIterator());

        ListWorkspaces.listWorkspaces(mockInventoryClient, testSubject, "viewer").forEach(response -> {
        });

        verify(mockInventoryClient, times(3)).streamedListObjects(requestCaptor.capture());
        List<StreamedListObjectsRequest> capturedRequests = requestCaptor.getAllValues();

        assertEquals(3, capturedRequests.size());
        // First request has no token
        assertFalse(capturedRequests.get(0).getPagination().hasContinuationToken());
        // Second request uses the token from the first response
        assertEquals("next-page-token", capturedRequests.get(1).getPagination().getContinuationToken());
    }

    @Test
    void testStopsWhenNoToken() {
        Iterator<StreamedListObjectsResponse> pageIterator = List.of(responseWithToken("")).iterator();
        when(mockInventoryClient.streamedListObjects(any(StreamedListObjectsRequest.class)))
                .thenReturn(pageIterator)
                .thenReturn(Collections.emptyIterator());

        ListWorkspaces.listWorkspaces(mockInventoryClient, testSubject, "admin").forEach(response -> {
        });

        verify(mockInventoryClient, times(2)).streamedListObjects(any(StreamedListObjectsRequest.class));
    }

    @Test
    void testHandlesGrpcError() {
        when(mockInventoryClient.streamedListObjects(any(StreamedListObjectsRequest.class)))
                .thenThrow(new RuntimeException("gRPC stream failed"));

        Iterable<StreamedListObjectsResponse> workspaces = ListWorkspaces.listWorkspaces(mockInventoryClient,
                testSubject, "member");
        Exception e = assertThrows(RuntimeException.class, () -> {
            workspaces.forEach(response -> {
            });
        });

        assertTrue(e.getMessage().contains("Error fetching workspaces"));
        verify(mockInventoryClient, times(1)).streamedListObjects(any(StreamedListObjectsRequest.class));
    }

    @Test
    void testUsesInitialToken() {
        String initialToken = "resume-from-here";
        Iterator<StreamedListObjectsResponse> pageIterator = List.of(responseWithToken("")).iterator();
        when(mockInventoryClient.streamedListObjects(any(StreamedListObjectsRequest.class)))
                .thenReturn(pageIterator)
                .thenReturn(Collections.emptyIterator());

        ListWorkspaces.listWorkspaces(mockInventoryClient, testSubject, "member", initialToken).forEach(response -> {
        });

        verify(mockInventoryClient, times(2)).streamedListObjects(requestCaptor.capture());
        StreamedListObjectsRequest capturedRequest = requestCaptor.getAllValues().get(0);

        assertEquals(initialToken, capturedRequest.getPagination().getContinuationToken());
    }

    @Test
    void testReturnsActualWorkspaceData() {
        Iterator<StreamedListObjectsResponse> responses = List.of(
                workspaceResponse("workspace-1", null),
                workspaceResponse("workspace-2", null),
                workspaceResponse("workspace-3", null)).iterator();

        when(mockInventoryClient.streamedListObjects(any(StreamedListObjectsRequest.class)))
                .thenReturn(responses)
                .thenReturn(Collections.emptyIterator());

        List<String> workspaceIds = new ArrayList<>();
        for (StreamedListObjectsResponse response : ListWorkspaces.listWorkspaces(mockInventoryClient, testSubject,
                "member")) {
            assertNotNull(response.getObject());
            assertEquals("workspace", response.getObject().getResourceType());
            assertEquals("rbac", response.getObject().getReporter().getType());
            workspaceIds.add(response.getObject().getResourceId());
        }

        assertEquals(3, workspaceIds.size());
        assertEquals("workspace-1", workspaceIds.get(0));
        assertEquals("workspace-2", workspaceIds.get(1));
        assertEquals("workspace-3", workspaceIds.get(2));
    }

    @Test
    void testReturnsWorkspacesAcrossMultiplePages() {
        Iterator<StreamedListObjectsResponse> firstPage = List.of(
                workspaceResponse("prod-workspace-1", "page2-token"),
                workspaceResponse("prod-workspace-2", "page2-token")).iterator();

        Iterator<StreamedListObjectsResponse> secondPage = List.of(
                workspaceResponse("dev-workspace-1", "page3-token"),
                workspaceResponse("dev-workspace-2", null)).iterator();

        when(mockInventoryClient.streamedListObjects(any(StreamedListObjectsRequest.class)))
                .thenReturn(firstPage)
                .thenReturn(secondPage)
                .thenReturn(Collections.emptyIterator());

        List<StreamedListObjectsResponse> allResponses = new ArrayList<>();
        for (StreamedListObjectsResponse response : ListWorkspaces.listWorkspaces(mockInventoryClient, testSubject,
                "viewer")) {
            allResponses.add(response);
        }

        assertEquals(4, allResponses.size());
        assertEquals("prod-workspace-1", allResponses.get(0).getObject().getResourceId());
        assertEquals("prod-workspace-2", allResponses.get(1).getObject().getResourceId());
        assertEquals("dev-workspace-1", allResponses.get(2).getObject().getResourceId());
        assertEquals("dev-workspace-2", allResponses.get(3).getObject().getResourceId());

        verify(mockInventoryClient, times(3)).streamedListObjects(requestCaptor.capture());
        List<StreamedListObjectsRequest> requests = requestCaptor.getAllValues();

        assertFalse(requests.get(0).getPagination().hasContinuationToken());
        assertEquals("page2-token", requests.get(1).getPagination().getContinuationToken());
        assertEquals("page3-token", requests.get(2).getPagination().getContinuationToken());
    }

    @Test
    void testCanIterateWorkspacesMultipleTimes() {
        Iterator<StreamedListObjectsResponse> responses1 = List.of(
                workspaceResponse("workspace-alpha", null),
                workspaceResponse("workspace-beta", null)).iterator();

        Iterator<StreamedListObjectsResponse> responses2 = List.of(
                workspaceResponse("workspace-alpha", null),
                workspaceResponse("workspace-beta", null)).iterator();

        when(mockInventoryClient.streamedListObjects(any(StreamedListObjectsRequest.class)))
                .thenReturn(responses1)
                .thenReturn(Collections.emptyIterator())
                .thenReturn(responses2)
                .thenReturn(Collections.emptyIterator());

        Iterable<StreamedListObjectsResponse> workspaces = ListWorkspaces.listWorkspaces(mockInventoryClient,
                testSubject, "admin");

        List<String> firstIteration = new ArrayList<>();
        for (StreamedListObjectsResponse response : workspaces) {
            firstIteration.add(response.getObject().getResourceId());
        }

        List<String> secondIteration = new ArrayList<>();
        for (StreamedListObjectsResponse response : workspaces) {
            secondIteration.add(response.getObject().getResourceId());
        }

        assertEquals(2, firstIteration.size());
        assertEquals(2, secondIteration.size());
        assertEquals(firstIteration, secondIteration);
        assertEquals("workspace-alpha", firstIteration.get(0));
        assertEquals("workspace-beta", firstIteration.get(1));

        verify(mockInventoryClient, times(4)).streamedListObjects(any(StreamedListObjectsRequest.class));
    }

    @Test
    void testEmptyWorkspaceList() {
        when(mockInventoryClient.streamedListObjects(any(StreamedListObjectsRequest.class)))
                .thenReturn(Collections.emptyIterator());

        List<StreamedListObjectsResponse> workspaces = new ArrayList<>();
        for (StreamedListObjectsResponse response : ListWorkspaces.listWorkspaces(mockInventoryClient, testSubject,
                "member")) {
            workspaces.add(response);
        }

        assertEquals(0, workspaces.size());
        verify(mockInventoryClient, times(1)).streamedListObjects(any(StreamedListObjectsRequest.class));
    }

    private static StreamedListObjectsResponse responseWithToken(String token) {
        return StreamedListObjectsResponse.newBuilder()
                .setPagination(ResponsePagination.newBuilder().setContinuationToken(token).build())
                .build();
    }

    private static StreamedListObjectsResponse workspaceResponse(String workspaceId, String token) {
        StreamedListObjectsResponse.Builder responseBuilder = StreamedListObjectsResponse.newBuilder()
                .setObject(Utils.workspaceResource(workspaceId));

        if (token != null) {
            responseBuilder.setPagination(
                    ResponsePagination.newBuilder()
                            .setContinuationToken(token)
                            .build());
        }

        return responseBuilder.build();
    }
}
