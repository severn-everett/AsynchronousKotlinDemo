package com.severett.akd.loom

import kotlinx.benchmark.Measurement
import kotlinx.benchmark.Warmup
import kotlinx.coroutines.ExecutorCoroutineDispatcher
import kotlinx.coroutines.asCoroutineDispatcher
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.openjdk.jmh.annotations.Benchmark
import org.openjdk.jmh.annotations.BenchmarkMode
import org.openjdk.jmh.annotations.Fork
import org.openjdk.jmh.annotations.Level
import org.openjdk.jmh.annotations.Mode
import org.openjdk.jmh.annotations.OutputTimeUnit
import org.openjdk.jmh.annotations.Param
import org.openjdk.jmh.annotations.Scope
import org.openjdk.jmh.annotations.Setup
import org.openjdk.jmh.annotations.State
import org.openjdk.jmh.annotations.TearDown
import org.openjdk.jmh.infra.Blackhole
import java.util.concurrent.*

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@Warmup(time = 5, timeUnit = TimeUnit.SECONDS)
@Measurement(time = 5, timeUnit = TimeUnit.SECONDS)
@State(Scope.Thread)
@Fork(1)
class LoomBenchmark {
    @Param(value = ["100000"])
    // @Param(value = ["10", "100", "1000", "10000", "100000"])
    private var repeat: Int = 0
    // Simulating work in increasingly-heavier loads
    @Param(value = ["10"])
    // @Param(value = ["0", "10", "100"])
    private var delay: Long = 0
    private lateinit var executorService: ExecutorService
    private lateinit var executorCoroutineDispatcher: ExecutorCoroutineDispatcher

    @Setup(Level.Iteration)
    fun setup() {
        executorService = Executors.newVirtualThreadPerTaskExecutor()
        executorCoroutineDispatcher = executorService.asCoroutineDispatcher()
    }

    @TearDown(Level.Iteration)
    fun teardown() {
        executorService.close()
    }

    @Benchmark
    fun loom(blackhole: Blackhole) {
        (0..<repeat).map { i ->
            executorService.submit {
                Thread.sleep(delay)
                blackhole.consume(i)
            }
        }.forEach { it.get() }
    }

    @Benchmark
    fun coroutines(blackhole: Blackhole) {
        runBlocking {
            (0..<repeat).map { i ->
                launch {
                    delay(delay)
                    blackhole.consume(i)
                }
            }.forEach { it.join() }
        }
    }

    @Benchmark
    fun hybrid(blackhole: Blackhole) {
        runBlocking(executorCoroutineDispatcher) {
            (0..<repeat).map { i ->
                launch {
                    delay(delay)
                    blackhole.consume(i)
                }
            }.forEach { it.join() }
        }
    }
}
