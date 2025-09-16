package org.project_kessel.api.inventory.v1beta1.authz;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 */
@io.grpc.stub.annotations.GrpcGenerated
public final class KesselCheckServiceGrpc {

  private KesselCheckServiceGrpc() {}

  public static final java.lang.String SERVICE_NAME = "kessel.inventory.v1beta1.authz.KesselCheckService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<org.project_kessel.api.inventory.v1beta1.authz.CheckRequest,
      org.project_kessel.api.inventory.v1beta1.authz.CheckResponse> getCheckMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Check",
      requestType = org.project_kessel.api.inventory.v1beta1.authz.CheckRequest.class,
      responseType = org.project_kessel.api.inventory.v1beta1.authz.CheckResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<org.project_kessel.api.inventory.v1beta1.authz.CheckRequest,
      org.project_kessel.api.inventory.v1beta1.authz.CheckResponse> getCheckMethod() {
    io.grpc.MethodDescriptor<org.project_kessel.api.inventory.v1beta1.authz.CheckRequest, org.project_kessel.api.inventory.v1beta1.authz.CheckResponse> getCheckMethod;
    if ((getCheckMethod = KesselCheckServiceGrpc.getCheckMethod) == null) {
      synchronized (KesselCheckServiceGrpc.class) {
        if ((getCheckMethod = KesselCheckServiceGrpc.getCheckMethod) == null) {
          KesselCheckServiceGrpc.getCheckMethod = getCheckMethod =
              io.grpc.MethodDescriptor.<org.project_kessel.api.inventory.v1beta1.authz.CheckRequest, org.project_kessel.api.inventory.v1beta1.authz.CheckResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Check"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  org.project_kessel.api.inventory.v1beta1.authz.CheckRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  org.project_kessel.api.inventory.v1beta1.authz.CheckResponse.getDefaultInstance()))
              .setSchemaDescriptor(new KesselCheckServiceMethodDescriptorSupplier("Check"))
              .build();
        }
      }
    }
    return getCheckMethod;
  }

