package org.project_kessel.api.inventory.v1beta2;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 * <pre>
 * KesselInventoryService provides APIs to perform relationship checks
 * and manage inventory resource lifecycles (reporting and deletion).
 * </pre>
 */
@io.grpc.stub.annotations.GrpcGenerated
public final class KesselInventoryServiceGrpc {

  private KesselInventoryServiceGrpc() {}

  public static final java.lang.String SERVICE_NAME = "kessel.inventory.v1beta2.KesselInventoryService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<org.project_kessel.api.inventory.v1beta2.CheckRequest,
      org.project_kessel.api.inventory.v1beta2.CheckResponse> getCheckMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Check",
      requestType = org.project_kessel.api.inventory.v1beta2.CheckRequest.class,
      responseType = org.project_kessel.api.inventory.v1beta2.CheckResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<org.project_kessel.api.inventory.v1beta2.CheckRequest,
      org.project_kessel.api.inventory.v1beta2.CheckResponse> getCheckMethod() {
    io.grpc.MethodDescriptor<org.project_kessel.api.inventory.v1beta2.CheckRequest, org.project_kessel.api.inventory.v1beta2.CheckResponse> getCheckMethod;
    if ((getCheckMethod = KesselInventoryServiceGrpc.getCheckMethod) == null) {
      synchronized (KesselInventoryServiceGrpc.class) {
        if ((getCheckMethod = KesselInventoryServiceGrpc.getCheckMethod) == null) {
          KesselInventoryServiceGrpc.getCheckMethod = getCheckMethod =
              io.grpc.MethodDescriptor.<org.project_kessel.api.inventory.v1beta2.CheckRequest, org.project_kessel.api.inventory.v1beta2.CheckResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Check"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  org.project_kessel.api.inventory.v1beta2.CheckRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  org.project_kessel.api.inventory.v1beta2.CheckResponse.getDefaultInstance()))
              .setSchemaDescriptor(new KesselInventoryServiceMethodDescriptorSupplier("Check"))
              .build();
        }
      }
    }
    return getCheckMethod;
  }

