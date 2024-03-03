package org.example

import generated.GreeterGrpcKt
import generated.helloRequest
import io.grpc.ManagedChannel

import java.io.Closeable
import java.util.concurrent.TimeUnit

class HelloWorldClient(
    private val channel: ManagedChannel
) : Closeable {
    private val stub: GreeterGrpcKt.GreeterCoroutineStub = GreeterGrpcKt.GreeterCoroutineStub(channel)

    suspend fun greet(name: String) {
        val request = helloRequest { this.name = name }
        val response = stub.sayHello(request)
        println("Received: ${response.message}")
    }

    override fun close() {
        channel.shutdown().awaitTermination(5, TimeUnit.SECONDS)
    }
}