package com.severett.akd.lincheck

import org.jetbrains.kotlinx.lincheck.RandomProvider
import org.jetbrains.kotlinx.lincheck.paramgen.ParameterGenerator
import kotlin.random.Random

@Suppress("UNUSED_PARAMETER")
class AnEnumGen(randomProvider: RandomProvider, configuration: String) : ParameterGenerator<AnEnum> {
    private val values = AnEnum.values()
    override fun generate(): AnEnum {
        return values[Random.nextInt(0, values.size)]
    }
}
