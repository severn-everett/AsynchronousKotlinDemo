package com.severett.akd.loom

import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.time.Instant
import java.util.concurrent.atomic.AtomicLong

class CoroutineType : IType {
    override fun run(times: Int): Long {
        return runBlocking {
            val counter = AtomicLong(0L)
            println("Coroutines - Started loop at ${Instant.now()}")
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
            println("Coroutines - Ended loop at ${Instant.now()}")
            counter.get()
        }
    }
}