package com.severett.akd.loom

private const val TIMES = 10000

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
