package org.project_kessel.api.inventory.v1beta1.resources;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 */
@io.grpc.stub.annotations.GrpcGenerated
public final class KesselK8sClusterServiceGrpc {

  private KesselK8sClusterServiceGrpc() {}

  public static final java.lang.String SERVICE_NAME = "kessel.inventory.v1beta1.resources.KesselK8sClusterService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<org.project_kessel.api.inventory.v1beta1.resources.CreateK8sClusterRequest,
      org.project_kessel.api.inventory.v1beta1.resources.CreateK8sClusterResponse> getCreateK8sClusterMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "CreateK8sCluster",
      requestType = org.project_kessel.api.inventory.v1beta1.resources.CreateK8sClusterRequest.class,
      responseType = org.project_kessel.api.inventory.v1beta1.resources.CreateK8sClusterResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<org.project_kessel.api.inventory.v1beta1.resources.CreateK8sClusterRequest,
      org.project_kessel.api.inventory.v1beta1.resources.CreateK8sClusterResponse> getCreateK8sClusterMethod() {
    io.grpc.MethodDescriptor<org.project_kessel.api.inventory.v1beta1.resources.CreateK8sClusterRequest, org.project_kessel.api.inventory.v1beta1.resources.CreateK8sClusterResponse> getCreateK8sClusterMethod;
    if ((getCreateK8sClusterMethod = KesselK8sClusterServiceGrpc.getCreateK8sClusterMethod) == null) {
      synchronized (KesselK8sClusterServiceGrpc.class) {
        if ((getCreateK8sClusterMethod = KesselK8sClusterServiceGrpc.getCreateK8sClusterMethod) == null) {
          KesselK8sClusterServiceGrpc.getCreateK8sClusterMethod = getCreateK8sClusterMethod =
              io.grpc.MethodDescriptor.<org.project_kessel.api.inventory.v1beta1.resources.CreateK8sClusterRequest, org.project_kessel.api.inventory.v1beta1.resources.CreateK8sClusterResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "CreateK8sCluster"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  org.project_kessel.api.inventory.v1beta1.resources.CreateK8sClusterRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  org.project_kessel.api.inventory.v1beta1.resources.CreateK8sClusterResponse.getDefaultInstance()))
              .setSchemaDescriptor(new KesselK8sClusterServiceMethodDescriptorSupplier("CreateK8sCluster"))
              .build();
        }
      }
    }
    return getCreateK8sClusterMethod;
  }

  private static volatile io.grpc.MethodDescriptor<org.project_kessel.api.inventory.v1beta1.resources.UpdateK8sClusterRequest,
      org.project_kessel.api.inventory.v1beta1.resources.UpdateK8sClusterResponse> getUpdateK8sClusterMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "UpdateK8sCluster",
      requestType = org.project_kessel.api.inventory.v1beta1.resources.UpdateK8sClusterRequest.class,
      responseType = org.project_kessel.api.inventory.v1beta1.resources.UpdateK8sClusterResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<org.project_kessel.api.inventory.v1beta1.resources.UpdateK8sClusterRequest,
      org.project_kessel.api.inventory.v1beta1.resources.UpdateK8sClusterResponse> getUpdateK8sClusterMethod() {
    io.grpc.MethodDescriptor<org.project_kessel.api.inventory.v1beta1.resources.UpdateK8sClusterRequest, org.project_kessel.api.inventory.v1beta1.resources.UpdateK8sClusterResponse> getUpdateK8sClusterMethod;
    if ((getUpdateK8sClusterMethod = KesselK8sClusterServiceGrpc.getUpdateK8sClusterMethod) == null) {
      synchronized (KesselK8sClusterServiceGrpc.class) {
        if ((getUpdateK8sClusterMethod = KesselK8sClusterServiceGrpc.getUpdateK8sClusterMethod) == null) {
          KesselK8sClusterServiceGrpc.getUpdateK8sClusterMethod = getUpdateK8sClusterMethod =
              io.grpc.MethodDescriptor.<org.project_kessel.api.inventory.v1beta1.resources.UpdateK8sClusterRequest, org.project_kessel.api.inventory.v1beta1.resources.UpdateK8sClusterResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "UpdateK8sCluster"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  org.project_kessel.api.inventory.v1beta1.resources.UpdateK8sClusterRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  org.project_kessel.api.inventory.v1beta1.resources.UpdateK8sClusterResponse.getDefaultInstance()))
              .setSchemaDescriptor(new KesselK8sClusterServiceMethodDescriptorSupplier("UpdateK8sCluster"))
              .build();
        }
      }
    }
    return getUpdateK8sClusterMethod;
  }

  private static volatile io.grpc.MethodDescriptor<org.project_kessel.api.inventory.v1beta1.resources.DeleteK8sClusterRequest,
      org.project_kessel.api.inventory.v1beta1.resources.DeleteK8sClusterResponse> getDeleteK8sClusterMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "DeleteK8sCluster",
      requestType = org.project_kessel.api.inventory.v1beta1.resources.DeleteK8sClusterRequest.class,
      responseType = org.project_kessel.api.inventory.v1beta1.resources.DeleteK8sClusterResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<org.project_kessel.api.inventory.v1beta1.resources.DeleteK8sClusterRequest,
      org.project_kessel.api.inventory.v1beta1.resources.DeleteK8sClusterResponse> getDeleteK8sClusterMethod() {
    io.grpc.MethodDescriptor<org.project_kessel.api.inventory.v1beta1.resources.DeleteK8sClusterRequest, org.project_kessel.api.inventory.v1beta1.resources.DeleteK8sClusterResponse> getDeleteK8sClusterMethod;
    if ((getDeleteK8sClusterMethod = KesselK8sClusterServiceGrpc.getDeleteK8sClusterMethod) == null) {
      synchronized (KesselK8sClusterServiceGrpc.class) {
        if ((getDeleteK8sClusterMethod = KesselK8sClusterServiceGrpc.getDeleteK8sClusterMethod) == null) {
          KesselK8sClusterServiceGrpc.getDeleteK8sClusterMethod = getDeleteK8sClusterMethod =
              io.grpc.MethodDescriptor.<org.project_kessel.api.inventory.v1beta1.resources.DeleteK8sClusterRequest, org.project_kessel.api.inventory.v1beta1.resources.DeleteK8sClusterResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "DeleteK8sCluster"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  org.project_kessel.api.inventory.v1beta1.resources.DeleteK8sClusterRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  org.project_kessel.api.inventory.v1beta1.resources.DeleteK8sClusterResponse.getDefaultInstance()))
              .setSchemaDescriptor(new KesselK8sClusterServiceMethodDescriptorSupplier("DeleteK8sCluster"))
              .build();
        }
      }
    }
    return getDeleteK8sClusterMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static KesselK8sClusterServiceStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<KesselK8sClusterServiceStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<KesselK8sClusterServiceStub>() {
        @java.lang.Override
        public KesselK8sClusterServiceStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new KesselK8sClusterServiceStub(channel, callOptions);
        }
      };
    return KesselK8sClusterServiceStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports all types of calls on the service
   */
  public static KesselK8sClusterServiceBlockingV2Stub newBlockingV2Stub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<KesselK8sClusterServiceBlockingV2Stub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<KesselK8sClusterServiceBlockingV2Stub>() {
        @java.lang.Override
        public KesselK8sClusterServiceBlockingV2Stub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new KesselK8sClusterServiceBlockingV2Stub(channel, callOptions);
        }
      };
    return KesselK8sClusterServiceBlockingV2Stub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static KesselK8sClusterServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<KesselK8sClusterServiceBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<KesselK8sClusterServiceBlockingStub>() {
        @java.lang.Override
        public KesselK8sClusterServiceBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new KesselK8sClusterServiceBlockingStub(channel, callOptions);
        }
      };
    return KesselK8sClusterServiceBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static KesselK8sClusterServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<KesselK8sClusterServiceFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<KesselK8sClusterServiceFutureStub>() {
        @java.lang.Override
        public KesselK8sClusterServiceFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new KesselK8sClusterServiceFutureStub(channel, callOptions);
        }
      };
    return KesselK8sClusterServiceFutureStub.newStub(factory, channel);
  }

  /**
   */
  public interface AsyncService {

    /**
     */
    default void createK8sCluster(org.project_kessel.api.inventory.v1beta1.resources.CreateK8sClusterRequest request,
        io.grpc.stub.StreamObserver<org.project_kessel.api.inventory.v1beta1.resources.CreateK8sClusterResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getCreateK8sClusterMethod(), responseObserver);
    }

    /**
     */
    default void updateK8sCluster(org.project_kessel.api.inventory.v1beta1.resources.UpdateK8sClusterRequest request,
        io.grpc.stub.StreamObserver<org.project_kessel.api.inventory.v1beta1.resources.UpdateK8sClusterResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getUpdateK8sClusterMethod(), responseObserver);
    }

    /**
     */
    default void deleteK8sCluster(org.project_kessel.api.inventory.v1beta1.resources.DeleteK8sClusterRequest request,
        io.grpc.stub.StreamObserver<org.project_kessel.api.inventory.v1beta1.resources.DeleteK8sClusterResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getDeleteK8sClusterMethod(), responseObserver);
    }
  }

  /**
   * Base class for the server implementation of the service KesselK8sClusterService.
   */
  public static abstract class KesselK8sClusterServiceImplBase
      implements io.grpc.BindableService, AsyncService {

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return KesselK8sClusterServiceGrpc.bindService(this);
    }
  }

  /**
   * A stub to allow clients to do asynchronous rpc calls to service KesselK8sClusterService.
   */
  public static final class KesselK8sClusterServiceStub
      extends io.grpc.stub.AbstractAsyncStub<KesselK8sClusterServiceStub> {
    private KesselK8sClusterServiceStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected KesselK8sClusterServiceStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new KesselK8sClusterServiceStub(channel, callOptions);
    }

    /**
     */
    public void createK8sCluster(org.project_kessel.api.inventory.v1beta1.resources.CreateK8sClusterRequest request,
        io.grpc.stub.StreamObserver<org.project_kessel.api.inventory.v1beta1.resources.CreateK8sClusterResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getCreateK8sClusterMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void updateK8sCluster(org.project_kessel.api.inventory.v1beta1.resources.UpdateK8sClusterRequest request,
        io.grpc.stub.StreamObserver<org.project_kessel.api.inventory.v1beta1.resources.UpdateK8sClusterResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getUpdateK8sClusterMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void deleteK8sCluster(org.project_kessel.api.inventory.v1beta1.resources.DeleteK8sClusterRequest request,
        io.grpc.stub.StreamObserver<org.project_kessel.api.inventory.v1beta1.resources.DeleteK8sClusterResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getDeleteK8sClusterMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * A stub to allow clients to do synchronous rpc calls to service KesselK8sClusterService.
   */
  public static final class KesselK8sClusterServiceBlockingV2Stub
      extends io.grpc.stub.AbstractBlockingStub<KesselK8sClusterServiceBlockingV2Stub> {
    private KesselK8sClusterServiceBlockingV2Stub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected KesselK8sClusterServiceBlockingV2Stub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new KesselK8sClusterServiceBlockingV2Stub(channel, callOptions);
    }

    /**
     */
    public org.project_kessel.api.inventory.v1beta1.resources.CreateK8sClusterResponse createK8sCluster(org.project_kessel.api.inventory.v1beta1.resources.CreateK8sClusterRequest request) throws io.grpc.StatusException {
      return io.grpc.stub.ClientCalls.blockingV2UnaryCall(
          getChannel(), getCreateK8sClusterMethod(), getCallOptions(), request);
    }

    /**
     */
    public org.project_kessel.api.inventory.v1beta1.resources.UpdateK8sClusterResponse updateK8sCluster(org.project_kessel.api.inventory.v1beta1.resources.UpdateK8sClusterRequest request) throws io.grpc.StatusException {
      return io.grpc.stub.ClientCalls.blockingV2UnaryCall(
          getChannel(), getUpdateK8sClusterMethod(), getCallOptions(), request);
    }

    /**
     */
    public org.project_kessel.api.inventory.v1beta1.resources.DeleteK8sClusterResponse deleteK8sCluster(org.project_kessel.api.inventory.v1beta1.resources.DeleteK8sClusterRequest request) throws io.grpc.StatusException {
      return io.grpc.stub.ClientCalls.blockingV2UnaryCall(
          getChannel(), getDeleteK8sClusterMethod(), getCallOptions(), request);
    }
  }

  /**
   * A stub to allow clients to do limited synchronous rpc calls to service KesselK8sClusterService.
   */
  public static final class KesselK8sClusterServiceBlockingStub
      extends io.grpc.stub.AbstractBlockingStub<KesselK8sClusterServiceBlockingStub> {
    private KesselK8sClusterServiceBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected KesselK8sClusterServiceBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new KesselK8sClusterServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public org.project_kessel.api.inventory.v1beta1.resources.CreateK8sClusterResponse createK8sCluster(org.project_kessel.api.inventory.v1beta1.resources.CreateK8sClusterRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getCreateK8sClusterMethod(), getCallOptions(), request);
    }

    /**
     */
    public org.project_kessel.api.inventory.v1beta1.resources.UpdateK8sClusterResponse updateK8sCluster(org.project_kessel.api.inventory.v1beta1.resources.UpdateK8sClusterRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getUpdateK8sClusterMethod(), getCallOptions(), request);
    }

    /**
     */
    public org.project_kessel.api.inventory.v1beta1.resources.DeleteK8sClusterResponse deleteK8sCluster(org.project_kessel.api.inventory.v1beta1.resources.DeleteK8sClusterRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getDeleteK8sClusterMethod(), getCallOptions(), request);
    }
  }

  /**
   * A stub to allow clients to do ListenableFuture-style rpc calls to service KesselK8sClusterService.
   */
  public static final class KesselK8sClusterServiceFutureStub
      extends io.grpc.stub.AbstractFutureStub<KesselK8sClusterServiceFutureStub> {
    private KesselK8sClusterServiceFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected KesselK8sClusterServiceFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new KesselK8sClusterServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<org.project_kessel.api.inventory.v1beta1.resources.CreateK8sClusterResponse> createK8sCluster(
        org.project_kessel.api.inventory.v1beta1.resources.CreateK8sClusterRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getCreateK8sClusterMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<org.project_kessel.api.inventory.v1beta1.resources.UpdateK8sClusterResponse> updateK8sCluster(
        org.project_kessel.api.inventory.v1beta1.resources.UpdateK8sClusterRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getUpdateK8sClusterMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<org.project_kessel.api.inventory.v1beta1.resources.DeleteK8sClusterResponse> deleteK8sCluster(
        org.project_kessel.api.inventory.v1beta1.resources.DeleteK8sClusterRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getDeleteK8sClusterMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_CREATE_K8S_CLUSTER = 0;
  private static final int METHODID_UPDATE_K8S_CLUSTER = 1;
  private static final int METHODID_DELETE_K8S_CLUSTER = 2;

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
        case METHODID_CREATE_K8S_CLUSTER:
          serviceImpl.createK8sCluster((org.project_kessel.api.inventory.v1beta1.resources.CreateK8sClusterRequest) request,
              (io.grpc.stub.StreamObserver<org.project_kessel.api.inventory.v1beta1.resources.CreateK8sClusterResponse>) responseObserver);
          break;
        case METHODID_UPDATE_K8S_CLUSTER:
          serviceImpl.updateK8sCluster((org.project_kessel.api.inventory.v1beta1.resources.UpdateK8sClusterRequest) request,
              (io.grpc.stub.StreamObserver<org.project_kessel.api.inventory.v1beta1.resources.UpdateK8sClusterResponse>) responseObserver);
          break;
        case METHODID_DELETE_K8S_CLUSTER:
          serviceImpl.deleteK8sCluster((org.project_kessel.api.inventory.v1beta1.resources.DeleteK8sClusterRequest) request,
              (io.grpc.stub.StreamObserver<org.project_kessel.api.inventory.v1beta1.resources.DeleteK8sClusterResponse>) responseObserver);
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
          getCreateK8sClusterMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              org.project_kessel.api.inventory.v1beta1.resources.CreateK8sClusterRequest,
              org.project_kessel.api.inventory.v1beta1.resources.CreateK8sClusterResponse>(
                service, METHODID_CREATE_K8S_CLUSTER)))
        .addMethod(
          getUpdateK8sClusterMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              org.project_kessel.api.inventory.v1beta1.resources.UpdateK8sClusterRequest,
              org.project_kessel.api.inventory.v1beta1.resources.UpdateK8sClusterResponse>(
                service, METHODID_UPDATE_K8S_CLUSTER)))
        .addMethod(
          getDeleteK8sClusterMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              org.project_kessel.api.inventory.v1beta1.resources.DeleteK8sClusterRequest,
              org.project_kessel.api.inventory.v1beta1.resources.DeleteK8sClusterResponse>(
                service, METHODID_DELETE_K8S_CLUSTER)))
        .build();
  }

  private static abstract class KesselK8sClusterServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    KesselK8sClusterServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return org.project_kessel.api.inventory.v1beta1.resources.K8SClustersService.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("KesselK8sClusterService");
    }
  }

  private static final class KesselK8sClusterServiceFileDescriptorSupplier
      extends KesselK8sClusterServiceBaseDescriptorSupplier {
    KesselK8sClusterServiceFileDescriptorSupplier() {}
  }

  private static final class KesselK8sClusterServiceMethodDescriptorSupplier
      extends KesselK8sClusterServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final java.lang.String methodName;

    KesselK8sClusterServiceMethodDescriptorSupplier(java.lang.String methodName) {
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
      synchronized (KesselK8sClusterServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new KesselK8sClusterServiceFileDescriptorSupplier())
              .addMethod(getCreateK8sClusterMethod())
              .addMethod(getUpdateK8sClusterMethod())
              .addMethod(getDeleteK8sClusterMethod())
              .build();
        }
      }
    }
    return result;
  }
}
