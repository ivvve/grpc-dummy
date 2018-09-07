package com.simple.grpc.greeting.client;

import com.proto.dummy.DummyServiceGrpc;
import com.proto.greet.*;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

public class GreetingClient {

    // 서버가 올라가있지 않아도 Exception 없이 실행된다.
    public static void main(String[] args) {
        System.out.println("Hello I'm a gRPC client");

        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 8888)
                                                    .usePlaintext() // security (SSL)
                                                    .build();

        // created a greet service client (blocking - synchronous)
        GreetServiceGrpc.GreetServiceBlockingStub greetSyncClient = GreetServiceGrpc.newBlockingStub(channel);

        // created a protocol buffer greeting message
        Greeting greeting = Greeting.newBuilder()
                                        .setFirstName("Chris")
                                        .setLastName("Son")
                                        .build();

        // create greeting request
        GreetRequest request = GreetRequest.newBuilder()
                                            .setGreeting(greeting)
                                            .build();

        // call the RPC and get back a GreetResponse (protocol buffer)
        GreetResponse response = greetSyncClient.greet(request);

        System.out.println(response.getResult());
        // old & dummy
        // blocking stub = synchronous
        // DummyServiceGrpc.DummyServiceBlockingStub syncClient = DummyServiceGrpc.newBlockingStub(channel);
        // System.out.println("BlockingStub was created");

        // future stub = asynchronous
        // DummyServiceGrpc.DummyServiceFutureStub asyncClient = DummyServiceGrpc.newFutureStub(channel);

        // do something
        System.out.println("Shutting down channel");
        channel.shutdown();
    }
}
