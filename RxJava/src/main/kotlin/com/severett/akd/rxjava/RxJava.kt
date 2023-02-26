package com.severett.akd.rxjava

import io.reactivex.rxjava3.core.Observable
import java.util.stream.*

fun main() {
    // runStream()
    runRxJava()
}

private fun runStream() {
    Stream.iterate(0) { i -> i + 1 }
        .filter { i -> i % 5 != 0 }
        .map { i -> i to "NUMBER $i" }
        .limit(100)
        .forEach { pair ->
            if (pair.first == 118) throw Exception("Something bad!")
            println("ENTRY: ${pair.second} (i ${pair.first})")
        }
}

private fun runRxJava() {
    Observable.fromStream(Stream.iterate(0) { i -> i + 1 })
        .filter { i -> i % 5 != 0 }
        .map { i -> i to "NUMBER $i" }
        .take(100)
        .forEach { pair ->
            if (pair.first == 118) throw Exception("Something bad!")
            println("ENTRY: ${pair.second} (i ${pair.first})")
        }
}
