package org.project_kessel.api.inventory.v1beta1.resources;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 */
@io.grpc.stub.annotations.GrpcGenerated
public final class KesselK8sPolicyServiceGrpc {

  private KesselK8sPolicyServiceGrpc() {}

  public static final java.lang.String SERVICE_NAME = "kessel.inventory.v1beta1.resources.KesselK8sPolicyService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<org.project_kessel.api.inventory.v1beta1.resources.CreateK8sPolicyRequest,
      org.project_kessel.api.inventory.v1beta1.resources.CreateK8sPolicyResponse> getCreateK8sPolicyMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "CreateK8sPolicy",
      requestType = org.project_kessel.api.inventory.v1beta1.resources.CreateK8sPolicyRequest.class,
      responseType = org.project_kessel.api.inventory.v1beta1.resources.CreateK8sPolicyResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<org.project_kessel.api.inventory.v1beta1.resources.CreateK8sPolicyRequest,
      org.project_kessel.api.inventory.v1beta1.resources.CreateK8sPolicyResponse> getCreateK8sPolicyMethod() {
    io.grpc.MethodDescriptor<org.project_kessel.api.inventory.v1beta1.resources.CreateK8sPolicyRequest, org.project_kessel.api.inventory.v1beta1.resources.CreateK8sPolicyResponse> getCreateK8sPolicyMethod;
    if ((getCreateK8sPolicyMethod = KesselK8sPolicyServiceGrpc.getCreateK8sPolicyMethod) == null) {
      synchronized (KesselK8sPolicyServiceGrpc.class) {
        if ((getCreateK8sPolicyMethod = KesselK8sPolicyServiceGrpc.getCreateK8sPolicyMethod) == null) {
          KesselK8sPolicyServiceGrpc.getCreateK8sPolicyMethod = getCreateK8sPolicyMethod =
              io.grpc.MethodDescriptor.<org.project_kessel.api.inventory.v1beta1.resources.CreateK8sPolicyRequest, org.project_kessel.api.inventory.v1beta1.resources.CreateK8sPolicyResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "CreateK8sPolicy"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  org.project_kessel.api.inventory.v1beta1.resources.CreateK8sPolicyRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  org.project_kessel.api.inventory.v1beta1.resources.CreateK8sPolicyResponse.getDefaultInstance()))
              .setSchemaDescriptor(new KesselK8sPolicyServiceMethodDescriptorSupplier("CreateK8sPolicy"))
              .build();
        }
      }
    }
    return getCreateK8sPolicyMethod;
  }

  private static volatile io.grpc.MethodDescriptor<org.project_kessel.api.inventory.v1beta1.resources.UpdateK8sPolicyRequest,
      org.project_kessel.api.inventory.v1beta1.resources.UpdateK8sPolicyResponse> getUpdateK8sPolicyMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "UpdateK8sPolicy",
      requestType = org.project_kessel.api.inventory.v1beta1.resources.UpdateK8sPolicyRequest.class,
      responseType = org.project_kessel.api.inventory.v1beta1.resources.UpdateK8sPolicyResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<org.project_kessel.api.inventory.v1beta1.resources.UpdateK8sPolicyRequest,
      org.project_kessel.api.inventory.v1beta1.resources.UpdateK8sPolicyResponse> getUpdateK8sPolicyMethod() {
    io.grpc.MethodDescriptor<org.project_kessel.api.inventory.v1beta1.resources.UpdateK8sPolicyRequest, org.project_kessel.api.inventory.v1beta1.resources.UpdateK8sPolicyResponse> getUpdateK8sPolicyMethod;
    if ((getUpdateK8sPolicyMethod = KesselK8sPolicyServiceGrpc.getUpdateK8sPolicyMethod) == null) {
      synchronized (KesselK8sPolicyServiceGrpc.class) {
        if ((getUpdateK8sPolicyMethod = KesselK8sPolicyServiceGrpc.getUpdateK8sPolicyMethod) == null) {
          KesselK8sPolicyServiceGrpc.getUpdateK8sPolicyMethod = getUpdateK8sPolicyMethod =
              io.grpc.MethodDescriptor.<org.project_kessel.api.inventory.v1beta1.resources.UpdateK8sPolicyRequest, org.project_kessel.api.inventory.v1beta1.resources.UpdateK8sPolicyResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "UpdateK8sPolicy"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  org.project_kessel.api.inventory.v1beta1.resources.UpdateK8sPolicyRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  org.project_kessel.api.inventory.v1beta1.resources.UpdateK8sPolicyResponse.getDefaultInstance()))
              .setSchemaDescriptor(new KesselK8sPolicyServiceMethodDescriptorSupplier("UpdateK8sPolicy"))
              .build();
        }
      }
    }
    return getUpdateK8sPolicyMethod;
  }

  private static volatile io.grpc.MethodDescriptor<org.project_kessel.api.inventory.v1beta1.resources.DeleteK8sPolicyRequest,
      org.project_kessel.api.inventory.v1beta1.resources.DeleteK8sPolicyResponse> getDeleteK8sPolicyMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "DeleteK8sPolicy",
      requestType = org.project_kessel.api.inventory.v1beta1.resources.DeleteK8sPolicyRequest.class,
      responseType = org.project_kessel.api.inventory.v1beta1.resources.DeleteK8sPolicyResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<org.project_kessel.api.inventory.v1beta1.resources.DeleteK8sPolicyRequest,
      org.project_kessel.api.inventory.v1beta1.resources.DeleteK8sPolicyResponse> getDeleteK8sPolicyMethod() {
    io.grpc.MethodDescriptor<org.project_kessel.api.inventory.v1beta1.resources.DeleteK8sPolicyRequest, org.project_kessel.api.inventory.v1beta1.resources.DeleteK8sPolicyResponse> getDeleteK8sPolicyMethod;
    if ((getDeleteK8sPolicyMethod = KesselK8sPolicyServiceGrpc.getDeleteK8sPolicyMethod) == null) {
      synchronized (KesselK8sPolicyServiceGrpc.class) {
        if ((getDeleteK8sPolicyMethod = KesselK8sPolicyServiceGrpc.getDeleteK8sPolicyMethod) == null) {
          KesselK8sPolicyServiceGrpc.getDeleteK8sPolicyMethod = getDeleteK8sPolicyMethod =
              io.grpc.MethodDescriptor.<org.project_kessel.api.inventory.v1beta1.resources.DeleteK8sPolicyRequest, org.project_kessel.api.inventory.v1beta1.resources.DeleteK8sPolicyResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "DeleteK8sPolicy"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  org.project_kessel.api.inventory.v1beta1.resources.DeleteK8sPolicyRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  org.project_kessel.api.inventory.v1beta1.resources.DeleteK8sPolicyResponse.getDefaultInstance()))
              .setSchemaDescriptor(new KesselK8sPolicyServiceMethodDescriptorSupplier("DeleteK8sPolicy"))
              .build();
        }
      }
    }
    return getDeleteK8sPolicyMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static KesselK8sPolicyServiceStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<KesselK8sPolicyServiceStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<KesselK8sPolicyServiceStub>() {
        @java.lang.Override
        public KesselK8sPolicyServiceStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new KesselK8sPolicyServiceStub(channel, callOptions);
        }
      };
    return KesselK8sPolicyServiceStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports all types of calls on the service
   */
  public static KesselK8sPolicyServiceBlockingV2Stub newBlockingV2Stub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<KesselK8sPolicyServiceBlockingV2Stub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<KesselK8sPolicyServiceBlockingV2Stub>() {
        @java.lang.Override
        public KesselK8sPolicyServiceBlockingV2Stub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new KesselK8sPolicyServiceBlockingV2Stub(channel, callOptions);
        }
      };
    return KesselK8sPolicyServiceBlockingV2Stub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static KesselK8sPolicyServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<KesselK8sPolicyServiceBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<KesselK8sPolicyServiceBlockingStub>() {
        @java.lang.Override
        public KesselK8sPolicyServiceBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new KesselK8sPolicyServiceBlockingStub(channel, callOptions);
        }
      };
    return KesselK8sPolicyServiceBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static KesselK8sPolicyServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<KesselK8sPolicyServiceFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<KesselK8sPolicyServiceFutureStub>() {
        @java.lang.Override
        public KesselK8sPolicyServiceFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new KesselK8sPolicyServiceFutureStub(channel, callOptions);
        }
      };
    return KesselK8sPolicyServiceFutureStub.newStub(factory, channel);
  }

  /**
   */
  public interface AsyncService {

    /**
     */
    default void createK8sPolicy(org.project_kessel.api.inventory.v1beta1.resources.CreateK8sPolicyRequest request,
        io.grpc.stub.StreamObserver<org.project_kessel.api.inventory.v1beta1.resources.CreateK8sPolicyResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getCreateK8sPolicyMethod(), responseObserver);
    }

    /**
     */
    default void updateK8sPolicy(org.project_kessel.api.inventory.v1beta1.resources.UpdateK8sPolicyRequest request,
        io.grpc.stub.StreamObserver<org.project_kessel.api.inventory.v1beta1.resources.UpdateK8sPolicyResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getUpdateK8sPolicyMethod(), responseObserver);
    }

    /**
     */
    default void deleteK8sPolicy(org.project_kessel.api.inventory.v1beta1.resources.DeleteK8sPolicyRequest request,
        io.grpc.stub.StreamObserver<org.project_kessel.api.inventory.v1beta1.resources.DeleteK8sPolicyResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getDeleteK8sPolicyMethod(), responseObserver);
    }
  }

  /**
   * Base class for the server implementation of the service KesselK8sPolicyService.
   */
  public static abstract class KesselK8sPolicyServiceImplBase
      implements io.grpc.BindableService, AsyncService {

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return KesselK8sPolicyServiceGrpc.bindService(this);
    }
  }

  /**
   * A stub to allow clients to do asynchronous rpc calls to service KesselK8sPolicyService.
   */
  public static final class KesselK8sPolicyServiceStub
      extends io.grpc.stub.AbstractAsyncStub<KesselK8sPolicyServiceStub> {
    private KesselK8sPolicyServiceStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected KesselK8sPolicyServiceStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new KesselK8sPolicyServiceStub(channel, callOptions);
    }

    /**
     */
    public void createK8sPolicy(org.project_kessel.api.inventory.v1beta1.resources.CreateK8sPolicyRequest request,
        io.grpc.stub.StreamObserver<org.project_kessel.api.inventory.v1beta1.resources.CreateK8sPolicyResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getCreateK8sPolicyMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void updateK8sPolicy(org.project_kessel.api.inventory.v1beta1.resources.UpdateK8sPolicyRequest request,
        io.grpc.stub.StreamObserver<org.project_kessel.api.inventory.v1beta1.resources.UpdateK8sPolicyResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getUpdateK8sPolicyMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void deleteK8sPolicy(org.project_kessel.api.inventory.v1beta1.resources.DeleteK8sPolicyRequest request,
        io.grpc.stub.StreamObserver<org.project_kessel.api.inventory.v1beta1.resources.DeleteK8sPolicyResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getDeleteK8sPolicyMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * A stub to allow clients to do synchronous rpc calls to service KesselK8sPolicyService.
   */
  public static final class KesselK8sPolicyServiceBlockingV2Stub
      extends io.grpc.stub.AbstractBlockingStub<KesselK8sPolicyServiceBlockingV2Stub> {
    private KesselK8sPolicyServiceBlockingV2Stub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected KesselK8sPolicyServiceBlockingV2Stub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new KesselK8sPolicyServiceBlockingV2Stub(channel, callOptions);
    }

    /**
     */
    public org.project_kessel.api.inventory.v1beta1.resources.CreateK8sPolicyResponse createK8sPolicy(org.project_kessel.api.inventory.v1beta1.resources.CreateK8sPolicyRequest request) throws io.grpc.StatusException {
      return io.grpc.stub.ClientCalls.blockingV2UnaryCall(
          getChannel(), getCreateK8sPolicyMethod(), getCallOptions(), request);
    }

    /**
     */
    public org.project_kessel.api.inventory.v1beta1.resources.UpdateK8sPolicyResponse updateK8sPolicy(org.project_kessel.api.inventory.v1beta1.resources.UpdateK8sPolicyRequest request) throws io.grpc.StatusException {
      return io.grpc.stub.ClientCalls.blockingV2UnaryCall(
          getChannel(), getUpdateK8sPolicyMethod(), getCallOptions(), request);
    }

    /**
     */
    public org.project_kessel.api.inventory.v1beta1.resources.DeleteK8sPolicyResponse deleteK8sPolicy(org.project_kessel.api.inventory.v1beta1.resources.DeleteK8sPolicyRequest request) throws io.grpc.StatusException {
      return io.grpc.stub.ClientCalls.blockingV2UnaryCall(
          getChannel(), getDeleteK8sPolicyMethod(), getCallOptions(), request);
    }
  }

  /**
   * A stub to allow clients to do limited synchronous rpc calls to service KesselK8sPolicyService.
   */
  public static final class KesselK8sPolicyServiceBlockingStub
      extends io.grpc.stub.AbstractBlockingStub<KesselK8sPolicyServiceBlockingStub> {
    private KesselK8sPolicyServiceBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected KesselK8sPolicyServiceBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new KesselK8sPolicyServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public org.project_kessel.api.inventory.v1beta1.resources.CreateK8sPolicyResponse createK8sPolicy(org.project_kessel.api.inventory.v1beta1.resources.CreateK8sPolicyRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getCreateK8sPolicyMethod(), getCallOptions(), request);
    }

    /**
     */
    public org.project_kessel.api.inventory.v1beta1.resources.UpdateK8sPolicyResponse updateK8sPolicy(org.project_kessel.api.inventory.v1beta1.resources.UpdateK8sPolicyRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getUpdateK8sPolicyMethod(), getCallOptions(), request);
    }

    /**
     */
    public org.project_kessel.api.inventory.v1beta1.resources.DeleteK8sPolicyResponse deleteK8sPolicy(org.project_kessel.api.inventory.v1beta1.resources.DeleteK8sPolicyRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getDeleteK8sPolicyMethod(), getCallOptions(), request);
    }
  }

  /**
   * A stub to allow clients to do ListenableFuture-style rpc calls to service KesselK8sPolicyService.
   */
  public static final class KesselK8sPolicyServiceFutureStub
      extends io.grpc.stub.AbstractFutureStub<KesselK8sPolicyServiceFutureStub> {
    private KesselK8sPolicyServiceFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected KesselK8sPolicyServiceFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new KesselK8sPolicyServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<org.project_kessel.api.inventory.v1beta1.resources.CreateK8sPolicyResponse> createK8sPolicy(
        org.project_kessel.api.inventory.v1beta1.resources.CreateK8sPolicyRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getCreateK8sPolicyMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<org.project_kessel.api.inventory.v1beta1.resources.UpdateK8sPolicyResponse> updateK8sPolicy(
        org.project_kessel.api.inventory.v1beta1.resources.UpdateK8sPolicyRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getUpdateK8sPolicyMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<org.project_kessel.api.inventory.v1beta1.resources.DeleteK8sPolicyResponse> deleteK8sPolicy(
        org.project_kessel.api.inventory.v1beta1.resources.DeleteK8sPolicyRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getDeleteK8sPolicyMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_CREATE_K8S_POLICY = 0;
  private static final int METHODID_UPDATE_K8S_POLICY = 1;
  private static final int METHODID_DELETE_K8S_POLICY = 2;

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
        case METHODID_CREATE_K8S_POLICY:
          serviceImpl.createK8sPolicy((org.project_kessel.api.inventory.v1beta1.resources.CreateK8sPolicyRequest) request,
              (io.grpc.stub.StreamObserver<org.project_kessel.api.inventory.v1beta1.resources.CreateK8sPolicyResponse>) responseObserver);
          break;
        case METHODID_UPDATE_K8S_POLICY:
          serviceImpl.updateK8sPolicy((org.project_kessel.api.inventory.v1beta1.resources.UpdateK8sPolicyRequest) request,
              (io.grpc.stub.StreamObserver<org.project_kessel.api.inventory.v1beta1.resources.UpdateK8sPolicyResponse>) responseObserver);
          break;
        case METHODID_DELETE_K8S_POLICY:
          serviceImpl.deleteK8sPolicy((org.project_kessel.api.inventory.v1beta1.resources.DeleteK8sPolicyRequest) request,
              (io.grpc.stub.StreamObserver<org.project_kessel.api.inventory.v1beta1.resources.DeleteK8sPolicyResponse>) responseObserver);
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
          getCreateK8sPolicyMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              org.project_kessel.api.inventory.v1beta1.resources.CreateK8sPolicyRequest,
              org.project_kessel.api.inventory.v1beta1.resources.CreateK8sPolicyResponse>(
                service, METHODID_CREATE_K8S_POLICY)))
        .addMethod(
          getUpdateK8sPolicyMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              org.project_kessel.api.inventory.v1beta1.resources.UpdateK8sPolicyRequest,
              org.project_kessel.api.inventory.v1beta1.resources.UpdateK8sPolicyResponse>(
                service, METHODID_UPDATE_K8S_POLICY)))
        .addMethod(
          getDeleteK8sPolicyMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              org.project_kessel.api.inventory.v1beta1.resources.DeleteK8sPolicyRequest,
              org.project_kessel.api.inventory.v1beta1.resources.DeleteK8sPolicyResponse>(
                service, METHODID_DELETE_K8S_POLICY)))
        .build();
  }

  private static abstract class KesselK8sPolicyServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    KesselK8sPolicyServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return org.project_kessel.api.inventory.v1beta1.resources.K8SPoliciesService.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("KesselK8sPolicyService");
    }
  }

  private static final class KesselK8sPolicyServiceFileDescriptorSupplier
      extends KesselK8sPolicyServiceBaseDescriptorSupplier {
    KesselK8sPolicyServiceFileDescriptorSupplier() {}
  }

  private static final class KesselK8sPolicyServiceMethodDescriptorSupplier
      extends KesselK8sPolicyServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final java.lang.String methodName;

    KesselK8sPolicyServiceMethodDescriptorSupplier(java.lang.String methodName) {
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
      synchronized (KesselK8sPolicyServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new KesselK8sPolicyServiceFileDescriptorSupplier())
              .addMethod(getCreateK8sPolicyMethod())
              .addMethod(getUpdateK8sPolicyMethod())
              .addMethod(getDeleteK8sPolicyMethod())
              .build();
        }
      }
    }
    return result;
  }
}
