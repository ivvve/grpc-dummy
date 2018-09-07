package com.simple.grpc.greeting.server;

import io.grpc.Server;
import io.grpc.ServerBuilder;

import java.io.IOException;

public class GreetingServer {

    public static void main(String[] args) throws IOException, InterruptedException {
        System.out.println("Hello gRPC");

        Server greetingServer = ServerBuilder.forPort(8888)
                                                .build();

        greetingServer.start();

        // Runtime 종료 시 수행 할 명령 등록
        Runtime.getRuntime().addShutdownHook(new Thread( () -> {
            System.out.println("Received Shutdown Request");
            greetingServer.shutdown();
            System.out.println("Successfully stopped the server");
        } ));

        greetingServer.awaitTermination();
    }

}
