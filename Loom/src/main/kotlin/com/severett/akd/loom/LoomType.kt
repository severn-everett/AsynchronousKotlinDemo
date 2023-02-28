package com.severett.akd.loom

import java.lang.Thread.sleep
import java.lang.Thread.startVirtualThread
import java.time.Instant
import java.util.concurrent.atomic.AtomicLong

class LoomType : IType {
    override fun run(times: Int): Long {
        val counter = AtomicLong(0L)
        println("Loom - Started loop at ${Instant.now()}")
        (0 until times).map {
            startVirtualThread {
                sleep(33L)
                counter.incrementAndGet()
                sleep(33L)
                counter.decrementAndGet()
                sleep(34L)
                counter.incrementAndGet()
            }
        }.forEach(Thread::join)
        println("Loom - Ended loop at ${Instant.now()}")
        return counter.get()
    }
}
