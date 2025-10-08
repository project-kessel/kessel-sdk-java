package org.project_kessel.api.rbac.v2;

import org.project_kessel.api.inventory.v1beta2.KesselInventoryServiceGrpc.KesselInventoryServiceBlockingStub;
import org.project_kessel.api.inventory.v1beta2.RequestPagination;
import org.project_kessel.api.inventory.v1beta2.StreamedListObjectsRequest;
import org.project_kessel.api.inventory.v1beta2.StreamedListObjectsResponse;
import org.project_kessel.api.inventory.v1beta2.SubjectReference;

import java.util.Collections;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class ListWorkspaces {

    private static final int DEFAULT_PAGE_LIMIT = 1000;

    public static Iterable<StreamedListObjectsResponse> listWorkspaces(
            KesselInventoryServiceBlockingStub inventory,
            SubjectReference subject,
            String relation) {
        return listWorkspaces(inventory, subject, relation, null);
    }

    public static Iterable<StreamedListObjectsResponse> listWorkspaces(
            KesselInventoryServiceBlockingStub inventory,
            SubjectReference subject,
            String relation,
            String continuationToken) {
        return () -> new WorkspaceListIterator(inventory, subject, relation, continuationToken);
    }

    private static class WorkspaceListIterator implements Iterator<StreamedListObjectsResponse> {
        private KesselInventoryServiceBlockingStub inventory;
        private SubjectReference subject;
        private String relation;

        private String continuationToken;
        private Iterator<StreamedListObjectsResponse> currentPageIterator;
        private StreamedListObjectsResponse nextResponse;

        public WorkspaceListIterator(
                KesselInventoryServiceBlockingStub inventory,
                SubjectReference subject,
                String relation,
                String initialToken) {
            this.inventory = inventory;
            this.subject = subject;
            this.relation = relation;
            this.continuationToken = initialToken;
            this.currentPageIterator = Collections.emptyIterator();
        }

        @Override
        public boolean hasNext() {
            if (nextResponse != null) {
                return true;
            }
            return tryToFetchNext();
        }

        @Override
        public StreamedListObjectsResponse next() {
            if (nextResponse == null && !tryToFetchNext()) {
                throw new NoSuchElementException("No more workspace responses available");
            }
            StreamedListObjectsResponse response = nextResponse;
            nextResponse = null;
            return response;
        }

        private boolean tryToFetchNext() {
            while (true) {
                if (currentPageIterator.hasNext()) {
                    nextResponse = currentPageIterator.next();
                    if (nextResponse.hasPagination()) {
                        continuationToken = nextResponse.getPagination().getContinuationToken();
                    }
                    return true;
                }

                // in case of an empty token, we're done here
                if (continuationToken != null && continuationToken.isEmpty()) {
                    return false;
                }

                try {
                    StreamedListObjectsRequest request = buildStreamedListObjectsRequest(subject, relation, continuationToken);
                    currentPageIterator = inventory.streamedListObjects(request);
                    if (!currentPageIterator.hasNext()) {
                        return false;
                    }
                } catch (Exception e) {
                    throw new RuntimeException("Error fetching workspaces: " + e.getMessage(), e);
                }
            }
        }
    }

    private static StreamedListObjectsRequest buildStreamedListObjectsRequest(
            SubjectReference subject, String relation, String continuationToken) {

        StreamedListObjectsRequest.Builder requestBuilder = StreamedListObjectsRequest.newBuilder()
                .setObjectType(Utils.workspaceType())
                .setRelation(relation)
                .setSubject(subject);

        RequestPagination.Builder paginationBuilder = RequestPagination.newBuilder()
                .setLimit(DEFAULT_PAGE_LIMIT);

        if (continuationToken != null && !continuationToken.isEmpty()) {
            paginationBuilder.setContinuationToken(continuationToken);
        }

        requestBuilder.setPagination(paginationBuilder.build());
        return requestBuilder.build();
    }
}