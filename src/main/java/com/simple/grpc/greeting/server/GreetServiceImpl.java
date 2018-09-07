package com.simple.grpc.greeting.server;

import com.proto.greet.GreetRequest;
import com.proto.greet.GreetResponse;
import com.proto.greet.GreetServiceGrpc;
import com.proto.greet.Greeting;
import io.grpc.stub.StreamObserver;

public class GreetServiceImpl extends GreetServiceGrpc.GreetServiceImplBase {

    /**
     * rpc Greet(GreetRequest) returns (GreetResponse) {}; 에 해당
     * @param request
     * @param responseObserver
     */
    @Override
    public void greet(GreetRequest request, StreamObserver<GreetResponse> responseObserver) {
        // extract data from request
        Greeting greeting = request.getGreeting();
        String result = "Hello " + greeting.getFirstName();

        // create response
        GreetResponse response = GreetResponse.newBuilder()
                                                .setResult(result)
                                                .build();

        // send response
        responseObserver.onNext(response);

        // complete the RPC call
        responseObserver.onCompleted();
    }
}
