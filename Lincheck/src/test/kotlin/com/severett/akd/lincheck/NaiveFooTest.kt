package com.severett.akd.lincheck

import org.jetbrains.kotlinx.lincheck.annotations.Operation
import org.jetbrains.kotlinx.lincheck.check
import org.jetbrains.kotlinx.lincheck.strategy.managed.modelchecking.ModelCheckingOptions
import org.jetbrains.kotlinx.lincheck.strategy.stress.StressOptions
import org.jetbrains.kotlinx.lincheck.verifier.VerifierState
import org.junit.jupiter.api.Test

class NaiveFooTest {
    private val naiveFoo = NaiveFoo()
    @Operation
    fun incAndGet() = naiveFoo.incAndGet()
    @Operation
    fun get() = naiveFoo.get()

    @Test
    fun stressTest() = StressOptions().check(this::class.java)
    @Test
    fun modelTest() = ModelCheckingOptions().check(this::class)

    override fun equals(other: Any?): Boolean {
        // return naiveFoo.get() == other
        return naiveFoo.get() == (other as? NaiveFooTest)?.get()
    }

    override fun hashCode(): Int {
        return naiveFoo.get()
    }
}
