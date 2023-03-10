package com.severett.akd.lincheck

import org.jetbrains.kotlinx.lincheck.annotations.Operation
import org.jetbrains.kotlinx.lincheck.check
import org.jetbrains.kotlinx.lincheck.strategy.managed.modelchecking.ModelCheckingOptions
import org.jetbrains.kotlinx.lincheck.strategy.stress.StressOptions
import org.jetbrains.kotlinx.lincheck.verifier.VerifierState
import org.junit.jupiter.api.Test

class DeadlockFooTest : VerifierState() {
    private val deadlockFoo = DeadlockFoo()
    @Operation
    fun incAndGet() = deadlockFoo.incAndGet()
    @Operation
    fun get() = deadlockFoo.get()
    override fun extractState() = deadlockFoo.get()

    @Test
    fun stressTest() = StressOptions().check(this::class.java)
    @Test
    fun modelTest() = ModelCheckingOptions().check(this::class)
}
