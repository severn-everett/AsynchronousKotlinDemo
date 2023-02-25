package com.severett.akd.lincheck

import org.jctools.queues.MpscLinkedQueue
import org.jetbrains.kotlinx.lincheck.annotations.OpGroupConfig
import org.jetbrains.kotlinx.lincheck.annotations.Operation
import org.jetbrains.kotlinx.lincheck.check
import org.jetbrains.kotlinx.lincheck.strategy.managed.modelchecking.ModelCheckingOptions
import org.jetbrains.kotlinx.lincheck.strategy.stress.StressOptions
import org.jetbrains.kotlinx.lincheck.verifier.VerifierState
import org.junit.jupiter.api.Test

@OpGroupConfig(name = "consumer", nonParallel = true)
class OpGroupTest : VerifierState() {
    private val queue = MpscLinkedQueue<Int>()
    @Operation
    fun offer() = queue.offer(5)
    @Operation(group = "consumer")
    fun poll(): Int? = queue.poll()
    @Operation(group = "consumer")
    fun peek(): Int? = queue.peek()
    @Test
    fun stressTest() = StressOptions().check(this::class.java)
    @Test
    fun modelTest() = ModelCheckingOptions().check(this::class)
    override fun extractState() = queue
}
