package com.severett.akd.lincheck

import java.util.concurrent.atomic.*

class NaiveFoo {
    private var value = 0
    fun incAndGet() = ++value
    fun get() = value
}

class AtomicFoo {
    private val value = AtomicInteger(0)
    fun incAndGet() = value.incrementAndGet()
    fun get() = value.get()
}