  private static volatile io.grpc.MethodDescriptor<org.project_kessel.api.inventory.v1beta2.CheckForUpdateRequest,
      org.project_kessel.api.inventory.v1beta2.CheckForUpdateResponse> getCheckForUpdateMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "CheckForUpdate",
      requestType = org.project_kessel.api.inventory.v1beta2.CheckForUpdateRequest.class,
      responseType = org.project_kessel.api.inventory.v1beta2.CheckForUpdateResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<org.project_kessel.api.inventory.v1beta2.CheckForUpdateRequest,
      org.project_kessel.api.inventory.v1beta2.CheckForUpdateResponse> getCheckForUpdateMethod() {
    io.grpc.MethodDescriptor<org.project_kessel.api.inventory.v1beta2.CheckForUpdateRequest, org.project_kessel.api.inventory.v1beta2.CheckForUpdateResponse> getCheckForUpdateMethod;
    if ((getCheckForUpdateMethod = KesselInventoryServiceGrpc.getCheckForUpdateMethod) == null) {
      synchronized (KesselInventoryServiceGrpc.class) {
        if ((getCheckForUpdateMethod = KesselInventoryServiceGrpc.getCheckForUpdateMethod) == null) {
          KesselInventoryServiceGrpc.getCheckForUpdateMethod = getCheckForUpdateMethod =
              io.grpc.MethodDescriptor.<org.project_kessel.api.inventory.v1beta2.CheckForUpdateRequest, org.project_kessel.api.inventory.v1beta2.CheckForUpdateResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "CheckForUpdate"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  org.project_kessel.api.inventory.v1beta2.CheckForUpdateRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  org.project_kessel.api.inventory.v1beta2.CheckForUpdateResponse.getDefaultInstance()))
              .setSchemaDescriptor(new KesselInventoryServiceMethodDescriptorSupplier("CheckForUpdate"))
              .build();
        }
      }
    }
    return getCheckForUpdateMethod;
  }

  private static volatile io.grpc.MethodDescriptor<org.project_kessel.api.inventory.v1beta2.CheckBulkRequest,
      org.project_kessel.api.inventory.v1beta2.CheckBulkResponse> getCheckBulkMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "CheckBulk",
      requestType = org.project_kessel.api.inventory.v1beta2.CheckBulkRequest.class,
      responseType = org.project_kessel.api.inventory.v1beta2.CheckBulkResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<org.project_kessel.api.inventory.v1beta2.CheckBulkRequest,
      org.project_kessel.api.inventory.v1beta2.CheckBulkResponse> getCheckBulkMethod() {
    io.grpc.MethodDescriptor<org.project_kessel.api.inventory.v1beta2.CheckBulkRequest, org.project_kessel.api.inventory.v1beta2.CheckBulkResponse> getCheckBulkMethod;
    if ((getCheckBulkMethod = KesselInventoryServiceGrpc.getCheckBulkMethod) == null) {
      synchronized (KesselInventoryServiceGrpc.class) {
        if ((getCheckBulkMethod = KesselInventoryServiceGrpc.getCheckBulkMethod) == null) {
          KesselInventoryServiceGrpc.getCheckBulkMethod = getCheckBulkMethod =
              io.grpc.MethodDescriptor.<org.project_kessel.api.inventory.v1beta2.CheckBulkRequest, org.project_kessel.api.inventory.v1beta2.CheckBulkResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "CheckBulk"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  org.project_kessel.api.inventory.v1beta2.CheckBulkRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  org.project_kessel.api.inventory.v1beta2.CheckBulkResponse.getDefaultInstance()))
              .setSchemaDescriptor(new KesselInventoryServiceMethodDescriptorSupplier("CheckBulk"))
              .build();
        }
      }
    }
    return getCheckBulkMethod;
  }

  private static volatile io.grpc.MethodDescriptor<org.project_kessel.api.inventory.v1beta2.ReportResourceRequest,
      org.project_kessel.api.inventory.v1beta2.ReportResourceResponse> getReportResourceMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "ReportResource",
      requestType = org.project_kessel.api.inventory.v1beta2.ReportResourceRequest.class,
      responseType = org.project_kessel.api.inventory.v1beta2.ReportResourceResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<org.project_kessel.api.inventory.v1beta2.ReportResourceRequest,
      org.project_kessel.api.inventory.v1beta2.ReportResourceResponse> getReportResourceMethod() {
    io.grpc.MethodDescriptor<org.project_kessel.api.inventory.v1beta2.ReportResourceRequest, org.project_kessel.api.inventory.v1beta2.ReportResourceResponse> getReportResourceMethod;
    if ((getReportResourceMethod = KesselInventoryServiceGrpc.getReportResourceMethod) == null) {
      synchronized (KesselInventoryServiceGrpc.class) {
        if ((getReportResourceMethod = KesselInventoryServiceGrpc.getReportResourceMethod) == null) {
          KesselInventoryServiceGrpc.getReportResourceMethod = getReportResourceMethod =
              io.grpc.MethodDescriptor.<org.project_kessel.api.inventory.v1beta2.ReportResourceRequest, org.project_kessel.api.inventory.v1beta2.ReportResourceResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "ReportResource"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  org.project_kessel.api.inventory.v1beta2.ReportResourceRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  org.project_kessel.api.inventory.v1beta2.ReportResourceResponse.getDefaultInstance()))
              .setSchemaDescriptor(new KesselInventoryServiceMethodDescriptorSupplier("ReportResource"))
              .build();
        }
      }
    }
    return getReportResourceMethod;
  }

  private static volatile io.grpc.MethodDescriptor<org.project_kessel.api.inventory.v1beta2.DeleteResourceRequest,
      org.project_kessel.api.inventory.v1beta2.DeleteResourceResponse> getDeleteResourceMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "DeleteResource",
      requestType = org.project_kessel.api.inventory.v1beta2.DeleteResourceRequest.class,
      responseType = org.project_kessel.api.inventory.v1beta2.DeleteResourceResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<org.project_kessel.api.inventory.v1beta2.DeleteResourceRequest,
      org.project_kessel.api.inventory.v1beta2.DeleteResourceResponse> getDeleteResourceMethod() {
    io.grpc.MethodDescriptor<org.project_kessel.api.inventory.v1beta2.DeleteResourceRequest, org.project_kessel.api.inventory.v1beta2.DeleteResourceResponse> getDeleteResourceMethod;
    if ((getDeleteResourceMethod = KesselInventoryServiceGrpc.getDeleteResourceMethod) == null) {
      synchronized (KesselInventoryServiceGrpc.class) {
        if ((getDeleteResourceMethod = KesselInventoryServiceGrpc.getDeleteResourceMethod) == null) {
          KesselInventoryServiceGrpc.getDeleteResourceMethod = getDeleteResourceMethod =
              io.grpc.MethodDescriptor.<org.project_kessel.api.inventory.v1beta2.DeleteResourceRequest, org.project_kessel.api.inventory.v1beta2.DeleteResourceResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "DeleteResource"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  org.project_kessel.api.inventory.v1beta2.DeleteResourceRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  org.project_kessel.api.inventory.v1beta2.DeleteResourceResponse.getDefaultInstance()))
              .setSchemaDescriptor(new KesselInventoryServiceMethodDescriptorSupplier("DeleteResource"))
              .build();
        }
      }
    }
    return getDeleteResourceMethod;
  }

  private static volatile io.grpc.MethodDescriptor<org.project_kessel.api.inventory.v1beta2.StreamedListObjectsRequest,
      org.project_kessel.api.inventory.v1beta2.StreamedListObjectsResponse> getStreamedListObjectsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "StreamedListObjects",
      requestType = org.project_kessel.api.inventory.v1beta2.StreamedListObjectsRequest.class,
      responseType = org.project_kessel.api.inventory.v1beta2.StreamedListObjectsResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
  public static io.grpc.MethodDescriptor<org.project_kessel.api.inventory.v1beta2.StreamedListObjectsRequest,
      org.project_kessel.api.inventory.v1beta2.StreamedListObjectsResponse> getStreamedListObjectsMethod() {
    io.grpc.MethodDescriptor<org.project_kessel.api.inventory.v1beta2.StreamedListObjectsRequest, org.project_kessel.api.inventory.v1beta2.StreamedListObjectsResponse> getStreamedListObjectsMethod;
    if ((getStreamedListObjectsMethod = KesselInventoryServiceGrpc.getStreamedListObjectsMethod) == null) {
      synchronized (KesselInventoryServiceGrpc.class) {
        if ((getStreamedListObjectsMethod = KesselInventoryServiceGrpc.getStreamedListObjectsMethod) == null) {
          KesselInventoryServiceGrpc.getStreamedListObjectsMethod = getStreamedListObjectsMethod =
              io.grpc.MethodDescriptor.<org.project_kessel.api.inventory.v1beta2.StreamedListObjectsRequest, org.project_kessel.api.inventory.v1beta2.StreamedListObjectsResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "StreamedListObjects"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  org.project_kessel.api.inventory.v1beta2.StreamedListObjectsRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  org.project_kessel.api.inventory.v1beta2.StreamedListObjectsResponse.getDefaultInstance()))
              .setSchemaDescriptor(new KesselInventoryServiceMethodDescriptorSupplier("StreamedListObjects"))
              .build();
        }
      }
    }
    return getStreamedListObjectsMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static KesselInventoryServiceStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<KesselInventoryServiceStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<KesselInventoryServiceStub>() {
        @java.lang.Override
        public KesselInventoryServiceStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new KesselInventoryServiceStub(channel, callOptions);
        }
      };
    return KesselInventoryServiceStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports all types of calls on the service
   */
  public static KesselInventoryServiceBlockingV2Stub newBlockingV2Stub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<KesselInventoryServiceBlockingV2Stub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<KesselInventoryServiceBlockingV2Stub>() {
        @java.lang.Override
        public KesselInventoryServiceBlockingV2Stub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new KesselInventoryServiceBlockingV2Stub(channel, callOptions);
        }
      };
    return KesselInventoryServiceBlockingV2Stub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static KesselInventoryServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<KesselInventoryServiceBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<KesselInventoryServiceBlockingStub>() {
        @java.lang.Override
        public KesselInventoryServiceBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new KesselInventoryServiceBlockingStub(channel, callOptions);
        }
      };
    return KesselInventoryServiceBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static KesselInventoryServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<KesselInventoryServiceFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<KesselInventoryServiceFutureStub>() {
        @java.lang.Override
        public KesselInventoryServiceFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new KesselInventoryServiceFutureStub(channel, callOptions);
        }
      };
    return KesselInventoryServiceFutureStub.newStub(factory, channel);
  }

  /**
   * <pre>
   * KesselInventoryService provides APIs to perform relationship checks
   * and manage inventory resource lifecycles (reporting and deletion).
   * </pre>
   */
  public interface AsyncService {

    /**
     * <pre>
     * Performs an relationship check to determine whether a subject has a specific
     * permission or relationship on a resource.
     * This API evaluates whether the provided subject is a member of the specified relation
     * (e.g., "viewer", "editor", "admin") on the target object. It answers the question:
     * "Does subject *X* have relation *Y* on object *Z*?"
     * Common use cases include enforcing read access, conditional UI visibility,
     * or authorization gating for downstream API calls.
     * </pre>
     */
    default void check(org.project_kessel.api.inventory.v1beta2.CheckRequest request,
        io.grpc.stub.StreamObserver<org.project_kessel.api.inventory.v1beta2.CheckResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getCheckMethod(), responseObserver);
    }

    /**
     * <pre>
     * Performs a strongly consistent relationship check to determine whether a subject
     * has a specific relation to an object (representing, for example, a permission).
     * This API answers the question:
     * "Is subject *X* currently authorized to update or modify resource *Y*?"
     * Unlike the basic `Check` endpoint, this method guarantees a fully up-to-date
     * view of the relationship state (e.g., not relying on cached or eventually consistent data).
     * It is intended to be used just prior to sensitive operation (e.g., update, delete)
     * which depend on the current state of the relationship.
     * </pre>
     */
    default void checkForUpdate(org.project_kessel.api.inventory.v1beta2.CheckForUpdateRequest request,
        io.grpc.stub.StreamObserver<org.project_kessel.api.inventory.v1beta2.CheckForUpdateResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getCheckForUpdateMethod(), responseObserver);
    }

    /**
     * <pre>
     * Performs bulk permission checks for multiple resource-subject-relation combinations.
     * This API is more efficient than making individual Check calls when verifying permissions
     * for multiple items. It answers questions like:
     * "Which of these resources can subject *X* perform action *Y* on?"
     * Common use cases include:
     * - Filtering lists based on user permissions
     * - Batch authorization checks before performing bulk operations
     * - Dashboard rendering with multiple permission checks
     * - Pre-authorization for UI components
     * The response includes a result for each item in the request, maintaining the same order.
     * </pre>
     */
    default void checkBulk(org.project_kessel.api.inventory.v1beta2.CheckBulkRequest request,
        io.grpc.stub.StreamObserver<org.project_kessel.api.inventory.v1beta2.CheckBulkResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getCheckBulkMethod(), responseObserver);
    }

    /**
     * <pre>
     * Reports to Kessel Inventory that a Resource has been created or has been updated.
     * Reporters can use this API to report facts about their resources in order to
     * facilitate integration, correlation, and access control.
     * Each call can include:
     * - Reporter-specific attributes and relationships (`representations.reporter`)
     * - Shared attributes and relationships common to all reporters (`representations.common`)
     * - Identifiers and metadata that allow correlation to an existing resource
     * Multiple reporters may report representations for the same resource.
     * Kessel Inventory correlates these
     * based on correlation keys provided for a given resource type
     * All versions of your reported facts will be retained and can be queried as needed
     * The relationships reported through this API are used to determine relationship check outcomes
     * via the Check and CheckForUpdate APIs.
     * Reporters are responsible for ensuring delivery guarantees and message ordering
     * appropriate to the sensitivity and consistency needs of their use case.
     * This API does **not** guarantee immediate read-your-writes consistency by default.
     *  If a reporter requires newly submitted resources or relationships to be visible
     * in subsequent checks (e.g., `Check`), the request must explicitly set
     * `write_visibility = IMMEDIATE`.
     * </pre>
     */
    default void reportResource(org.project_kessel.api.inventory.v1beta2.ReportResourceRequest request,
        io.grpc.stub.StreamObserver<org.project_kessel.api.inventory.v1beta2.ReportResourceResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getReportResourceMethod(), responseObserver);
    }

    /**
     * <pre>
     * Reports to Kessel Inventory that a Reporter's representation of a Resource has been deleted.
     * This operation is typically used when a resource has been decommissioned or
     * is no longer reported by any authorized system.
     * As a result, relationship checks performed via the `Check` and
     * `CheckForUpdate` APIs will no longer resolve positively for the deleted
     * resource. Any decisions that depend on relationships tied to
     * this resource will be affected.
     * As an example, it can revoke previously granted access across the system.
     * </pre>
     */
    default void deleteResource(org.project_kessel.api.inventory.v1beta2.DeleteResourceRequest request,
        io.grpc.stub.StreamObserver<org.project_kessel.api.inventory.v1beta2.DeleteResourceResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getDeleteResourceMethod(), responseObserver);
    }

    /**
     * <pre>
     * Streams a list of objects where the given subject has the specified relation.
     * This relationship query answers the question:
     * "Which objects of type *X* does subject *Y* have the *Z* relation to?"
     * It is often used to power user-facing dashboards, filtered UIs, or policy-driven
     * access lists. The result is streamed incrementally to support large datasets and
     * reduce client-side latency or memory pressure.
     * Pagination and consistency controls allow fine-tuned performance and data freshness.
     * </pre>
     */
    default void streamedListObjects(org.project_kessel.api.inventory.v1beta2.StreamedListObjectsRequest request,
        io.grpc.stub.StreamObserver<org.project_kessel.api.inventory.v1beta2.StreamedListObjectsResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getStreamedListObjectsMethod(), responseObserver);
    }
  }

  /**
   * Base class for the server implementation of the service KesselInventoryService.
   * <pre>
   * KesselInventoryService provides APIs to perform relationship checks
   * and manage inventory resource lifecycles (reporting and deletion).
   * </pre>
   */
  public static abstract class KesselInventoryServiceImplBase
      implements io.grpc.BindableService, AsyncService {

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return KesselInventoryServiceGrpc.bindService(this);
    }
  }

  /**
   * A stub to allow clients to do asynchronous rpc calls to service KesselInventoryService.
   * <pre>
   * KesselInventoryService provides APIs to perform relationship checks
   * and manage inventory resource lifecycles (reporting and deletion).
   * </pre>
   */
  public static final class KesselInventoryServiceStub
      extends io.grpc.stub.AbstractAsyncStub<KesselInventoryServiceStub> {
    private KesselInventoryServiceStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected KesselInventoryServiceStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new KesselInventoryServiceStub(channel, callOptions);
    }

    /**
     * <pre>
     * Performs an relationship check to determine whether a subject has a specific
     * permission or relationship on a resource.
     * This API evaluates whether the provided subject is a member of the specified relation
     * (e.g., "viewer", "editor", "admin") on the target object. It answers the question:
     * "Does subject *X* have relation *Y* on object *Z*?"
     * Common use cases include enforcing read access, conditional UI visibility,
     * or authorization gating for downstream API calls.
     * </pre>
     */
    public void check(org.project_kessel.api.inventory.v1beta2.CheckRequest request,
        io.grpc.stub.StreamObserver<org.project_kessel.api.inventory.v1beta2.CheckResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getCheckMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Performs a strongly consistent relationship check to determine whether a subject
     * has a specific relation to an object (representing, for example, a permission).
     * This API answers the question:
     * "Is subject *X* currently authorized to update or modify resource *Y*?"
     * Unlike the basic `Check` endpoint, this method guarantees a fully up-to-date
     * view of the relationship state (e.g., not relying on cached or eventually consistent data).
     * It is intended to be used just prior to sensitive operation (e.g., update, delete)
     * which depend on the current state of the relationship.
     * </pre>
     */
    public void checkForUpdate(org.project_kessel.api.inventory.v1beta2.CheckForUpdateRequest request,
        io.grpc.stub.StreamObserver<org.project_kessel.api.inventory.v1beta2.CheckForUpdateResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getCheckForUpdateMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Performs bulk permission checks for multiple resource-subject-relation combinations.
     * This API is more efficient than making individual Check calls when verifying permissions
     * for multiple items. It answers questions like:
     * "Which of these resources can subject *X* perform action *Y* on?"
     * Common use cases include:
     * - Filtering lists based on user permissions
     * - Batch authorization checks before performing bulk operations
     * - Dashboard rendering with multiple permission checks
     * - Pre-authorization for UI components
     * The response includes a result for each item in the request, maintaining the same order.
     * </pre>
     */
    public void checkBulk(org.project_kessel.api.inventory.v1beta2.CheckBulkRequest request,
        io.grpc.stub.StreamObserver<org.project_kessel.api.inventory.v1beta2.CheckBulkResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getCheckBulkMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Reports to Kessel Inventory that a Resource has been created or has been updated.
     * Reporters can use this API to report facts about their resources in order to
     * facilitate integration, correlation, and access control.
     * Each call can include:
     * - Reporter-specific attributes and relationships (`representations.reporter`)
     * - Shared attributes and relationships common to all reporters (`representations.common`)
     * - Identifiers and metadata that allow correlation to an existing resource
     * Multiple reporters may report representations for the same resource.
     * Kessel Inventory correlates these
     * based on correlation keys provided for a given resource type
     * All versions of your reported facts will be retained and can be queried as needed
     * The relationships reported through this API are used to determine relationship check outcomes
     * via the Check and CheckForUpdate APIs.
     * Reporters are responsible for ensuring delivery guarantees and message ordering
     * appropriate to the sensitivity and consistency needs of their use case.
     * This API does **not** guarantee immediate read-your-writes consistency by default.
     *  If a reporter requires newly submitted resources or relationships to be visible
     * in subsequent checks (e.g., `Check`), the request must explicitly set
     * `write_visibility = IMMEDIATE`.
     * </pre>
     */
    public void reportResource(org.project_kessel.api.inventory.v1beta2.ReportResourceRequest request,
        io.grpc.stub.StreamObserver<org.project_kessel.api.inventory.v1beta2.ReportResourceResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getReportResourceMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Reports to Kessel Inventory that a Reporter's representation of a Resource has been deleted.
     * This operation is typically used when a resource has been decommissioned or
     * is no longer reported by any authorized system.
     * As a result, relationship checks performed via the `Check` and
     * `CheckForUpdate` APIs will no longer resolve positively for the deleted
     * resource. Any decisions that depend on relationships tied to
     * this resource will be affected.
     * As an example, it can revoke previously granted access across the system.
     * </pre>
     */
    public void deleteResource(org.project_kessel.api.inventory.v1beta2.DeleteResourceRequest request,
        io.grpc.stub.StreamObserver<org.project_kessel.api.inventory.v1beta2.DeleteResourceResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getDeleteResourceMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Streams a list of objects where the given subject has the specified relation.
     * This relationship query answers the question:
     * "Which objects of type *X* does subject *Y* have the *Z* relation to?"
     * It is often used to power user-facing dashboards, filtered UIs, or policy-driven
     * access lists. The result is streamed incrementally to support large datasets and
     * reduce client-side latency or memory pressure.
     * Pagination and consistency controls allow fine-tuned performance and data freshness.
     * </pre>
     */
    public void streamedListObjects(org.project_kessel.api.inventory.v1beta2.StreamedListObjectsRequest request,
        io.grpc.stub.StreamObserver<org.project_kessel.api.inventory.v1beta2.StreamedListObjectsResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncServerStreamingCall(
          getChannel().newCall(getStreamedListObjectsMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * A stub to allow clients to do synchronous rpc calls to service KesselInventoryService.
   * <pre>
   * KesselInventoryService provides APIs to perform relationship checks
   * and manage inventory resource lifecycles (reporting and deletion).
   * </pre>
   */
  public static final class KesselInventoryServiceBlockingV2Stub
      extends io.grpc.stub.AbstractBlockingStub<KesselInventoryServiceBlockingV2Stub> {
    private KesselInventoryServiceBlockingV2Stub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected KesselInventoryServiceBlockingV2Stub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new KesselInventoryServiceBlockingV2Stub(channel, callOptions);
    }

    /**
     * <pre>
     * Performs an relationship check to determine whether a subject has a specific
     * permission or relationship on a resource.
     * This API evaluates whether the provided subject is a member of the specified relation
     * (e.g., "viewer", "editor", "admin") on the target object. It answers the question:
     * "Does subject *X* have relation *Y* on object *Z*?"
     * Common use cases include enforcing read access, conditional UI visibility,
     * or authorization gating for downstream API calls.
     * </pre>
     */
    public org.project_kessel.api.inventory.v1beta2.CheckResponse check(org.project_kessel.api.inventory.v1beta2.CheckRequest request) throws io.grpc.StatusException {
      return io.grpc.stub.ClientCalls.blockingV2UnaryCall(
          getChannel(), getCheckMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Performs a strongly consistent relationship check to determine whether a subject
     * has a specific relation to an object (representing, for example, a permission).
     * This API answers the question:
     * "Is subject *X* currently authorized to update or modify resource *Y*?"
     * Unlike the basic `Check` endpoint, this method guarantees a fully up-to-date
     * view of the relationship state (e.g., not relying on cached or eventually consistent data).
     * It is intended to be used just prior to sensitive operation (e.g., update, delete)
     * which depend on the current state of the relationship.
     * </pre>
     */
    public org.project_kessel.api.inventory.v1beta2.CheckForUpdateResponse checkForUpdate(org.project_kessel.api.inventory.v1beta2.CheckForUpdateRequest request) throws io.grpc.StatusException {
      return io.grpc.stub.ClientCalls.blockingV2UnaryCall(
          getChannel(), getCheckForUpdateMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Performs bulk permission checks for multiple resource-subject-relation combinations.
     * This API is more efficient than making individual Check calls when verifying permissions
     * for multiple items. It answers questions like:
     * "Which of these resources can subject *X* perform action *Y* on?"
     * Common use cases include:
     * - Filtering lists based on user permissions
     * - Batch authorization checks before performing bulk operations
     * - Dashboard rendering with multiple permission checks
     * - Pre-authorization for UI components
     * The response includes a result for each item in the request, maintaining the same order.
     * </pre>
     */
    public org.project_kessel.api.inventory.v1beta2.CheckBulkResponse checkBulk(org.project_kessel.api.inventory.v1beta2.CheckBulkRequest request) throws io.grpc.StatusException {
      return io.grpc.stub.ClientCalls.blockingV2UnaryCall(
          getChannel(), getCheckBulkMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Reports to Kessel Inventory that a Resource has been created or has been updated.
     * Reporters can use this API to report facts about their resources in order to
     * facilitate integration, correlation, and access control.
     * Each call can include:
     * - Reporter-specific attributes and relationships (`representations.reporter`)
     * - Shared attributes and relationships common to all reporters (`representations.common`)
     * - Identifiers and metadata that allow correlation to an existing resource
     * Multiple reporters may report representations for the same resource.
     * Kessel Inventory correlates these
     * based on correlation keys provided for a given resource type
     * All versions of your reported facts will be retained and can be queried as needed
     * The relationships reported through this API are used to determine relationship check outcomes
     * via the Check and CheckForUpdate APIs.
     * Reporters are responsible for ensuring delivery guarantees and message ordering
     * appropriate to the sensitivity and consistency needs of their use case.
     * This API does **not** guarantee immediate read-your-writes consistency by default.
     *  If a reporter requires newly submitted resources or relationships to be visible
     * in subsequent checks (e.g., `Check`), the request must explicitly set
     * `write_visibility = IMMEDIATE`.
     * </pre>
     */
    public org.project_kessel.api.inventory.v1beta2.ReportResourceResponse reportResource(org.project_kessel.api.inventory.v1beta2.ReportResourceRequest request) throws io.grpc.StatusException {
      return io.grpc.stub.ClientCalls.blockingV2UnaryCall(
          getChannel(), getReportResourceMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Reports to Kessel Inventory that a Reporter's representation of a Resource has been deleted.
     * This operation is typically used when a resource has been decommissioned or
     * is no longer reported by any authorized system.
     * As a result, relationship checks performed via the `Check` and
     * `CheckForUpdate` APIs will no longer resolve positively for the deleted
     * resource. Any decisions that depend on relationships tied to
     * this resource will be affected.
     * As an example, it can revoke previously granted access across the system.
     * </pre>
     */
    public org.project_kessel.api.inventory.v1beta2.DeleteResourceResponse deleteResource(org.project_kessel.api.inventory.v1beta2.DeleteResourceRequest request) throws io.grpc.StatusException {
      return io.grpc.stub.ClientCalls.blockingV2UnaryCall(
          getChannel(), getDeleteResourceMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Streams a list of objects where the given subject has the specified relation.
     * This relationship query answers the question:
     * "Which objects of type *X* does subject *Y* have the *Z* relation to?"
     * It is often used to power user-facing dashboards, filtered UIs, or policy-driven
     * access lists. The result is streamed incrementally to support large datasets and
     * reduce client-side latency or memory pressure.
     * Pagination and consistency controls allow fine-tuned performance and data freshness.
     * </pre>
     */
    @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/10918")
    public io.grpc.stub.BlockingClientCall<?, org.project_kessel.api.inventory.v1beta2.StreamedListObjectsResponse>
        streamedListObjects(org.project_kessel.api.inventory.v1beta2.StreamedListObjectsRequest request) {
      return io.grpc.stub.ClientCalls.blockingV2ServerStreamingCall(
          getChannel(), getStreamedListObjectsMethod(), getCallOptions(), request);
    }
  }

  /**
   * A stub to allow clients to do limited synchronous rpc calls to service KesselInventoryService.
   * <pre>
   * KesselInventoryService provides APIs to perform relationship checks
   * and manage inventory resource lifecycles (reporting and deletion).
   * </pre>
   */
  public static final class KesselInventoryServiceBlockingStub
      extends io.grpc.stub.AbstractBlockingStub<KesselInventoryServiceBlockingStub> {
    private KesselInventoryServiceBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected KesselInventoryServiceBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new KesselInventoryServiceBlockingStub(channel, callOptions);
    }

    /**
     * <pre>
     * Performs an relationship check to determine whether a subject has a specific
     * permission or relationship on a resource.
     * This API evaluates whether the provided subject is a member of the specified relation
     * (e.g., "viewer", "editor", "admin") on the target object. It answers the question:
     * "Does subject *X* have relation *Y* on object *Z*?"
     * Common use cases include enforcing read access, conditional UI visibility,
     * or authorization gating for downstream API calls.
     * </pre>
     */
    public org.project_kessel.api.inventory.v1beta2.CheckResponse check(org.project_kessel.api.inventory.v1beta2.CheckRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getCheckMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Performs a strongly consistent relationship check to determine whether a subject
     * has a specific relation to an object (representing, for example, a permission).
     * This API answers the question:
     * "Is subject *X* currently authorized to update or modify resource *Y*?"
     * Unlike the basic `Check` endpoint, this method guarantees a fully up-to-date
     * view of the relationship state (e.g., not relying on cached or eventually consistent data).
     * It is intended to be used just prior to sensitive operation (e.g., update, delete)
     * which depend on the current state of the relationship.
     * </pre>
     */
    public org.project_kessel.api.inventory.v1beta2.CheckForUpdateResponse checkForUpdate(org.project_kessel.api.inventory.v1beta2.CheckForUpdateRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getCheckForUpdateMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Performs bulk permission checks for multiple resource-subject-relation combinations.
     * This API is more efficient than making individual Check calls when verifying permissions
     * for multiple items. It answers questions like:
     * "Which of these resources can subject *X* perform action *Y* on?"
     * Common use cases include:
     * - Filtering lists based on user permissions
     * - Batch authorization checks before performing bulk operations
     * - Dashboard rendering with multiple permission checks
     * - Pre-authorization for UI components
     * The response includes a result for each item in the request, maintaining the same order.
     * </pre>
     */
    public org.project_kessel.api.inventory.v1beta2.CheckBulkResponse checkBulk(org.project_kessel.api.inventory.v1beta2.CheckBulkRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getCheckBulkMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Reports to Kessel Inventory that a Resource has been created or has been updated.
     * Reporters can use this API to report facts about their resources in order to
     * facilitate integration, correlation, and access control.
     * Each call can include:
     * - Reporter-specific attributes and relationships (`representations.reporter`)
     * - Shared attributes and relationships common to all reporters (`representations.common`)
     * - Identifiers and metadata that allow correlation to an existing resource
     * Multiple reporters may report representations for the same resource.
     * Kessel Inventory correlates these
     * based on correlation keys provided for a given resource type
     * All versions of your reported facts will be retained and can be queried as needed
     * The relationships reported through this API are used to determine relationship check outcomes
     * via the Check and CheckForUpdate APIs.
     * Reporters are responsible for ensuring delivery guarantees and message ordering
     * appropriate to the sensitivity and consistency needs of their use case.
     * This API does **not** guarantee immediate read-your-writes consistency by default.
     *  If a reporter requires newly submitted resources or relationships to be visible
     * in subsequent checks (e.g., `Check`), the request must explicitly set
     * `write_visibility = IMMEDIATE`.
     * </pre>
     */
    public org.project_kessel.api.inventory.v1beta2.ReportResourceResponse reportResource(org.project_kessel.api.inventory.v1beta2.ReportResourceRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getReportResourceMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Reports to Kessel Inventory that a Reporter's representation of a Resource has been deleted.
     * This operation is typically used when a resource has been decommissioned or
     * is no longer reported by any authorized system.
     * As a result, relationship checks performed via the `Check` and
     * `CheckForUpdate` APIs will no longer resolve positively for the deleted
     * resource. Any decisions that depend on relationships tied to
     * this resource will be affected.
     * As an example, it can revoke previously granted access across the system.
     * </pre>
     */
    public org.project_kessel.api.inventory.v1beta2.DeleteResourceResponse deleteResource(org.project_kessel.api.inventory.v1beta2.DeleteResourceRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getDeleteResourceMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Streams a list of objects where the given subject has the specified relation.
     * This relationship query answers the question:
     * "Which objects of type *X* does subject *Y* have the *Z* relation to?"
     * It is often used to power user-facing dashboards, filtered UIs, or policy-driven
     * access lists. The result is streamed incrementally to support large datasets and
     * reduce client-side latency or memory pressure.
     * Pagination and consistency controls allow fine-tuned performance and data freshness.
     * </pre>
     */
    public java.util.Iterator<org.project_kessel.api.inventory.v1beta2.StreamedListObjectsResponse> streamedListObjects(
        org.project_kessel.api.inventory.v1beta2.StreamedListObjectsRequest request) {
      return io.grpc.stub.ClientCalls.blockingServerStreamingCall(
          getChannel(), getStreamedListObjectsMethod(), getCallOptions(), request);
    }
  }

  /**
   * A stub to allow clients to do ListenableFuture-style rpc calls to service KesselInventoryService.
   * <pre>
   * KesselInventoryService provides APIs to perform relationship checks
   * and manage inventory resource lifecycles (reporting and deletion).
   * </pre>
   */
  public static final class KesselInventoryServiceFutureStub
      extends io.grpc.stub.AbstractFutureStub<KesselInventoryServiceFutureStub> {
    private KesselInventoryServiceFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected KesselInventoryServiceFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new KesselInventoryServiceFutureStub(channel, callOptions);
    }

    /**
     * <pre>
     * Performs an relationship check to determine whether a subject has a specific
     * permission or relationship on a resource.
     * This API evaluates whether the provided subject is a member of the specified relation
     * (e.g., "viewer", "editor", "admin") on the target object. It answers the question:
     * "Does subject *X* have relation *Y* on object *Z*?"
     * Common use cases include enforcing read access, conditional UI visibility,
     * or authorization gating for downstream API calls.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<org.project_kessel.api.inventory.v1beta2.CheckResponse> check(
        org.project_kessel.api.inventory.v1beta2.CheckRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getCheckMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Performs a strongly consistent relationship check to determine whether a subject
     * has a specific relation to an object (representing, for example, a permission).
     * This API answers the question:
     * "Is subject *X* currently authorized to update or modify resource *Y*?"
     * Unlike the basic `Check` endpoint, this method guarantees a fully up-to-date
     * view of the relationship state (e.g., not relying on cached or eventually consistent data).
     * It is intended to be used just prior to sensitive operation (e.g., update, delete)
     * which depend on the current state of the relationship.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<org.project_kessel.api.inventory.v1beta2.CheckForUpdateResponse> checkForUpdate(
        org.project_kessel.api.inventory.v1beta2.CheckForUpdateRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getCheckForUpdateMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Performs bulk permission checks for multiple resource-subject-relation combinations.
     * This API is more efficient than making individual Check calls when verifying permissions
     * for multiple items. It answers questions like:
     * "Which of these resources can subject *X* perform action *Y* on?"
     * Common use cases include:
     * - Filtering lists based on user permissions
     * - Batch authorization checks before performing bulk operations
     * - Dashboard rendering with multiple permission checks
     * - Pre-authorization for UI components
     * The response includes a result for each item in the request, maintaining the same order.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<org.project_kessel.api.inventory.v1beta2.CheckBulkResponse> checkBulk(
        org.project_kessel.api.inventory.v1beta2.CheckBulkRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getCheckBulkMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Reports to Kessel Inventory that a Resource has been created or has been updated.
     * Reporters can use this API to report facts about their resources in order to
     * facilitate integration, correlation, and access control.
     * Each call can include:
     * - Reporter-specific attributes and relationships (`representations.reporter`)
     * - Shared attributes and relationships common to all reporters (`representations.common`)
     * - Identifiers and metadata that allow correlation to an existing resource
     * Multiple reporters may report representations for the same resource.
     * Kessel Inventory correlates these
     * based on correlation keys provided for a given resource type
     * All versions of your reported facts will be retained and can be queried as needed
     * The relationships reported through this API are used to determine relationship check outcomes
     * via the Check and CheckForUpdate APIs.
     * Reporters are responsible for ensuring delivery guarantees and message ordering
     * appropriate to the sensitivity and consistency needs of their use case.
     * This API does **not** guarantee immediate read-your-writes consistency by default.
     *  If a reporter requires newly submitted resources or relationships to be visible
     * in subsequent checks (e.g., `Check`), the request must explicitly set
     * `write_visibility = IMMEDIATE`.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<org.project_kessel.api.inventory.v1beta2.ReportResourceResponse> reportResource(
        org.project_kessel.api.inventory.v1beta2.ReportResourceRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getReportResourceMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Reports to Kessel Inventory that a Reporter's representation of a Resource has been deleted.
     * This operation is typically used when a resource has been decommissioned or
     * is no longer reported by any authorized system.
     * As a result, relationship checks performed via the `Check` and
     * `CheckForUpdate` APIs will no longer resolve positively for the deleted
     * resource. Any decisions that depend on relationships tied to
     * this resource will be affected.
     * As an example, it can revoke previously granted access across the system.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<org.project_kessel.api.inventory.v1beta2.DeleteResourceResponse> deleteResource(
        org.project_kessel.api.inventory.v1beta2.DeleteResourceRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getDeleteResourceMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_CHECK = 0;
  private static final int METHODID_CHECK_FOR_UPDATE = 1;
  private static final int METHODID_CHECK_BULK = 2;
  private static final int METHODID_REPORT_RESOURCE = 3;
  private static final int METHODID_DELETE_RESOURCE = 4;
  private static final int METHODID_STREAMED_LIST_OBJECTS = 5;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final AsyncService serviceImpl;
    private final int methodId;

    MethodHandlers(AsyncService serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_CHECK:
          serviceImpl.check((org.project_kessel.api.inventory.v1beta2.CheckRequest) request,
              (io.grpc.stub.StreamObserver<org.project_kessel.api.inventory.v1beta2.CheckResponse>) responseObserver);
          break;
        case METHODID_CHECK_FOR_UPDATE:
          serviceImpl.checkForUpdate((org.project_kessel.api.inventory.v1beta2.CheckForUpdateRequest) request,
              (io.grpc.stub.StreamObserver<org.project_kessel.api.inventory.v1beta2.CheckForUpdateResponse>) responseObserver);
          break;
        case METHODID_CHECK_BULK:
          serviceImpl.checkBulk((org.project_kessel.api.inventory.v1beta2.CheckBulkRequest) request,
              (io.grpc.stub.StreamObserver<org.project_kessel.api.inventory.v1beta2.CheckBulkResponse>) responseObserver);
          break;
        case METHODID_REPORT_RESOURCE:
          serviceImpl.reportResource((org.project_kessel.api.inventory.v1beta2.ReportResourceRequest) request,
              (io.grpc.stub.StreamObserver<org.project_kessel.api.inventory.v1beta2.ReportResourceResponse>) responseObserver);
          break;
        case METHODID_DELETE_RESOURCE:
          serviceImpl.deleteResource((org.project_kessel.api.inventory.v1beta2.DeleteResourceRequest) request,
              (io.grpc.stub.StreamObserver<org.project_kessel.api.inventory.v1beta2.DeleteResourceResponse>) responseObserver);
          break;
        case METHODID_STREAMED_LIST_OBJECTS:
          serviceImpl.streamedListObjects((org.project_kessel.api.inventory.v1beta2.StreamedListObjectsRequest) request,
              (io.grpc.stub.StreamObserver<org.project_kessel.api.inventory.v1beta2.StreamedListObjectsResponse>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }
  }

  public static final io.grpc.ServerServiceDefinition bindService(AsyncService service) {
    return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
        .addMethod(
          getCheckMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              org.project_kessel.api.inventory.v1beta2.CheckRequest,
              org.project_kessel.api.inventory.v1beta2.CheckResponse>(
                service, METHODID_CHECK)))
        .addMethod(
          getCheckForUpdateMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              org.project_kessel.api.inventory.v1beta2.CheckForUpdateRequest,
              org.project_kessel.api.inventory.v1beta2.CheckForUpdateResponse>(
                service, METHODID_CHECK_FOR_UPDATE)))
        .addMethod(
          getCheckBulkMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              org.project_kessel.api.inventory.v1beta2.CheckBulkRequest,
              org.project_kessel.api.inventory.v1beta2.CheckBulkResponse>(
                service, METHODID_CHECK_BULK)))
        .addMethod(
          getReportResourceMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              org.project_kessel.api.inventory.v1beta2.ReportResourceRequest,
              org.project_kessel.api.inventory.v1beta2.ReportResourceResponse>(
                service, METHODID_REPORT_RESOURCE)))
        .addMethod(
          getDeleteResourceMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              org.project_kessel.api.inventory.v1beta2.DeleteResourceRequest,
              org.project_kessel.api.inventory.v1beta2.DeleteResourceResponse>(
                service, METHODID_DELETE_RESOURCE)))
        .addMethod(
          getStreamedListObjectsMethod(),
          io.grpc.stub.ServerCalls.asyncServerStreamingCall(
            new MethodHandlers<
              org.project_kessel.api.inventory.v1beta2.StreamedListObjectsRequest,
              org.project_kessel.api.inventory.v1beta2.StreamedListObjectsResponse>(
                service, METHODID_STREAMED_LIST_OBJECTS)))
        .build();
  }

  private static abstract class KesselInventoryServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    KesselInventoryServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return org.project_kessel.api.inventory.v1beta2.InventoryService.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("KesselInventoryService");
    }
  }

  private static final class KesselInventoryServiceFileDescriptorSupplier
      extends KesselInventoryServiceBaseDescriptorSupplier {
    KesselInventoryServiceFileDescriptorSupplier() {}
  }

  private static final class KesselInventoryServiceMethodDescriptorSupplier
      extends KesselInventoryServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final java.lang.String methodName;

    KesselInventoryServiceMethodDescriptorSupplier(java.lang.String methodName) {
      this.methodName = methodName;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.MethodDescriptor getMethodDescriptor() {
      return getServiceDescriptor().findMethodByName(methodName);
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (KesselInventoryServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new KesselInventoryServiceFileDescriptorSupplier())
              .addMethod(getCheckMethod())
              .addMethod(getCheckForUpdateMethod())
              .addMethod(getCheckBulkMethod())
              .addMethod(getReportResourceMethod())
              .addMethod(getDeleteResourceMethod())
              .addMethod(getStreamedListObjectsMethod())
              .build();
        }
      }
    }
    return result;
  }
}
