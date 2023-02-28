package com.severett.akd.loom

import kotlinx.coroutines.asCoroutineDispatcher
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.time.Instant
import java.util.concurrent.*
import java.util.concurrent.atomic.*

class HybridType : IType {
    @Suppress("DuplicatedCode")
    override fun run(times: Int): Long {
        return Executors.newVirtualThreadPerTaskExecutor().asCoroutineDispatcher().use {
            runBlocking {
                val counter = AtomicLong(0L)
                println("Hybrid - Started loop at ${Instant.now()}")
                (0 until times).map {
                    launch {
                        delay(33L)
                        counter.incrementAndGet()
                        delay(33L)
                        counter.decrementAndGet()
                        delay(34L)
                        counter.incrementAndGet()
                    }
                }.forEach { it.join() }
                println("Hybrid - Ended loop at ${Instant.now()}")
                counter.get()
            }
        }
    }
}
