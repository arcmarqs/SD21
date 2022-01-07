import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;

import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.logging.Logger;

import io.grpc.Channel;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.StatusRuntimeException;



public class Peer {
    
}


class PServer {

    private static final Logger logger = Logger.getLogger(PServer.class.getName());
    Server server;    
    HashMap<String,String> dict;
    HashSet<String> iptable;

    void start() throws IOException {
        int port=52502;

        server = ServerBuilder.forPort(port).addService(new MessageImpl()).build().start();
        logger.info("Server started on " + port);
        


    }
    static class MessageImpl extends MessageGrpc.MessageImplBase {

        @Override
        public void pull(MessageProto.MessageRequest request,
                StreamObserver<MessageProto.Dictionary> responseObserver) {

        }

        @Override
        public void push(MessageProto.Dictionary request, StreamObserver<MessageProto.Empty> responseObserver) {

        }

        @Override
        public void pushpull(MessageProto.PPRequest request, StreamObserver<MessageProto.Dictionary> responseObserver) {

        }

        @Override
        public void register(MessageProto.MessageRequest request, StreamObserver<MessageProto.Empty> responseObserver) {

        }
        
    }
}