  private static volatile io.grpc.MethodDescriptor<org.project_kessel.api.inventory.v1beta1.authz.CheckForUpdateRequest,
      org.project_kessel.api.inventory.v1beta1.authz.CheckForUpdateResponse> getCheckForUpdateMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "CheckForUpdate",
      requestType = org.project_kessel.api.inventory.v1beta1.authz.CheckForUpdateRequest.class,
      responseType = org.project_kessel.api.inventory.v1beta1.authz.CheckForUpdateResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<org.project_kessel.api.inventory.v1beta1.authz.CheckForUpdateRequest,
      org.project_kessel.api.inventory.v1beta1.authz.CheckForUpdateResponse> getCheckForUpdateMethod() {
    io.grpc.MethodDescriptor<org.project_kessel.api.inventory.v1beta1.authz.CheckForUpdateRequest, org.project_kessel.api.inventory.v1beta1.authz.CheckForUpdateResponse> getCheckForUpdateMethod;
    if ((getCheckForUpdateMethod = KesselCheckServiceGrpc.getCheckForUpdateMethod) == null) {
      synchronized (KesselCheckServiceGrpc.class) {
        if ((getCheckForUpdateMethod = KesselCheckServiceGrpc.getCheckForUpdateMethod) == null) {
          KesselCheckServiceGrpc.getCheckForUpdateMethod = getCheckForUpdateMethod =
              io.grpc.MethodDescriptor.<org.project_kessel.api.inventory.v1beta1.authz.CheckForUpdateRequest, org.project_kessel.api.inventory.v1beta1.authz.CheckForUpdateResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "CheckForUpdate"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  org.project_kessel.api.inventory.v1beta1.authz.CheckForUpdateRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  org.project_kessel.api.inventory.v1beta1.authz.CheckForUpdateResponse.getDefaultInstance()))
              .setSchemaDescriptor(new KesselCheckServiceMethodDescriptorSupplier("CheckForUpdate"))
              .build();
        }
      }
    }
    return getCheckForUpdateMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static KesselCheckServiceStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<KesselCheckServiceStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<KesselCheckServiceStub>() {
        @java.lang.Override
        public KesselCheckServiceStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new KesselCheckServiceStub(channel, callOptions);
        }
      };
    return KesselCheckServiceStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports all types of calls on the service
   */
  public static KesselCheckServiceBlockingV2Stub newBlockingV2Stub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<KesselCheckServiceBlockingV2Stub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<KesselCheckServiceBlockingV2Stub>() {
        @java.lang.Override
        public KesselCheckServiceBlockingV2Stub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new KesselCheckServiceBlockingV2Stub(channel, callOptions);
        }
      };
    return KesselCheckServiceBlockingV2Stub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static KesselCheckServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<KesselCheckServiceBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<KesselCheckServiceBlockingStub>() {
        @java.lang.Override
        public KesselCheckServiceBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new KesselCheckServiceBlockingStub(channel, callOptions);
        }
      };
    return KesselCheckServiceBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static KesselCheckServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<KesselCheckServiceFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<KesselCheckServiceFutureStub>() {
        @java.lang.Override
        public KesselCheckServiceFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new KesselCheckServiceFutureStub(channel, callOptions);
        }
      };
    return KesselCheckServiceFutureStub.newStub(factory, channel);
  }

  /**
   */
  public interface AsyncService {

    /**
     * <pre>
     * Checks for the existence of a single Relationship 
     * (a Relation between a Resource and a Subject or Subject Set).
     * </pre>
     */
    default void check(org.project_kessel.api.inventory.v1beta1.authz.CheckRequest request,
        io.grpc.stub.StreamObserver<org.project_kessel.api.inventory.v1beta1.authz.CheckResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getCheckMethod(), responseObserver);
    }

    /**
     */
    default void checkForUpdate(org.project_kessel.api.inventory.v1beta1.authz.CheckForUpdateRequest request,
        io.grpc.stub.StreamObserver<org.project_kessel.api.inventory.v1beta1.authz.CheckForUpdateResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getCheckForUpdateMethod(), responseObserver);
    }
  }

  /**
   * Base class for the server implementation of the service KesselCheckService.
   */
  public static abstract class KesselCheckServiceImplBase
      implements io.grpc.BindableService, AsyncService {

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return KesselCheckServiceGrpc.bindService(this);
    }
  }

  /**
   * A stub to allow clients to do asynchronous rpc calls to service KesselCheckService.
   */
  public static final class KesselCheckServiceStub
      extends io.grpc.stub.AbstractAsyncStub<KesselCheckServiceStub> {
    private KesselCheckServiceStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected KesselCheckServiceStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new KesselCheckServiceStub(channel, callOptions);
    }

    /**
     * <pre>
     * Checks for the existence of a single Relationship 
     * (a Relation between a Resource and a Subject or Subject Set).
     * </pre>
     */
    public void check(org.project_kessel.api.inventory.v1beta1.authz.CheckRequest request,
        io.grpc.stub.StreamObserver<org.project_kessel.api.inventory.v1beta1.authz.CheckResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getCheckMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void checkForUpdate(org.project_kessel.api.inventory.v1beta1.authz.CheckForUpdateRequest request,
        io.grpc.stub.StreamObserver<org.project_kessel.api.inventory.v1beta1.authz.CheckForUpdateResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getCheckForUpdateMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * A stub to allow clients to do synchronous rpc calls to service KesselCheckService.
   */
  public static final class KesselCheckServiceBlockingV2Stub
      extends io.grpc.stub.AbstractBlockingStub<KesselCheckServiceBlockingV2Stub> {
    private KesselCheckServiceBlockingV2Stub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected KesselCheckServiceBlockingV2Stub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new KesselCheckServiceBlockingV2Stub(channel, callOptions);
    }

    /**
     * <pre>
     * Checks for the existence of a single Relationship 
     * (a Relation between a Resource and a Subject or Subject Set).
     * </pre>
     */
    public org.project_kessel.api.inventory.v1beta1.authz.CheckResponse check(org.project_kessel.api.inventory.v1beta1.authz.CheckRequest request) throws io.grpc.StatusException {
      return io.grpc.stub.ClientCalls.blockingV2UnaryCall(
          getChannel(), getCheckMethod(), getCallOptions(), request);
    }

    /**
     */
    public org.project_kessel.api.inventory.v1beta1.authz.CheckForUpdateResponse checkForUpdate(org.project_kessel.api.inventory.v1beta1.authz.CheckForUpdateRequest request) throws io.grpc.StatusException {
      return io.grpc.stub.ClientCalls.blockingV2UnaryCall(
          getChannel(), getCheckForUpdateMethod(), getCallOptions(), request);
    }
  }

  /**
   * A stub to allow clients to do limited synchronous rpc calls to service KesselCheckService.
   */
  public static final class KesselCheckServiceBlockingStub
      extends io.grpc.stub.AbstractBlockingStub<KesselCheckServiceBlockingStub> {
    private KesselCheckServiceBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected KesselCheckServiceBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new KesselCheckServiceBlockingStub(channel, callOptions);
    }

    /**
     * <pre>
     * Checks for the existence of a single Relationship 
     * (a Relation between a Resource and a Subject or Subject Set).
     * </pre>
     */
    public org.project_kessel.api.inventory.v1beta1.authz.CheckResponse check(org.project_kessel.api.inventory.v1beta1.authz.CheckRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getCheckMethod(), getCallOptions(), request);
    }

    /**
     */
    public org.project_kessel.api.inventory.v1beta1.authz.CheckForUpdateResponse checkForUpdate(org.project_kessel.api.inventory.v1beta1.authz.CheckForUpdateRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getCheckForUpdateMethod(), getCallOptions(), request);
    }
  }

  /**
   * A stub to allow clients to do ListenableFuture-style rpc calls to service KesselCheckService.
   */
  public static final class KesselCheckServiceFutureStub
      extends io.grpc.stub.AbstractFutureStub<KesselCheckServiceFutureStub> {
    private KesselCheckServiceFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected KesselCheckServiceFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new KesselCheckServiceFutureStub(channel, callOptions);
    }

    /**
     * <pre>
     * Checks for the existence of a single Relationship 
     * (a Relation between a Resource and a Subject or Subject Set).
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<org.project_kessel.api.inventory.v1beta1.authz.CheckResponse> check(
        org.project_kessel.api.inventory.v1beta1.authz.CheckRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getCheckMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<org.project_kessel.api.inventory.v1beta1.authz.CheckForUpdateResponse> checkForUpdate(
        org.project_kessel.api.inventory.v1beta1.authz.CheckForUpdateRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getCheckForUpdateMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_CHECK = 0;
  private static final int METHODID_CHECK_FOR_UPDATE = 1;

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
          serviceImpl.check((org.project_kessel.api.inventory.v1beta1.authz.CheckRequest) request,
              (io.grpc.stub.StreamObserver<org.project_kessel.api.inventory.v1beta1.authz.CheckResponse>) responseObserver);
          break;
        case METHODID_CHECK_FOR_UPDATE:
          serviceImpl.checkForUpdate((org.project_kessel.api.inventory.v1beta1.authz.CheckForUpdateRequest) request,
              (io.grpc.stub.StreamObserver<org.project_kessel.api.inventory.v1beta1.authz.CheckForUpdateResponse>) responseObserver);
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
              org.project_kessel.api.inventory.v1beta1.authz.CheckRequest,
              org.project_kessel.api.inventory.v1beta1.authz.CheckResponse>(
                service, METHODID_CHECK)))
        .addMethod(
          getCheckForUpdateMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              org.project_kessel.api.inventory.v1beta1.authz.CheckForUpdateRequest,
              org.project_kessel.api.inventory.v1beta1.authz.CheckForUpdateResponse>(
                service, METHODID_CHECK_FOR_UPDATE)))
        .build();
  }

  private static abstract class KesselCheckServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    KesselCheckServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return org.project_kessel.api.inventory.v1beta1.authz.Check.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("KesselCheckService");
    }
  }

  private static final class KesselCheckServiceFileDescriptorSupplier
      extends KesselCheckServiceBaseDescriptorSupplier {
    KesselCheckServiceFileDescriptorSupplier() {}
  }

  private static final class KesselCheckServiceMethodDescriptorSupplier
      extends KesselCheckServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final java.lang.String methodName;

    KesselCheckServiceMethodDescriptorSupplier(java.lang.String methodName) {
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
      synchronized (KesselCheckServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new KesselCheckServiceFileDescriptorSupplier())
              .addMethod(getCheckMethod())
              .addMethod(getCheckForUpdateMethod())
              .build();
        }
      }
    }
    return result;
  }
}
