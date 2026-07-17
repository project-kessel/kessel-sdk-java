package org.project_kessel.api.inventory.v1beta2;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 * <pre>
 * DEPRECATED: KesselTupleService provides legacy tuple-layer operations.
 * This service exists only for RBAC backward compatibility and will be removed.
 * All endpoints are marked deprecated. Do not add new consumers.
 * Use KesselInventoryService's ReportResource for new integrations.
 * </pre>
 */
@io.grpc.stub.annotations.GrpcGenerated
public final class KesselTupleServiceGrpc {

  private KesselTupleServiceGrpc() {}

  public static final java.lang.String SERVICE_NAME = "kessel.inventory.v1beta2.KesselTupleService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<org.project_kessel.api.inventory.v1beta2.CreateTuplesRequest,
      org.project_kessel.api.inventory.v1beta2.CreateTuplesResponse> getCreateTuplesMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "CreateTuples",
      requestType = org.project_kessel.api.inventory.v1beta2.CreateTuplesRequest.class,
      responseType = org.project_kessel.api.inventory.v1beta2.CreateTuplesResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<org.project_kessel.api.inventory.v1beta2.CreateTuplesRequest,
      org.project_kessel.api.inventory.v1beta2.CreateTuplesResponse> getCreateTuplesMethod() {
    io.grpc.MethodDescriptor<org.project_kessel.api.inventory.v1beta2.CreateTuplesRequest, org.project_kessel.api.inventory.v1beta2.CreateTuplesResponse> getCreateTuplesMethod;
    if ((getCreateTuplesMethod = KesselTupleServiceGrpc.getCreateTuplesMethod) == null) {
      synchronized (KesselTupleServiceGrpc.class) {
        if ((getCreateTuplesMethod = KesselTupleServiceGrpc.getCreateTuplesMethod) == null) {
          KesselTupleServiceGrpc.getCreateTuplesMethod = getCreateTuplesMethod =
              io.grpc.MethodDescriptor.<org.project_kessel.api.inventory.v1beta2.CreateTuplesRequest, org.project_kessel.api.inventory.v1beta2.CreateTuplesResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "CreateTuples"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  org.project_kessel.api.inventory.v1beta2.CreateTuplesRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  org.project_kessel.api.inventory.v1beta2.CreateTuplesResponse.getDefaultInstance()))
              .setSchemaDescriptor(new KesselTupleServiceMethodDescriptorSupplier("CreateTuples"))
              .build();
        }
      }
    }
    return getCreateTuplesMethod;
  }

  private static volatile io.grpc.MethodDescriptor<org.project_kessel.api.inventory.v1beta2.DeleteTuplesRequest,
      org.project_kessel.api.inventory.v1beta2.DeleteTuplesResponse> getDeleteTuplesMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "DeleteTuples",
      requestType = org.project_kessel.api.inventory.v1beta2.DeleteTuplesRequest.class,
      responseType = org.project_kessel.api.inventory.v1beta2.DeleteTuplesResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<org.project_kessel.api.inventory.v1beta2.DeleteTuplesRequest,
      org.project_kessel.api.inventory.v1beta2.DeleteTuplesResponse> getDeleteTuplesMethod() {
    io.grpc.MethodDescriptor<org.project_kessel.api.inventory.v1beta2.DeleteTuplesRequest, org.project_kessel.api.inventory.v1beta2.DeleteTuplesResponse> getDeleteTuplesMethod;
    if ((getDeleteTuplesMethod = KesselTupleServiceGrpc.getDeleteTuplesMethod) == null) {
      synchronized (KesselTupleServiceGrpc.class) {
        if ((getDeleteTuplesMethod = KesselTupleServiceGrpc.getDeleteTuplesMethod) == null) {
          KesselTupleServiceGrpc.getDeleteTuplesMethod = getDeleteTuplesMethod =
              io.grpc.MethodDescriptor.<org.project_kessel.api.inventory.v1beta2.DeleteTuplesRequest, org.project_kessel.api.inventory.v1beta2.DeleteTuplesResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "DeleteTuples"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  org.project_kessel.api.inventory.v1beta2.DeleteTuplesRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  org.project_kessel.api.inventory.v1beta2.DeleteTuplesResponse.getDefaultInstance()))
              .setSchemaDescriptor(new KesselTupleServiceMethodDescriptorSupplier("DeleteTuples"))
              .build();
        }
      }
    }
    return getDeleteTuplesMethod;
  }

  private static volatile io.grpc.MethodDescriptor<org.project_kessel.api.inventory.v1beta2.ReadTuplesRequest,
      org.project_kessel.api.inventory.v1beta2.ReadTuplesResponse> getReadTuplesMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "ReadTuples",
      requestType = org.project_kessel.api.inventory.v1beta2.ReadTuplesRequest.class,
      responseType = org.project_kessel.api.inventory.v1beta2.ReadTuplesResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
  public static io.grpc.MethodDescriptor<org.project_kessel.api.inventory.v1beta2.ReadTuplesRequest,
      org.project_kessel.api.inventory.v1beta2.ReadTuplesResponse> getReadTuplesMethod() {
    io.grpc.MethodDescriptor<org.project_kessel.api.inventory.v1beta2.ReadTuplesRequest, org.project_kessel.api.inventory.v1beta2.ReadTuplesResponse> getReadTuplesMethod;
    if ((getReadTuplesMethod = KesselTupleServiceGrpc.getReadTuplesMethod) == null) {
      synchronized (KesselTupleServiceGrpc.class) {
        if ((getReadTuplesMethod = KesselTupleServiceGrpc.getReadTuplesMethod) == null) {
          KesselTupleServiceGrpc.getReadTuplesMethod = getReadTuplesMethod =
              io.grpc.MethodDescriptor.<org.project_kessel.api.inventory.v1beta2.ReadTuplesRequest, org.project_kessel.api.inventory.v1beta2.ReadTuplesResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "ReadTuples"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  org.project_kessel.api.inventory.v1beta2.ReadTuplesRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  org.project_kessel.api.inventory.v1beta2.ReadTuplesResponse.getDefaultInstance()))
              .setSchemaDescriptor(new KesselTupleServiceMethodDescriptorSupplier("ReadTuples"))
              .build();
        }
      }
    }
    return getReadTuplesMethod;
  }

  private static volatile io.grpc.MethodDescriptor<org.project_kessel.api.inventory.v1beta2.AcquireLockRequest,
      org.project_kessel.api.inventory.v1beta2.AcquireLockResponse> getAcquireLockMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "AcquireLock",
      requestType = org.project_kessel.api.inventory.v1beta2.AcquireLockRequest.class,
      responseType = org.project_kessel.api.inventory.v1beta2.AcquireLockResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<org.project_kessel.api.inventory.v1beta2.AcquireLockRequest,
      org.project_kessel.api.inventory.v1beta2.AcquireLockResponse> getAcquireLockMethod() {
    io.grpc.MethodDescriptor<org.project_kessel.api.inventory.v1beta2.AcquireLockRequest, org.project_kessel.api.inventory.v1beta2.AcquireLockResponse> getAcquireLockMethod;
    if ((getAcquireLockMethod = KesselTupleServiceGrpc.getAcquireLockMethod) == null) {
      synchronized (KesselTupleServiceGrpc.class) {
        if ((getAcquireLockMethod = KesselTupleServiceGrpc.getAcquireLockMethod) == null) {
          KesselTupleServiceGrpc.getAcquireLockMethod = getAcquireLockMethod =
              io.grpc.MethodDescriptor.<org.project_kessel.api.inventory.v1beta2.AcquireLockRequest, org.project_kessel.api.inventory.v1beta2.AcquireLockResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "AcquireLock"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  org.project_kessel.api.inventory.v1beta2.AcquireLockRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  org.project_kessel.api.inventory.v1beta2.AcquireLockResponse.getDefaultInstance()))
              .setSchemaDescriptor(new KesselTupleServiceMethodDescriptorSupplier("AcquireLock"))
              .build();
        }
      }
    }
    return getAcquireLockMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static KesselTupleServiceStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<KesselTupleServiceStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<KesselTupleServiceStub>() {
        @java.lang.Override
        public KesselTupleServiceStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new KesselTupleServiceStub(channel, callOptions);
        }
      };
    return KesselTupleServiceStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports all types of calls on the service
   */
  public static KesselTupleServiceBlockingV2Stub newBlockingV2Stub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<KesselTupleServiceBlockingV2Stub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<KesselTupleServiceBlockingV2Stub>() {
        @java.lang.Override
        public KesselTupleServiceBlockingV2Stub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new KesselTupleServiceBlockingV2Stub(channel, callOptions);
        }
      };
    return KesselTupleServiceBlockingV2Stub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static KesselTupleServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<KesselTupleServiceBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<KesselTupleServiceBlockingStub>() {
        @java.lang.Override
        public KesselTupleServiceBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new KesselTupleServiceBlockingStub(channel, callOptions);
        }
      };
    return KesselTupleServiceBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static KesselTupleServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<KesselTupleServiceFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<KesselTupleServiceFutureStub>() {
        @java.lang.Override
        public KesselTupleServiceFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new KesselTupleServiceFutureStub(channel, callOptions);
        }
      };
    return KesselTupleServiceFutureStub.newStub(factory, channel);
  }

  /**
   * <pre>
   * DEPRECATED: KesselTupleService provides legacy tuple-layer operations.
   * This service exists only for RBAC backward compatibility and will be removed.
   * All endpoints are marked deprecated. Do not add new consumers.
   * Use KesselInventoryService's ReportResource for new integrations.
   * </pre>
   */
  public interface AsyncService {

    /**
     * <pre>
     * DEPRECATED: CreateTuples creates relationship tuples.
     * This endpoint exists only for RBAC backward compatibility.
     * Use ReportResource instead for new code.
     * </pre>
     */
    @java.lang.Deprecated
    default void createTuples(org.project_kessel.api.inventory.v1beta2.CreateTuplesRequest request,
        io.grpc.stub.StreamObserver<org.project_kessel.api.inventory.v1beta2.CreateTuplesResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getCreateTuplesMethod(), responseObserver);
    }

    /**
     * <pre>
     * DEPRECATED: DeleteTuples deletes relationship tuples.
     * This endpoint exists only for RBAC backward compatibility.
     * Use DeleteResource instead for new code.
     * </pre>
     */
    @java.lang.Deprecated
    default void deleteTuples(org.project_kessel.api.inventory.v1beta2.DeleteTuplesRequest request,
        io.grpc.stub.StreamObserver<org.project_kessel.api.inventory.v1beta2.DeleteTuplesResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getDeleteTuplesMethod(), responseObserver);
    }

    /**
     * <pre>
     * DEPRECATED: ReadTuples reads relationship tuples.
     * This endpoint exists only for RBAC backward compatibility.
     * Use StreamedListObjects/StreamedListSubjects instead for new code.
     * </pre>
     */
    @java.lang.Deprecated
    default void readTuples(org.project_kessel.api.inventory.v1beta2.ReadTuplesRequest request,
        io.grpc.stub.StreamObserver<org.project_kessel.api.inventory.v1beta2.ReadTuplesResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getReadTuplesMethod(), responseObserver);
    }

    /**
     * <pre>
     * DEPRECATED: AcquireLock acquires a distributed lock.
     * This endpoint exists only for RBAC backward compatibility.
     * </pre>
     */
    @java.lang.Deprecated
    default void acquireLock(org.project_kessel.api.inventory.v1beta2.AcquireLockRequest request,
        io.grpc.stub.StreamObserver<org.project_kessel.api.inventory.v1beta2.AcquireLockResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getAcquireLockMethod(), responseObserver);
    }
  }

  /**
   * Base class for the server implementation of the service KesselTupleService.
   * <pre>
   * DEPRECATED: KesselTupleService provides legacy tuple-layer operations.
   * This service exists only for RBAC backward compatibility and will be removed.
   * All endpoints are marked deprecated. Do not add new consumers.
   * Use KesselInventoryService's ReportResource for new integrations.
   * </pre>
   */
  public static abstract class KesselTupleServiceImplBase
      implements io.grpc.BindableService, AsyncService {

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return KesselTupleServiceGrpc.bindService(this);
    }
  }

  /**
   * A stub to allow clients to do asynchronous rpc calls to service KesselTupleService.
   * <pre>
   * DEPRECATED: KesselTupleService provides legacy tuple-layer operations.
   * This service exists only for RBAC backward compatibility and will be removed.
   * All endpoints are marked deprecated. Do not add new consumers.
   * Use KesselInventoryService's ReportResource for new integrations.
   * </pre>
   */
  public static final class KesselTupleServiceStub
      extends io.grpc.stub.AbstractAsyncStub<KesselTupleServiceStub> {
    private KesselTupleServiceStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected KesselTupleServiceStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new KesselTupleServiceStub(channel, callOptions);
    }

    /**
     * <pre>
     * DEPRECATED: CreateTuples creates relationship tuples.
     * This endpoint exists only for RBAC backward compatibility.
     * Use ReportResource instead for new code.
     * </pre>
     */
    @java.lang.Deprecated
    public void createTuples(org.project_kessel.api.inventory.v1beta2.CreateTuplesRequest request,
        io.grpc.stub.StreamObserver<org.project_kessel.api.inventory.v1beta2.CreateTuplesResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getCreateTuplesMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * DEPRECATED: DeleteTuples deletes relationship tuples.
     * This endpoint exists only for RBAC backward compatibility.
     * Use DeleteResource instead for new code.
     * </pre>
     */
    @java.lang.Deprecated
    public void deleteTuples(org.project_kessel.api.inventory.v1beta2.DeleteTuplesRequest request,
        io.grpc.stub.StreamObserver<org.project_kessel.api.inventory.v1beta2.DeleteTuplesResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getDeleteTuplesMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * DEPRECATED: ReadTuples reads relationship tuples.
     * This endpoint exists only for RBAC backward compatibility.
     * Use StreamedListObjects/StreamedListSubjects instead for new code.
     * </pre>
     */
    @java.lang.Deprecated
    public void readTuples(org.project_kessel.api.inventory.v1beta2.ReadTuplesRequest request,
        io.grpc.stub.StreamObserver<org.project_kessel.api.inventory.v1beta2.ReadTuplesResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncServerStreamingCall(
          getChannel().newCall(getReadTuplesMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * DEPRECATED: AcquireLock acquires a distributed lock.
     * This endpoint exists only for RBAC backward compatibility.
     * </pre>
     */
    @java.lang.Deprecated
    public void acquireLock(org.project_kessel.api.inventory.v1beta2.AcquireLockRequest request,
        io.grpc.stub.StreamObserver<org.project_kessel.api.inventory.v1beta2.AcquireLockResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getAcquireLockMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * A stub to allow clients to do synchronous rpc calls to service KesselTupleService.
   * <pre>
   * DEPRECATED: KesselTupleService provides legacy tuple-layer operations.
   * This service exists only for RBAC backward compatibility and will be removed.
   * All endpoints are marked deprecated. Do not add new consumers.
   * Use KesselInventoryService's ReportResource for new integrations.
   * </pre>
   */
  public static final class KesselTupleServiceBlockingV2Stub
      extends io.grpc.stub.AbstractBlockingStub<KesselTupleServiceBlockingV2Stub> {
    private KesselTupleServiceBlockingV2Stub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected KesselTupleServiceBlockingV2Stub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new KesselTupleServiceBlockingV2Stub(channel, callOptions);
    }

    /**
     * <pre>
     * DEPRECATED: CreateTuples creates relationship tuples.
     * This endpoint exists only for RBAC backward compatibility.
     * Use ReportResource instead for new code.
     * </pre>
     */
    @java.lang.Deprecated
    public org.project_kessel.api.inventory.v1beta2.CreateTuplesResponse createTuples(org.project_kessel.api.inventory.v1beta2.CreateTuplesRequest request) throws io.grpc.StatusException {
      return io.grpc.stub.ClientCalls.blockingV2UnaryCall(
          getChannel(), getCreateTuplesMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * DEPRECATED: DeleteTuples deletes relationship tuples.
     * This endpoint exists only for RBAC backward compatibility.
     * Use DeleteResource instead for new code.
     * </pre>
     */
    @java.lang.Deprecated
    public org.project_kessel.api.inventory.v1beta2.DeleteTuplesResponse deleteTuples(org.project_kessel.api.inventory.v1beta2.DeleteTuplesRequest request) throws io.grpc.StatusException {
      return io.grpc.stub.ClientCalls.blockingV2UnaryCall(
          getChannel(), getDeleteTuplesMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * DEPRECATED: ReadTuples reads relationship tuples.
     * This endpoint exists only for RBAC backward compatibility.
     * Use StreamedListObjects/StreamedListSubjects instead for new code.
     * </pre>
     */
    @java.lang.Deprecated
    @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/10918")
    public io.grpc.stub.BlockingClientCall<?, org.project_kessel.api.inventory.v1beta2.ReadTuplesResponse>
        readTuples(org.project_kessel.api.inventory.v1beta2.ReadTuplesRequest request) {
      return io.grpc.stub.ClientCalls.blockingV2ServerStreamingCall(
          getChannel(), getReadTuplesMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * DEPRECATED: AcquireLock acquires a distributed lock.
     * This endpoint exists only for RBAC backward compatibility.
     * </pre>
     */
    @java.lang.Deprecated
    public org.project_kessel.api.inventory.v1beta2.AcquireLockResponse acquireLock(org.project_kessel.api.inventory.v1beta2.AcquireLockRequest request) throws io.grpc.StatusException {
      return io.grpc.stub.ClientCalls.blockingV2UnaryCall(
          getChannel(), getAcquireLockMethod(), getCallOptions(), request);
    }
  }

  /**
   * A stub to allow clients to do limited synchronous rpc calls to service KesselTupleService.
   * <pre>
   * DEPRECATED: KesselTupleService provides legacy tuple-layer operations.
   * This service exists only for RBAC backward compatibility and will be removed.
   * All endpoints are marked deprecated. Do not add new consumers.
   * Use KesselInventoryService's ReportResource for new integrations.
   * </pre>
   */
  public static final class KesselTupleServiceBlockingStub
      extends io.grpc.stub.AbstractBlockingStub<KesselTupleServiceBlockingStub> {
    private KesselTupleServiceBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected KesselTupleServiceBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new KesselTupleServiceBlockingStub(channel, callOptions);
    }

    /**
     * <pre>
     * DEPRECATED: CreateTuples creates relationship tuples.
     * This endpoint exists only for RBAC backward compatibility.
     * Use ReportResource instead for new code.
     * </pre>
     */
    @java.lang.Deprecated
    public org.project_kessel.api.inventory.v1beta2.CreateTuplesResponse createTuples(org.project_kessel.api.inventory.v1beta2.CreateTuplesRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getCreateTuplesMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * DEPRECATED: DeleteTuples deletes relationship tuples.
     * This endpoint exists only for RBAC backward compatibility.
     * Use DeleteResource instead for new code.
     * </pre>
     */
    @java.lang.Deprecated
    public org.project_kessel.api.inventory.v1beta2.DeleteTuplesResponse deleteTuples(org.project_kessel.api.inventory.v1beta2.DeleteTuplesRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getDeleteTuplesMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * DEPRECATED: ReadTuples reads relationship tuples.
     * This endpoint exists only for RBAC backward compatibility.
     * Use StreamedListObjects/StreamedListSubjects instead for new code.
     * </pre>
     */
    @java.lang.Deprecated
    public java.util.Iterator<org.project_kessel.api.inventory.v1beta2.ReadTuplesResponse> readTuples(
        org.project_kessel.api.inventory.v1beta2.ReadTuplesRequest request) {
      return io.grpc.stub.ClientCalls.blockingServerStreamingCall(
          getChannel(), getReadTuplesMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * DEPRECATED: AcquireLock acquires a distributed lock.
     * This endpoint exists only for RBAC backward compatibility.
     * </pre>
     */
    @java.lang.Deprecated
    public org.project_kessel.api.inventory.v1beta2.AcquireLockResponse acquireLock(org.project_kessel.api.inventory.v1beta2.AcquireLockRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getAcquireLockMethod(), getCallOptions(), request);
    }
  }

  /**
   * A stub to allow clients to do ListenableFuture-style rpc calls to service KesselTupleService.
   * <pre>
   * DEPRECATED: KesselTupleService provides legacy tuple-layer operations.
   * This service exists only for RBAC backward compatibility and will be removed.
   * All endpoints are marked deprecated. Do not add new consumers.
   * Use KesselInventoryService's ReportResource for new integrations.
   * </pre>
   */
  public static final class KesselTupleServiceFutureStub
      extends io.grpc.stub.AbstractFutureStub<KesselTupleServiceFutureStub> {
    private KesselTupleServiceFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected KesselTupleServiceFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new KesselTupleServiceFutureStub(channel, callOptions);
    }

    /**
     * <pre>
     * DEPRECATED: CreateTuples creates relationship tuples.
     * This endpoint exists only for RBAC backward compatibility.
     * Use ReportResource instead for new code.
     * </pre>
     */
    @java.lang.Deprecated
    public com.google.common.util.concurrent.ListenableFuture<org.project_kessel.api.inventory.v1beta2.CreateTuplesResponse> createTuples(
        org.project_kessel.api.inventory.v1beta2.CreateTuplesRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getCreateTuplesMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * DEPRECATED: DeleteTuples deletes relationship tuples.
     * This endpoint exists only for RBAC backward compatibility.
     * Use DeleteResource instead for new code.
     * </pre>
     */
    @java.lang.Deprecated
    public com.google.common.util.concurrent.ListenableFuture<org.project_kessel.api.inventory.v1beta2.DeleteTuplesResponse> deleteTuples(
        org.project_kessel.api.inventory.v1beta2.DeleteTuplesRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getDeleteTuplesMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * DEPRECATED: AcquireLock acquires a distributed lock.
     * This endpoint exists only for RBAC backward compatibility.
     * </pre>
     */
    @java.lang.Deprecated
    public com.google.common.util.concurrent.ListenableFuture<org.project_kessel.api.inventory.v1beta2.AcquireLockResponse> acquireLock(
        org.project_kessel.api.inventory.v1beta2.AcquireLockRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getAcquireLockMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_CREATE_TUPLES = 0;
  private static final int METHODID_DELETE_TUPLES = 1;
  private static final int METHODID_READ_TUPLES = 2;
  private static final int METHODID_ACQUIRE_LOCK = 3;

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
        case METHODID_CREATE_TUPLES:
          serviceImpl.createTuples((org.project_kessel.api.inventory.v1beta2.CreateTuplesRequest) request,
              (io.grpc.stub.StreamObserver<org.project_kessel.api.inventory.v1beta2.CreateTuplesResponse>) responseObserver);
          break;
        case METHODID_DELETE_TUPLES:
          serviceImpl.deleteTuples((org.project_kessel.api.inventory.v1beta2.DeleteTuplesRequest) request,
              (io.grpc.stub.StreamObserver<org.project_kessel.api.inventory.v1beta2.DeleteTuplesResponse>) responseObserver);
          break;
        case METHODID_READ_TUPLES:
          serviceImpl.readTuples((org.project_kessel.api.inventory.v1beta2.ReadTuplesRequest) request,
              (io.grpc.stub.StreamObserver<org.project_kessel.api.inventory.v1beta2.ReadTuplesResponse>) responseObserver);
          break;
        case METHODID_ACQUIRE_LOCK:
          serviceImpl.acquireLock((org.project_kessel.api.inventory.v1beta2.AcquireLockRequest) request,
              (io.grpc.stub.StreamObserver<org.project_kessel.api.inventory.v1beta2.AcquireLockResponse>) responseObserver);
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
          getCreateTuplesMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              org.project_kessel.api.inventory.v1beta2.CreateTuplesRequest,
              org.project_kessel.api.inventory.v1beta2.CreateTuplesResponse>(
                service, METHODID_CREATE_TUPLES)))
        .addMethod(
          getDeleteTuplesMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              org.project_kessel.api.inventory.v1beta2.DeleteTuplesRequest,
              org.project_kessel.api.inventory.v1beta2.DeleteTuplesResponse>(
                service, METHODID_DELETE_TUPLES)))
        .addMethod(
          getReadTuplesMethod(),
          io.grpc.stub.ServerCalls.asyncServerStreamingCall(
            new MethodHandlers<
              org.project_kessel.api.inventory.v1beta2.ReadTuplesRequest,
              org.project_kessel.api.inventory.v1beta2.ReadTuplesResponse>(
                service, METHODID_READ_TUPLES)))
        .addMethod(
          getAcquireLockMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              org.project_kessel.api.inventory.v1beta2.AcquireLockRequest,
              org.project_kessel.api.inventory.v1beta2.AcquireLockResponse>(
                service, METHODID_ACQUIRE_LOCK)))
        .build();
  }

  private static abstract class KesselTupleServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    KesselTupleServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return org.project_kessel.api.inventory.v1beta2.TupleService.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("KesselTupleService");
    }
  }

  private static final class KesselTupleServiceFileDescriptorSupplier
      extends KesselTupleServiceBaseDescriptorSupplier {
    KesselTupleServiceFileDescriptorSupplier() {}
  }

  private static final class KesselTupleServiceMethodDescriptorSupplier
      extends KesselTupleServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final java.lang.String methodName;

    KesselTupleServiceMethodDescriptorSupplier(java.lang.String methodName) {
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
      synchronized (KesselTupleServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new KesselTupleServiceFileDescriptorSupplier())
              .addMethod(getCreateTuplesMethod())
              .addMethod(getDeleteTuplesMethod())
              .addMethod(getReadTuplesMethod())
              .addMethod(getAcquireLockMethod())
              .build();
        }
      }
    }
    return result;
  }
}
