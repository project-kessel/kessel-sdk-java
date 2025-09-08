package org.project_kessel.api.inventory.v1beta1.relationships;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 */
@io.grpc.stub.annotations.GrpcGenerated
public final class KesselK8SPolicyIsPropagatedToK8SClusterServiceGrpc {

  private KesselK8SPolicyIsPropagatedToK8SClusterServiceGrpc() {}

  public static final java.lang.String SERVICE_NAME = "kessel.inventory.v1beta1.relationships.KesselK8SPolicyIsPropagatedToK8SClusterService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<org.project_kessel.api.inventory.v1beta1.relationships.CreateK8SPolicyIsPropagatedToK8SClusterRequest,
      org.project_kessel.api.inventory.v1beta1.relationships.CreateK8SPolicyIsPropagatedToK8SClusterResponse> getCreateK8SPolicyIsPropagatedToK8SClusterMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "CreateK8SPolicyIsPropagatedToK8SCluster",
      requestType = org.project_kessel.api.inventory.v1beta1.relationships.CreateK8SPolicyIsPropagatedToK8SClusterRequest.class,
      responseType = org.project_kessel.api.inventory.v1beta1.relationships.CreateK8SPolicyIsPropagatedToK8SClusterResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<org.project_kessel.api.inventory.v1beta1.relationships.CreateK8SPolicyIsPropagatedToK8SClusterRequest,
      org.project_kessel.api.inventory.v1beta1.relationships.CreateK8SPolicyIsPropagatedToK8SClusterResponse> getCreateK8SPolicyIsPropagatedToK8SClusterMethod() {
    io.grpc.MethodDescriptor<org.project_kessel.api.inventory.v1beta1.relationships.CreateK8SPolicyIsPropagatedToK8SClusterRequest, org.project_kessel.api.inventory.v1beta1.relationships.CreateK8SPolicyIsPropagatedToK8SClusterResponse> getCreateK8SPolicyIsPropagatedToK8SClusterMethod;
    if ((getCreateK8SPolicyIsPropagatedToK8SClusterMethod = KesselK8SPolicyIsPropagatedToK8SClusterServiceGrpc.getCreateK8SPolicyIsPropagatedToK8SClusterMethod) == null) {
      synchronized (KesselK8SPolicyIsPropagatedToK8SClusterServiceGrpc.class) {
        if ((getCreateK8SPolicyIsPropagatedToK8SClusterMethod = KesselK8SPolicyIsPropagatedToK8SClusterServiceGrpc.getCreateK8SPolicyIsPropagatedToK8SClusterMethod) == null) {
          KesselK8SPolicyIsPropagatedToK8SClusterServiceGrpc.getCreateK8SPolicyIsPropagatedToK8SClusterMethod = getCreateK8SPolicyIsPropagatedToK8SClusterMethod =
              io.grpc.MethodDescriptor.<org.project_kessel.api.inventory.v1beta1.relationships.CreateK8SPolicyIsPropagatedToK8SClusterRequest, org.project_kessel.api.inventory.v1beta1.relationships.CreateK8SPolicyIsPropagatedToK8SClusterResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "CreateK8SPolicyIsPropagatedToK8SCluster"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  org.project_kessel.api.inventory.v1beta1.relationships.CreateK8SPolicyIsPropagatedToK8SClusterRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  org.project_kessel.api.inventory.v1beta1.relationships.CreateK8SPolicyIsPropagatedToK8SClusterResponse.getDefaultInstance()))
              .setSchemaDescriptor(new KesselK8SPolicyIsPropagatedToK8SClusterServiceMethodDescriptorSupplier("CreateK8SPolicyIsPropagatedToK8SCluster"))
              .build();
        }
      }
    }
    return getCreateK8SPolicyIsPropagatedToK8SClusterMethod;
  }

  private static volatile io.grpc.MethodDescriptor<org.project_kessel.api.inventory.v1beta1.relationships.UpdateK8SPolicyIsPropagatedToK8SClusterRequest,
      org.project_kessel.api.inventory.v1beta1.relationships.UpdateK8SPolicyIsPropagatedToK8SClusterResponse> getUpdateK8SPolicyIsPropagatedToK8SClusterMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "UpdateK8SPolicyIsPropagatedToK8SCluster",
      requestType = org.project_kessel.api.inventory.v1beta1.relationships.UpdateK8SPolicyIsPropagatedToK8SClusterRequest.class,
      responseType = org.project_kessel.api.inventory.v1beta1.relationships.UpdateK8SPolicyIsPropagatedToK8SClusterResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<org.project_kessel.api.inventory.v1beta1.relationships.UpdateK8SPolicyIsPropagatedToK8SClusterRequest,
      org.project_kessel.api.inventory.v1beta1.relationships.UpdateK8SPolicyIsPropagatedToK8SClusterResponse> getUpdateK8SPolicyIsPropagatedToK8SClusterMethod() {
    io.grpc.MethodDescriptor<org.project_kessel.api.inventory.v1beta1.relationships.UpdateK8SPolicyIsPropagatedToK8SClusterRequest, org.project_kessel.api.inventory.v1beta1.relationships.UpdateK8SPolicyIsPropagatedToK8SClusterResponse> getUpdateK8SPolicyIsPropagatedToK8SClusterMethod;
    if ((getUpdateK8SPolicyIsPropagatedToK8SClusterMethod = KesselK8SPolicyIsPropagatedToK8SClusterServiceGrpc.getUpdateK8SPolicyIsPropagatedToK8SClusterMethod) == null) {
      synchronized (KesselK8SPolicyIsPropagatedToK8SClusterServiceGrpc.class) {
        if ((getUpdateK8SPolicyIsPropagatedToK8SClusterMethod = KesselK8SPolicyIsPropagatedToK8SClusterServiceGrpc.getUpdateK8SPolicyIsPropagatedToK8SClusterMethod) == null) {
          KesselK8SPolicyIsPropagatedToK8SClusterServiceGrpc.getUpdateK8SPolicyIsPropagatedToK8SClusterMethod = getUpdateK8SPolicyIsPropagatedToK8SClusterMethod =
              io.grpc.MethodDescriptor.<org.project_kessel.api.inventory.v1beta1.relationships.UpdateK8SPolicyIsPropagatedToK8SClusterRequest, org.project_kessel.api.inventory.v1beta1.relationships.UpdateK8SPolicyIsPropagatedToK8SClusterResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "UpdateK8SPolicyIsPropagatedToK8SCluster"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  org.project_kessel.api.inventory.v1beta1.relationships.UpdateK8SPolicyIsPropagatedToK8SClusterRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  org.project_kessel.api.inventory.v1beta1.relationships.UpdateK8SPolicyIsPropagatedToK8SClusterResponse.getDefaultInstance()))
              .setSchemaDescriptor(new KesselK8SPolicyIsPropagatedToK8SClusterServiceMethodDescriptorSupplier("UpdateK8SPolicyIsPropagatedToK8SCluster"))
              .build();
        }
      }
    }
    return getUpdateK8SPolicyIsPropagatedToK8SClusterMethod;
  }

  private static volatile io.grpc.MethodDescriptor<org.project_kessel.api.inventory.v1beta1.relationships.DeleteK8SPolicyIsPropagatedToK8SClusterRequest,
      org.project_kessel.api.inventory.v1beta1.relationships.DeleteK8SPolicyIsPropagatedToK8SClusterResponse> getDeleteK8SPolicyIsPropagatedToK8SClusterMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "DeleteK8SPolicyIsPropagatedToK8SCluster",
      requestType = org.project_kessel.api.inventory.v1beta1.relationships.DeleteK8SPolicyIsPropagatedToK8SClusterRequest.class,
      responseType = org.project_kessel.api.inventory.v1beta1.relationships.DeleteK8SPolicyIsPropagatedToK8SClusterResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<org.project_kessel.api.inventory.v1beta1.relationships.DeleteK8SPolicyIsPropagatedToK8SClusterRequest,
      org.project_kessel.api.inventory.v1beta1.relationships.DeleteK8SPolicyIsPropagatedToK8SClusterResponse> getDeleteK8SPolicyIsPropagatedToK8SClusterMethod() {
    io.grpc.MethodDescriptor<org.project_kessel.api.inventory.v1beta1.relationships.DeleteK8SPolicyIsPropagatedToK8SClusterRequest, org.project_kessel.api.inventory.v1beta1.relationships.DeleteK8SPolicyIsPropagatedToK8SClusterResponse> getDeleteK8SPolicyIsPropagatedToK8SClusterMethod;
    if ((getDeleteK8SPolicyIsPropagatedToK8SClusterMethod = KesselK8SPolicyIsPropagatedToK8SClusterServiceGrpc.getDeleteK8SPolicyIsPropagatedToK8SClusterMethod) == null) {
      synchronized (KesselK8SPolicyIsPropagatedToK8SClusterServiceGrpc.class) {
        if ((getDeleteK8SPolicyIsPropagatedToK8SClusterMethod = KesselK8SPolicyIsPropagatedToK8SClusterServiceGrpc.getDeleteK8SPolicyIsPropagatedToK8SClusterMethod) == null) {
          KesselK8SPolicyIsPropagatedToK8SClusterServiceGrpc.getDeleteK8SPolicyIsPropagatedToK8SClusterMethod = getDeleteK8SPolicyIsPropagatedToK8SClusterMethod =
              io.grpc.MethodDescriptor.<org.project_kessel.api.inventory.v1beta1.relationships.DeleteK8SPolicyIsPropagatedToK8SClusterRequest, org.project_kessel.api.inventory.v1beta1.relationships.DeleteK8SPolicyIsPropagatedToK8SClusterResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "DeleteK8SPolicyIsPropagatedToK8SCluster"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  org.project_kessel.api.inventory.v1beta1.relationships.DeleteK8SPolicyIsPropagatedToK8SClusterRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  org.project_kessel.api.inventory.v1beta1.relationships.DeleteK8SPolicyIsPropagatedToK8SClusterResponse.getDefaultInstance()))
              .setSchemaDescriptor(new KesselK8SPolicyIsPropagatedToK8SClusterServiceMethodDescriptorSupplier("DeleteK8SPolicyIsPropagatedToK8SCluster"))
              .build();
        }
      }
    }
    return getDeleteK8SPolicyIsPropagatedToK8SClusterMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static KesselK8SPolicyIsPropagatedToK8SClusterServiceStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<KesselK8SPolicyIsPropagatedToK8SClusterServiceStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<KesselK8SPolicyIsPropagatedToK8SClusterServiceStub>() {
        @java.lang.Override
        public KesselK8SPolicyIsPropagatedToK8SClusterServiceStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new KesselK8SPolicyIsPropagatedToK8SClusterServiceStub(channel, callOptions);
        }
      };
    return KesselK8SPolicyIsPropagatedToK8SClusterServiceStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports all types of calls on the service
   */
  public static KesselK8SPolicyIsPropagatedToK8SClusterServiceBlockingV2Stub newBlockingV2Stub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<KesselK8SPolicyIsPropagatedToK8SClusterServiceBlockingV2Stub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<KesselK8SPolicyIsPropagatedToK8SClusterServiceBlockingV2Stub>() {
        @java.lang.Override
        public KesselK8SPolicyIsPropagatedToK8SClusterServiceBlockingV2Stub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new KesselK8SPolicyIsPropagatedToK8SClusterServiceBlockingV2Stub(channel, callOptions);
        }
      };
    return KesselK8SPolicyIsPropagatedToK8SClusterServiceBlockingV2Stub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static KesselK8SPolicyIsPropagatedToK8SClusterServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<KesselK8SPolicyIsPropagatedToK8SClusterServiceBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<KesselK8SPolicyIsPropagatedToK8SClusterServiceBlockingStub>() {
        @java.lang.Override
        public KesselK8SPolicyIsPropagatedToK8SClusterServiceBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new KesselK8SPolicyIsPropagatedToK8SClusterServiceBlockingStub(channel, callOptions);
        }
      };
    return KesselK8SPolicyIsPropagatedToK8SClusterServiceBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static KesselK8SPolicyIsPropagatedToK8SClusterServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<KesselK8SPolicyIsPropagatedToK8SClusterServiceFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<KesselK8SPolicyIsPropagatedToK8SClusterServiceFutureStub>() {
        @java.lang.Override
        public KesselK8SPolicyIsPropagatedToK8SClusterServiceFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new KesselK8SPolicyIsPropagatedToK8SClusterServiceFutureStub(channel, callOptions);
        }
      };
    return KesselK8SPolicyIsPropagatedToK8SClusterServiceFutureStub.newStub(factory, channel);
  }

  /**
   */
  public interface AsyncService {

    /**
     */
    default void createK8SPolicyIsPropagatedToK8SCluster(org.project_kessel.api.inventory.v1beta1.relationships.CreateK8SPolicyIsPropagatedToK8SClusterRequest request,
        io.grpc.stub.StreamObserver<org.project_kessel.api.inventory.v1beta1.relationships.CreateK8SPolicyIsPropagatedToK8SClusterResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getCreateK8SPolicyIsPropagatedToK8SClusterMethod(), responseObserver);
    }

    /**
     */
    default void updateK8SPolicyIsPropagatedToK8SCluster(org.project_kessel.api.inventory.v1beta1.relationships.UpdateK8SPolicyIsPropagatedToK8SClusterRequest request,
        io.grpc.stub.StreamObserver<org.project_kessel.api.inventory.v1beta1.relationships.UpdateK8SPolicyIsPropagatedToK8SClusterResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getUpdateK8SPolicyIsPropagatedToK8SClusterMethod(), responseObserver);
    }

    /**
     */
    default void deleteK8SPolicyIsPropagatedToK8SCluster(org.project_kessel.api.inventory.v1beta1.relationships.DeleteK8SPolicyIsPropagatedToK8SClusterRequest request,
        io.grpc.stub.StreamObserver<org.project_kessel.api.inventory.v1beta1.relationships.DeleteK8SPolicyIsPropagatedToK8SClusterResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getDeleteK8SPolicyIsPropagatedToK8SClusterMethod(), responseObserver);
    }
  }

  /**
   * Base class for the server implementation of the service KesselK8SPolicyIsPropagatedToK8SClusterService.
   */
  public static abstract class KesselK8SPolicyIsPropagatedToK8SClusterServiceImplBase
      implements io.grpc.BindableService, AsyncService {

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return KesselK8SPolicyIsPropagatedToK8SClusterServiceGrpc.bindService(this);
    }
  }

  /**
   * A stub to allow clients to do asynchronous rpc calls to service KesselK8SPolicyIsPropagatedToK8SClusterService.
   */
  public static final class KesselK8SPolicyIsPropagatedToK8SClusterServiceStub
      extends io.grpc.stub.AbstractAsyncStub<KesselK8SPolicyIsPropagatedToK8SClusterServiceStub> {
    private KesselK8SPolicyIsPropagatedToK8SClusterServiceStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected KesselK8SPolicyIsPropagatedToK8SClusterServiceStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new KesselK8SPolicyIsPropagatedToK8SClusterServiceStub(channel, callOptions);
    }

    /**
     */
    public void createK8SPolicyIsPropagatedToK8SCluster(org.project_kessel.api.inventory.v1beta1.relationships.CreateK8SPolicyIsPropagatedToK8SClusterRequest request,
        io.grpc.stub.StreamObserver<org.project_kessel.api.inventory.v1beta1.relationships.CreateK8SPolicyIsPropagatedToK8SClusterResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getCreateK8SPolicyIsPropagatedToK8SClusterMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void updateK8SPolicyIsPropagatedToK8SCluster(org.project_kessel.api.inventory.v1beta1.relationships.UpdateK8SPolicyIsPropagatedToK8SClusterRequest request,
        io.grpc.stub.StreamObserver<org.project_kessel.api.inventory.v1beta1.relationships.UpdateK8SPolicyIsPropagatedToK8SClusterResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getUpdateK8SPolicyIsPropagatedToK8SClusterMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void deleteK8SPolicyIsPropagatedToK8SCluster(org.project_kessel.api.inventory.v1beta1.relationships.DeleteK8SPolicyIsPropagatedToK8SClusterRequest request,
        io.grpc.stub.StreamObserver<org.project_kessel.api.inventory.v1beta1.relationships.DeleteK8SPolicyIsPropagatedToK8SClusterResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getDeleteK8SPolicyIsPropagatedToK8SClusterMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * A stub to allow clients to do synchronous rpc calls to service KesselK8SPolicyIsPropagatedToK8SClusterService.
   */
  public static final class KesselK8SPolicyIsPropagatedToK8SClusterServiceBlockingV2Stub
      extends io.grpc.stub.AbstractBlockingStub<KesselK8SPolicyIsPropagatedToK8SClusterServiceBlockingV2Stub> {
    private KesselK8SPolicyIsPropagatedToK8SClusterServiceBlockingV2Stub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected KesselK8SPolicyIsPropagatedToK8SClusterServiceBlockingV2Stub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new KesselK8SPolicyIsPropagatedToK8SClusterServiceBlockingV2Stub(channel, callOptions);
    }

    /**
     */
    public org.project_kessel.api.inventory.v1beta1.relationships.CreateK8SPolicyIsPropagatedToK8SClusterResponse createK8SPolicyIsPropagatedToK8SCluster(org.project_kessel.api.inventory.v1beta1.relationships.CreateK8SPolicyIsPropagatedToK8SClusterRequest request) throws io.grpc.StatusException {
      return io.grpc.stub.ClientCalls.blockingV2UnaryCall(
          getChannel(), getCreateK8SPolicyIsPropagatedToK8SClusterMethod(), getCallOptions(), request);
    }

    /**
     */
    public org.project_kessel.api.inventory.v1beta1.relationships.UpdateK8SPolicyIsPropagatedToK8SClusterResponse updateK8SPolicyIsPropagatedToK8SCluster(org.project_kessel.api.inventory.v1beta1.relationships.UpdateK8SPolicyIsPropagatedToK8SClusterRequest request) throws io.grpc.StatusException {
      return io.grpc.stub.ClientCalls.blockingV2UnaryCall(
          getChannel(), getUpdateK8SPolicyIsPropagatedToK8SClusterMethod(), getCallOptions(), request);
    }

    /**
     */
    public org.project_kessel.api.inventory.v1beta1.relationships.DeleteK8SPolicyIsPropagatedToK8SClusterResponse deleteK8SPolicyIsPropagatedToK8SCluster(org.project_kessel.api.inventory.v1beta1.relationships.DeleteK8SPolicyIsPropagatedToK8SClusterRequest request) throws io.grpc.StatusException {
      return io.grpc.stub.ClientCalls.blockingV2UnaryCall(
          getChannel(), getDeleteK8SPolicyIsPropagatedToK8SClusterMethod(), getCallOptions(), request);
    }
  }

  /**
   * A stub to allow clients to do limited synchronous rpc calls to service KesselK8SPolicyIsPropagatedToK8SClusterService.
   */
  public static final class KesselK8SPolicyIsPropagatedToK8SClusterServiceBlockingStub
      extends io.grpc.stub.AbstractBlockingStub<KesselK8SPolicyIsPropagatedToK8SClusterServiceBlockingStub> {
    private KesselK8SPolicyIsPropagatedToK8SClusterServiceBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected KesselK8SPolicyIsPropagatedToK8SClusterServiceBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new KesselK8SPolicyIsPropagatedToK8SClusterServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public org.project_kessel.api.inventory.v1beta1.relationships.CreateK8SPolicyIsPropagatedToK8SClusterResponse createK8SPolicyIsPropagatedToK8SCluster(org.project_kessel.api.inventory.v1beta1.relationships.CreateK8SPolicyIsPropagatedToK8SClusterRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getCreateK8SPolicyIsPropagatedToK8SClusterMethod(), getCallOptions(), request);
    }

    /**
     */
    public org.project_kessel.api.inventory.v1beta1.relationships.UpdateK8SPolicyIsPropagatedToK8SClusterResponse updateK8SPolicyIsPropagatedToK8SCluster(org.project_kessel.api.inventory.v1beta1.relationships.UpdateK8SPolicyIsPropagatedToK8SClusterRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getUpdateK8SPolicyIsPropagatedToK8SClusterMethod(), getCallOptions(), request);
    }

    /**
     */
    public org.project_kessel.api.inventory.v1beta1.relationships.DeleteK8SPolicyIsPropagatedToK8SClusterResponse deleteK8SPolicyIsPropagatedToK8SCluster(org.project_kessel.api.inventory.v1beta1.relationships.DeleteK8SPolicyIsPropagatedToK8SClusterRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getDeleteK8SPolicyIsPropagatedToK8SClusterMethod(), getCallOptions(), request);
    }
  }

  /**
   * A stub to allow clients to do ListenableFuture-style rpc calls to service KesselK8SPolicyIsPropagatedToK8SClusterService.
   */
  public static final class KesselK8SPolicyIsPropagatedToK8SClusterServiceFutureStub
      extends io.grpc.stub.AbstractFutureStub<KesselK8SPolicyIsPropagatedToK8SClusterServiceFutureStub> {
    private KesselK8SPolicyIsPropagatedToK8SClusterServiceFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected KesselK8SPolicyIsPropagatedToK8SClusterServiceFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new KesselK8SPolicyIsPropagatedToK8SClusterServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<org.project_kessel.api.inventory.v1beta1.relationships.CreateK8SPolicyIsPropagatedToK8SClusterResponse> createK8SPolicyIsPropagatedToK8SCluster(
        org.project_kessel.api.inventory.v1beta1.relationships.CreateK8SPolicyIsPropagatedToK8SClusterRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getCreateK8SPolicyIsPropagatedToK8SClusterMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<org.project_kessel.api.inventory.v1beta1.relationships.UpdateK8SPolicyIsPropagatedToK8SClusterResponse> updateK8SPolicyIsPropagatedToK8SCluster(
        org.project_kessel.api.inventory.v1beta1.relationships.UpdateK8SPolicyIsPropagatedToK8SClusterRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getUpdateK8SPolicyIsPropagatedToK8SClusterMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<org.project_kessel.api.inventory.v1beta1.relationships.DeleteK8SPolicyIsPropagatedToK8SClusterResponse> deleteK8SPolicyIsPropagatedToK8SCluster(
        org.project_kessel.api.inventory.v1beta1.relationships.DeleteK8SPolicyIsPropagatedToK8SClusterRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getDeleteK8SPolicyIsPropagatedToK8SClusterMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_CREATE_K8SPOLICY_IS_PROPAGATED_TO_K8SCLUSTER = 0;
  private static final int METHODID_UPDATE_K8SPOLICY_IS_PROPAGATED_TO_K8SCLUSTER = 1;
  private static final int METHODID_DELETE_K8SPOLICY_IS_PROPAGATED_TO_K8SCLUSTER = 2;

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
        case METHODID_CREATE_K8SPOLICY_IS_PROPAGATED_TO_K8SCLUSTER:
          serviceImpl.createK8SPolicyIsPropagatedToK8SCluster((org.project_kessel.api.inventory.v1beta1.relationships.CreateK8SPolicyIsPropagatedToK8SClusterRequest) request,
              (io.grpc.stub.StreamObserver<org.project_kessel.api.inventory.v1beta1.relationships.CreateK8SPolicyIsPropagatedToK8SClusterResponse>) responseObserver);
          break;
        case METHODID_UPDATE_K8SPOLICY_IS_PROPAGATED_TO_K8SCLUSTER:
          serviceImpl.updateK8SPolicyIsPropagatedToK8SCluster((org.project_kessel.api.inventory.v1beta1.relationships.UpdateK8SPolicyIsPropagatedToK8SClusterRequest) request,
              (io.grpc.stub.StreamObserver<org.project_kessel.api.inventory.v1beta1.relationships.UpdateK8SPolicyIsPropagatedToK8SClusterResponse>) responseObserver);
          break;
        case METHODID_DELETE_K8SPOLICY_IS_PROPAGATED_TO_K8SCLUSTER:
          serviceImpl.deleteK8SPolicyIsPropagatedToK8SCluster((org.project_kessel.api.inventory.v1beta1.relationships.DeleteK8SPolicyIsPropagatedToK8SClusterRequest) request,
              (io.grpc.stub.StreamObserver<org.project_kessel.api.inventory.v1beta1.relationships.DeleteK8SPolicyIsPropagatedToK8SClusterResponse>) responseObserver);
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
          getCreateK8SPolicyIsPropagatedToK8SClusterMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              org.project_kessel.api.inventory.v1beta1.relationships.CreateK8SPolicyIsPropagatedToK8SClusterRequest,
              org.project_kessel.api.inventory.v1beta1.relationships.CreateK8SPolicyIsPropagatedToK8SClusterResponse>(
                service, METHODID_CREATE_K8SPOLICY_IS_PROPAGATED_TO_K8SCLUSTER)))
        .addMethod(
          getUpdateK8SPolicyIsPropagatedToK8SClusterMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              org.project_kessel.api.inventory.v1beta1.relationships.UpdateK8SPolicyIsPropagatedToK8SClusterRequest,
              org.project_kessel.api.inventory.v1beta1.relationships.UpdateK8SPolicyIsPropagatedToK8SClusterResponse>(
                service, METHODID_UPDATE_K8SPOLICY_IS_PROPAGATED_TO_K8SCLUSTER)))
        .addMethod(
          getDeleteK8SPolicyIsPropagatedToK8SClusterMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              org.project_kessel.api.inventory.v1beta1.relationships.DeleteK8SPolicyIsPropagatedToK8SClusterRequest,
              org.project_kessel.api.inventory.v1beta1.relationships.DeleteK8SPolicyIsPropagatedToK8SClusterResponse>(
                service, METHODID_DELETE_K8SPOLICY_IS_PROPAGATED_TO_K8SCLUSTER)))
        .build();
  }

  private static abstract class KesselK8SPolicyIsPropagatedToK8SClusterServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    KesselK8SPolicyIsPropagatedToK8SClusterServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return org.project_kessel.api.inventory.v1beta1.relationships.K8SpolicyIspropagatedtoK8SclusterService.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("KesselK8SPolicyIsPropagatedToK8SClusterService");
    }
  }

  private static final class KesselK8SPolicyIsPropagatedToK8SClusterServiceFileDescriptorSupplier
      extends KesselK8SPolicyIsPropagatedToK8SClusterServiceBaseDescriptorSupplier {
    KesselK8SPolicyIsPropagatedToK8SClusterServiceFileDescriptorSupplier() {}
  }

  private static final class KesselK8SPolicyIsPropagatedToK8SClusterServiceMethodDescriptorSupplier
      extends KesselK8SPolicyIsPropagatedToK8SClusterServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final java.lang.String methodName;

    KesselK8SPolicyIsPropagatedToK8SClusterServiceMethodDescriptorSupplier(java.lang.String methodName) {
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
      synchronized (KesselK8SPolicyIsPropagatedToK8SClusterServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new KesselK8SPolicyIsPropagatedToK8SClusterServiceFileDescriptorSupplier())
              .addMethod(getCreateK8SPolicyIsPropagatedToK8SClusterMethod())
              .addMethod(getUpdateK8SPolicyIsPropagatedToK8SClusterMethod())
              .addMethod(getDeleteK8SPolicyIsPropagatedToK8SClusterMethod())
              .build();
        }
      }
    }
    return result;
  }
}
