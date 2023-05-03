package com.severett.akd.lincheck

import org.jctools.queues.MpscLinkedQueue
import org.jetbrains.kotlinx.lincheck.annotations.Operation
import org.jetbrains.kotlinx.lincheck.check
import org.jetbrains.kotlinx.lincheck.strategy.managed.modelchecking.ModelCheckingOptions
import org.jetbrains.kotlinx.lincheck.strategy.stress.StressOptions
import org.junit.jupiter.api.Test

class OpGroupTest {
    private val queue = MpscLinkedQueue<Int>()
    @Operation
    fun offer() = queue.offer(5)
    @Operation(nonParallelGroup = "consumer")
    fun poll(): Int? = queue.poll()
    @Operation(nonParallelGroup = "consumer")
    fun peek(): Int? = queue.peek()
    @Test
    fun stressTest() = StressOptions().check(this::class.java)
    @Test
    fun modelTest() = ModelCheckingOptions().check(this::class)
}
