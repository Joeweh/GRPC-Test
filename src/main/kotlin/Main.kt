package org.example

import io.grpc.ManagedChannelBuilder

suspend fun main(args: Array<String>) {
    val port = System.getenv("PORT")?.toInt() ?: 50051

    val channel = ManagedChannelBuilder.forAddress("localhost", port).usePlaintext().build()

    val client = HelloWorldClient(channel)

    val user = args.singleOrNull() ?: "world"
    client.greet(user)
}