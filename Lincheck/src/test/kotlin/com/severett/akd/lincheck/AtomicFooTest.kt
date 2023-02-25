package com.severett.akd.lincheck

import org.jetbrains.kotlinx.lincheck.annotations.Operation
import org.jetbrains.kotlinx.lincheck.check
import org.jetbrains.kotlinx.lincheck.strategy.managed.modelchecking.ModelCheckingOptions
import org.jetbrains.kotlinx.lincheck.strategy.stress.StressOptions
import org.jetbrains.kotlinx.lincheck.verifier.VerifierState
import org.junit.jupiter.api.Test

class AtomicFooTest : VerifierState() {
    private val atomicFoo = AtomicFoo()
    @Operation
    fun incAndGet() = atomicFoo.incAndGet()
    @Operation
    fun get() = atomicFoo.get()
    @Test
    fun stressTest() = StressOptions().check(this::class)
    @Test
    fun modelTest() = ModelCheckingOptions().check(this::class)

    override fun extractState() = atomicFoo.get()
}
