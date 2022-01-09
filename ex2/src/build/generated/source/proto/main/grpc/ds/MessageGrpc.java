package ds;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.43.1)",
    comments = "Source: messages.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class MessageGrpc {

  private MessageGrpc() {}

  public static final String SERVICE_NAME = "Message";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<ds.MessageRequest,
      ds.Empty> getRegisterMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "register",
      requestType = ds.MessageRequest.class,
      responseType = ds.Empty.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<ds.MessageRequest,
      ds.Empty> getRegisterMethod() {
    io.grpc.MethodDescriptor<ds.MessageRequest, ds.Empty> getRegisterMethod;
    if ((getRegisterMethod = MessageGrpc.getRegisterMethod) == null) {
      synchronized (MessageGrpc.class) {
        if ((getRegisterMethod = MessageGrpc.getRegisterMethod) == null) {
          MessageGrpc.getRegisterMethod = getRegisterMethod =
              io.grpc.MethodDescriptor.<ds.MessageRequest, ds.Empty>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "register"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  ds.MessageRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  ds.Empty.getDefaultInstance()))
              .setSchemaDescriptor(new MessageMethodDescriptorSupplier("register"))
              .build();
        }
      }
    }
    return getRegisterMethod;
  }

  private static volatile io.grpc.MethodDescriptor<ds.PPRequest,
      ds.Empty> getPushMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "push",
      requestType = ds.PPRequest.class,
      responseType = ds.Empty.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<ds.PPRequest,
      ds.Empty> getPushMethod() {
    io.grpc.MethodDescriptor<ds.PPRequest, ds.Empty> getPushMethod;
    if ((getPushMethod = MessageGrpc.getPushMethod) == null) {
      synchronized (MessageGrpc.class) {
        if ((getPushMethod = MessageGrpc.getPushMethod) == null) {
          MessageGrpc.getPushMethod = getPushMethod =
              io.grpc.MethodDescriptor.<ds.PPRequest, ds.Empty>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "push"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  ds.PPRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  ds.Empty.getDefaultInstance()))
              .setSchemaDescriptor(new MessageMethodDescriptorSupplier("push"))
              .build();
        }
      }
    }
    return getPushMethod;
  }

  private static volatile io.grpc.MethodDescriptor<ds.MessageRequest,
      ds.Dictionary> getPullMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "pull",
      requestType = ds.MessageRequest.class,
      responseType = ds.Dictionary.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<ds.MessageRequest,
      ds.Dictionary> getPullMethod() {
    io.grpc.MethodDescriptor<ds.MessageRequest, ds.Dictionary> getPullMethod;
    if ((getPullMethod = MessageGrpc.getPullMethod) == null) {
      synchronized (MessageGrpc.class) {
        if ((getPullMethod = MessageGrpc.getPullMethod) == null) {
          MessageGrpc.getPullMethod = getPullMethod =
              io.grpc.MethodDescriptor.<ds.MessageRequest, ds.Dictionary>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "pull"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  ds.MessageRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  ds.Dictionary.getDefaultInstance()))
              .setSchemaDescriptor(new MessageMethodDescriptorSupplier("pull"))
              .build();
        }
      }
    }
    return getPullMethod;
  }

  private static volatile io.grpc.MethodDescriptor<ds.PPRequest,
      ds.Dictionary> getPushpullMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "pushpull",
      requestType = ds.PPRequest.class,
      responseType = ds.Dictionary.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<ds.PPRequest,
      ds.Dictionary> getPushpullMethod() {
    io.grpc.MethodDescriptor<ds.PPRequest, ds.Dictionary> getPushpullMethod;
    if ((getPushpullMethod = MessageGrpc.getPushpullMethod) == null) {
      synchronized (MessageGrpc.class) {
        if ((getPushpullMethod = MessageGrpc.getPushpullMethod) == null) {
          MessageGrpc.getPushpullMethod = getPushpullMethod =
              io.grpc.MethodDescriptor.<ds.PPRequest, ds.Dictionary>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "pushpull"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  ds.PPRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  ds.Dictionary.getDefaultInstance()))
              .setSchemaDescriptor(new MessageMethodDescriptorSupplier("pushpull"))
              .build();
        }
      }
    }
    return getPushpullMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static MessageStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<MessageStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<MessageStub>() {
        @java.lang.Override
        public MessageStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new MessageStub(channel, callOptions);
        }
      };
    return MessageStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static MessageBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<MessageBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<MessageBlockingStub>() {
        @java.lang.Override
        public MessageBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new MessageBlockingStub(channel, callOptions);
        }
      };
    return MessageBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static MessageFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<MessageFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<MessageFutureStub>() {
        @java.lang.Override
        public MessageFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new MessageFutureStub(channel, callOptions);
        }
      };
    return MessageFutureStub.newStub(factory, channel);
  }

  /**
   */
  public static abstract class MessageImplBase implements io.grpc.BindableService {

    /**
     */
    public void register(ds.MessageRequest request,
        io.grpc.stub.StreamObserver<ds.Empty> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getRegisterMethod(), responseObserver);
    }

    /**
     */
    public void push(ds.PPRequest request,
        io.grpc.stub.StreamObserver<ds.Empty> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getPushMethod(), responseObserver);
    }

    /**
     */
    public void pull(ds.MessageRequest request,
        io.grpc.stub.StreamObserver<ds.Dictionary> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getPullMethod(), responseObserver);
    }

    /**
     */
    public void pushpull(ds.PPRequest request,
        io.grpc.stub.StreamObserver<ds.Dictionary> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getPushpullMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getRegisterMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                ds.MessageRequest,
                ds.Empty>(
                  this, METHODID_REGISTER)))
          .addMethod(
            getPushMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                ds.PPRequest,
                ds.Empty>(
                  this, METHODID_PUSH)))
          .addMethod(
            getPullMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                ds.MessageRequest,
                ds.Dictionary>(
                  this, METHODID_PULL)))
          .addMethod(
            getPushpullMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                ds.PPRequest,
                ds.Dictionary>(
                  this, METHODID_PUSHPULL)))
          .build();
    }
  }

  /**
   */
  public static final class MessageStub extends io.grpc.stub.AbstractAsyncStub<MessageStub> {
    private MessageStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected MessageStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new MessageStub(channel, callOptions);
    }

    /**
     */
    public void register(ds.MessageRequest request,
        io.grpc.stub.StreamObserver<ds.Empty> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getRegisterMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void push(ds.PPRequest request,
        io.grpc.stub.StreamObserver<ds.Empty> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getPushMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void pull(ds.MessageRequest request,
        io.grpc.stub.StreamObserver<ds.Dictionary> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getPullMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void pushpull(ds.PPRequest request,
        io.grpc.stub.StreamObserver<ds.Dictionary> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getPushpullMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class MessageBlockingStub extends io.grpc.stub.AbstractBlockingStub<MessageBlockingStub> {
    private MessageBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected MessageBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new MessageBlockingStub(channel, callOptions);
    }

    /**
     */
    public ds.Empty register(ds.MessageRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getRegisterMethod(), getCallOptions(), request);
    }

    /**
     */
    public ds.Empty push(ds.PPRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getPushMethod(), getCallOptions(), request);
    }

    /**
     */
    public ds.Dictionary pull(ds.MessageRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getPullMethod(), getCallOptions(), request);
    }

    /**
     */
    public ds.Dictionary pushpull(ds.PPRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getPushpullMethod(), getCallOptions(), request);
    }
  }

  /**
   */
  public static final class MessageFutureStub extends io.grpc.stub.AbstractFutureStub<MessageFutureStub> {
    private MessageFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected MessageFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new MessageFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<ds.Empty> register(
        ds.MessageRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getRegisterMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<ds.Empty> push(
        ds.PPRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getPushMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<ds.Dictionary> pull(
        ds.MessageRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getPullMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<ds.Dictionary> pushpull(
        ds.PPRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getPushpullMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_REGISTER = 0;
  private static final int METHODID_PUSH = 1;
  private static final int METHODID_PULL = 2;
  private static final int METHODID_PUSHPULL = 3;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final MessageImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(MessageImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_REGISTER:
          serviceImpl.register((ds.MessageRequest) request,
              (io.grpc.stub.StreamObserver<ds.Empty>) responseObserver);
          break;
        case METHODID_PUSH:
          serviceImpl.push((ds.PPRequest) request,
              (io.grpc.stub.StreamObserver<ds.Empty>) responseObserver);
          break;
        case METHODID_PULL:
          serviceImpl.pull((ds.MessageRequest) request,
              (io.grpc.stub.StreamObserver<ds.Dictionary>) responseObserver);
          break;
        case METHODID_PUSHPULL:
          serviceImpl.pushpull((ds.PPRequest) request,
              (io.grpc.stub.StreamObserver<ds.Dictionary>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }
  }

  private static abstract class MessageBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    MessageBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return ds.MessageProto.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("Message");
    }
  }

  private static final class MessageFileDescriptorSupplier
      extends MessageBaseDescriptorSupplier {
    MessageFileDescriptorSupplier() {}
  }

  private static final class MessageMethodDescriptorSupplier
      extends MessageBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    MessageMethodDescriptorSupplier(String methodName) {
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
      synchronized (MessageGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new MessageFileDescriptorSupplier())
              .addMethod(getRegisterMethod())
              .addMethod(getPushMethod())
              .addMethod(getPullMethod())
              .addMethod(getPushpullMethod())
              .build();
        }
      }
    }
    return result;
  }
}
