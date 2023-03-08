package com.severett.akd.lincheck

import org.jetbrains.kotlinx.lincheck.paramgen.ParameterGenerator
import kotlin.random.Random

class AnEnumGen(configuration: String) : ParameterGenerator<AnEnum> {
    private val values = AnEnum.values()
    override fun generate(): AnEnum {
        return values[Random.nextInt(0, values.size)]
    }
}
