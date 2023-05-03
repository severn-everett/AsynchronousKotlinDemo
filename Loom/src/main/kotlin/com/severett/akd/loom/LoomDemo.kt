package com.severett.akd.loom

const val TIMES = 1_000_000

fun main() {
    val coroutineType = CoroutineType()
    val hybridType = HybridType()
    val loomType = LoomType()
    val threadType = ThreadType()
    coroutineType.run(TIMES)
    // hybridType.run(TIMES)
    // loomType.run(TIMES)
    // threadType.run(TIMES)
}
