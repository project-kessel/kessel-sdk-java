package org.project_kessel.api.inventory.v1;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 */
@io.grpc.stub.annotations.GrpcGenerated
public final class KesselInventoryHealthServiceGrpc {

  private KesselInventoryHealthServiceGrpc() {}

  public static final java.lang.String SERVICE_NAME = "kessel.inventory.v1.KesselInventoryHealthService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<org.project_kessel.api.inventory.v1.GetLivezRequest,
      org.project_kessel.api.inventory.v1.GetLivezResponse> getGetLivezMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "GetLivez",
      requestType = org.project_kessel.api.inventory.v1.GetLivezRequest.class,
      responseType = org.project_kessel.api.inventory.v1.GetLivezResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<org.project_kessel.api.inventory.v1.GetLivezRequest,
      org.project_kessel.api.inventory.v1.GetLivezResponse> getGetLivezMethod() {
    io.grpc.MethodDescriptor<org.project_kessel.api.inventory.v1.GetLivezRequest, org.project_kessel.api.inventory.v1.GetLivezResponse> getGetLivezMethod;
    if ((getGetLivezMethod = KesselInventoryHealthServiceGrpc.getGetLivezMethod) == null) {
      synchronized (KesselInventoryHealthServiceGrpc.class) {
        if ((getGetLivezMethod = KesselInventoryHealthServiceGrpc.getGetLivezMethod) == null) {
          KesselInventoryHealthServiceGrpc.getGetLivezMethod = getGetLivezMethod =
              io.grpc.MethodDescriptor.<org.project_kessel.api.inventory.v1.GetLivezRequest, org.project_kessel.api.inventory.v1.GetLivezResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "GetLivez"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  org.project_kessel.api.inventory.v1.GetLivezRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  org.project_kessel.api.inventory.v1.GetLivezResponse.getDefaultInstance()))
              .setSchemaDescriptor(new KesselInventoryHealthServiceMethodDescriptorSupplier("GetLivez"))
              .build();
        }
      }
    }
    return getGetLivezMethod;
  }

  private static volatile io.grpc.MethodDescriptor<org.project_kessel.api.inventory.v1.GetReadyzRequest,
      org.project_kessel.api.inventory.v1.GetReadyzResponse> getGetReadyzMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "GetReadyz",
      requestType = org.project_kessel.api.inventory.v1.GetReadyzRequest.class,
      responseType = org.project_kessel.api.inventory.v1.GetReadyzResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<org.project_kessel.api.inventory.v1.GetReadyzRequest,
      org.project_kessel.api.inventory.v1.GetReadyzResponse> getGetReadyzMethod() {
    io.grpc.MethodDescriptor<org.project_kessel.api.inventory.v1.GetReadyzRequest, org.project_kessel.api.inventory.v1.GetReadyzResponse> getGetReadyzMethod;
    if ((getGetReadyzMethod = KesselInventoryHealthServiceGrpc.getGetReadyzMethod) == null) {
      synchronized (KesselInventoryHealthServiceGrpc.class) {
        if ((getGetReadyzMethod = KesselInventoryHealthServiceGrpc.getGetReadyzMethod) == null) {
          KesselInventoryHealthServiceGrpc.getGetReadyzMethod = getGetReadyzMethod =
              io.grpc.MethodDescriptor.<org.project_kessel.api.inventory.v1.GetReadyzRequest, org.project_kessel.api.inventory.v1.GetReadyzResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "GetReadyz"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  org.project_kessel.api.inventory.v1.GetReadyzRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  org.project_kessel.api.inventory.v1.GetReadyzResponse.getDefaultInstance()))
              .setSchemaDescriptor(new KesselInventoryHealthServiceMethodDescriptorSupplier("GetReadyz"))
              .build();
        }
      }
    }
    return getGetReadyzMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static KesselInventoryHealthServiceStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<KesselInventoryHealthServiceStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<KesselInventoryHealthServiceStub>() {
        @java.lang.Override
        public KesselInventoryHealthServiceStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new KesselInventoryHealthServiceStub(channel, callOptions);
        }
      };
    return KesselInventoryHealthServiceStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports all types of calls on the service
   */
  public static KesselInventoryHealthServiceBlockingV2Stub newBlockingV2Stub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<KesselInventoryHealthServiceBlockingV2Stub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<KesselInventoryHealthServiceBlockingV2Stub>() {
        @java.lang.Override
        public KesselInventoryHealthServiceBlockingV2Stub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new KesselInventoryHealthServiceBlockingV2Stub(channel, callOptions);
        }
      };
    return KesselInventoryHealthServiceBlockingV2Stub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static KesselInventoryHealthServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<KesselInventoryHealthServiceBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<KesselInventoryHealthServiceBlockingStub>() {
        @java.lang.Override
        public KesselInventoryHealthServiceBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new KesselInventoryHealthServiceBlockingStub(channel, callOptions);
        }
      };
    return KesselInventoryHealthServiceBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static KesselInventoryHealthServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<KesselInventoryHealthServiceFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<KesselInventoryHealthServiceFutureStub>() {
        @java.lang.Override
        public KesselInventoryHealthServiceFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new KesselInventoryHealthServiceFutureStub(channel, callOptions);
        }
      };
    return KesselInventoryHealthServiceFutureStub.newStub(factory, channel);
  }

  /**
   */
  public interface AsyncService {

    /**
     */
    default void getLivez(org.project_kessel.api.inventory.v1.GetLivezRequest request,
        io.grpc.stub.StreamObserver<org.project_kessel.api.inventory.v1.GetLivezResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getGetLivezMethod(), responseObserver);
    }

    /**
     */
    default void getReadyz(org.project_kessel.api.inventory.v1.GetReadyzRequest request,
        io.grpc.stub.StreamObserver<org.project_kessel.api.inventory.v1.GetReadyzResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getGetReadyzMethod(), responseObserver);
    }
  }

  /**
   * Base class for the server implementation of the service KesselInventoryHealthService.
   */
  public static abstract class KesselInventoryHealthServiceImplBase
      implements io.grpc.BindableService, AsyncService {

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return KesselInventoryHealthServiceGrpc.bindService(this);
    }
  }

  /**
   * A stub to allow clients to do asynchronous rpc calls to service KesselInventoryHealthService.
   */
  public static final class KesselInventoryHealthServiceStub
      extends io.grpc.stub.AbstractAsyncStub<KesselInventoryHealthServiceStub> {
    private KesselInventoryHealthServiceStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected KesselInventoryHealthServiceStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new KesselInventoryHealthServiceStub(channel, callOptions);
    }

    /**
     */
    public void getLivez(org.project_kessel.api.inventory.v1.GetLivezRequest request,
        io.grpc.stub.StreamObserver<org.project_kessel.api.inventory.v1.GetLivezResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getGetLivezMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void getReadyz(org.project_kessel.api.inventory.v1.GetReadyzRequest request,
        io.grpc.stub.StreamObserver<org.project_kessel.api.inventory.v1.GetReadyzResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getGetReadyzMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * A stub to allow clients to do synchronous rpc calls to service KesselInventoryHealthService.
   */
  public static final class KesselInventoryHealthServiceBlockingV2Stub
      extends io.grpc.stub.AbstractBlockingStub<KesselInventoryHealthServiceBlockingV2Stub> {
    private KesselInventoryHealthServiceBlockingV2Stub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected KesselInventoryHealthServiceBlockingV2Stub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new KesselInventoryHealthServiceBlockingV2Stub(channel, callOptions);
    }

    /**
     */
    public org.project_kessel.api.inventory.v1.GetLivezResponse getLivez(org.project_kessel.api.inventory.v1.GetLivezRequest request) throws io.grpc.StatusException {
      return io.grpc.stub.ClientCalls.blockingV2UnaryCall(
          getChannel(), getGetLivezMethod(), getCallOptions(), request);
    }

    /**
     */
    public org.project_kessel.api.inventory.v1.GetReadyzResponse getReadyz(org.project_kessel.api.inventory.v1.GetReadyzRequest request) throws io.grpc.StatusException {
      return io.grpc.stub.ClientCalls.blockingV2UnaryCall(
          getChannel(), getGetReadyzMethod(), getCallOptions(), request);
    }
  }

  /**
   * A stub to allow clients to do limited synchronous rpc calls to service KesselInventoryHealthService.
   */
  public static final class KesselInventoryHealthServiceBlockingStub
      extends io.grpc.stub.AbstractBlockingStub<KesselInventoryHealthServiceBlockingStub> {
    private KesselInventoryHealthServiceBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected KesselInventoryHealthServiceBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new KesselInventoryHealthServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public org.project_kessel.api.inventory.v1.GetLivezResponse getLivez(org.project_kessel.api.inventory.v1.GetLivezRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getGetLivezMethod(), getCallOptions(), request);
    }

    /**
     */
    public org.project_kessel.api.inventory.v1.GetReadyzResponse getReadyz(org.project_kessel.api.inventory.v1.GetReadyzRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getGetReadyzMethod(), getCallOptions(), request);
    }
  }

  /**
   * A stub to allow clients to do ListenableFuture-style rpc calls to service KesselInventoryHealthService.
   */
  public static final class KesselInventoryHealthServiceFutureStub
      extends io.grpc.stub.AbstractFutureStub<KesselInventoryHealthServiceFutureStub> {
    private KesselInventoryHealthServiceFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected KesselInventoryHealthServiceFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new KesselInventoryHealthServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<org.project_kessel.api.inventory.v1.GetLivezResponse> getLivez(
        org.project_kessel.api.inventory.v1.GetLivezRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getGetLivezMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<org.project_kessel.api.inventory.v1.GetReadyzResponse> getReadyz(
        org.project_kessel.api.inventory.v1.GetReadyzRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getGetReadyzMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_GET_LIVEZ = 0;
  private static final int METHODID_GET_READYZ = 1;

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
        case METHODID_GET_LIVEZ:
          serviceImpl.getLivez((org.project_kessel.api.inventory.v1.GetLivezRequest) request,
              (io.grpc.stub.StreamObserver<org.project_kessel.api.inventory.v1.GetLivezResponse>) responseObserver);
          break;
        case METHODID_GET_READYZ:
          serviceImpl.getReadyz((org.project_kessel.api.inventory.v1.GetReadyzRequest) request,
              (io.grpc.stub.StreamObserver<org.project_kessel.api.inventory.v1.GetReadyzResponse>) responseObserver);
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
          getGetLivezMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              org.project_kessel.api.inventory.v1.GetLivezRequest,
              org.project_kessel.api.inventory.v1.GetLivezResponse>(
                service, METHODID_GET_LIVEZ)))
        .addMethod(
          getGetReadyzMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              org.project_kessel.api.inventory.v1.GetReadyzRequest,
              org.project_kessel.api.inventory.v1.GetReadyzResponse>(
                service, METHODID_GET_READYZ)))
        .build();
  }

  private static abstract class KesselInventoryHealthServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    KesselInventoryHealthServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return org.project_kessel.api.inventory.v1.Health.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("KesselInventoryHealthService");
    }
  }

  private static final class KesselInventoryHealthServiceFileDescriptorSupplier
      extends KesselInventoryHealthServiceBaseDescriptorSupplier {
    KesselInventoryHealthServiceFileDescriptorSupplier() {}
  }

  private static final class KesselInventoryHealthServiceMethodDescriptorSupplier
      extends KesselInventoryHealthServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final java.lang.String methodName;

    KesselInventoryHealthServiceMethodDescriptorSupplier(java.lang.String methodName) {
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
      synchronized (KesselInventoryHealthServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new KesselInventoryHealthServiceFileDescriptorSupplier())
              .addMethod(getGetLivezMethod())
              .addMethod(getGetReadyzMethod())
              .build();
        }
      }
    }
    return result;
  }
}
