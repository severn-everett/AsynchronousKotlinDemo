package com.severett.akd.loom

import java.lang.Thread.sleep
import java.time.Instant
import java.util.concurrent.atomic.AtomicLong
import kotlin.concurrent.thread

class ThreadType : IType {
    override fun run(times: Int): Long {
        val counter = AtomicLong(0L)
        println("Threads - Started loop at ${Instant.now()}")
        (0 until times).map {
            thread {
                sleep(33L)
                counter.incrementAndGet()
                sleep(33L)
                counter.decrementAndGet()
                sleep(34L)
                counter.incrementAndGet()
            }
        }.forEach(Thread::join)
        println("Threads - Ended loop at ${Instant.now()}")
        return counter.get()
    }
}

fun main() {
    ThreadType().run(TIMES)
}
