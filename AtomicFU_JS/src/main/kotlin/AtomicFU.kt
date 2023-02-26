import kotlinx.atomicfu.atomic
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class Foo {
    private val safeCounter = atomic(0)
    private var unsafeCounter = 0

    suspend fun run() {
        (0 until 1000).map {
            CoroutineScope(Dispatchers.Default).launch {
                safeCounter += 1
                unsafeCounter += 1
            }
        }.forEach { it.join() }
        println("Safe counter: ${safeCounter.value}")
        println("Unsafe counter: $unsafeCounter")
    }
}

suspend fun main() {
    Foo().run()
}
