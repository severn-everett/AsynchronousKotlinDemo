package com.severett.akd.rxjava

import io.reactivex.rxjava3.core.Observable
import java.util.stream.*

fun main() {
    try {
        runStream()
    } catch (e: Exception) {
        e.printStackTrace(System.err)
    }
    try {
        runRxJava()
    } catch (e: Exception) {
        e.printStackTrace(System.err)
    }
}

private fun runStream() {
    generateSequence(0) { i -> i + 1 }
        .filter { i -> i % 5 != 0 }
        .map { i -> if (i == 118) throw Exception("Something bad!") else i to "NUMBER $i" }
        .take(100)
        .forEach { pair ->
            println("STREAM ENTRY: ${pair.second} (i ${pair.first})")
        }
}

private fun runRxJava() {
    Observable.fromStream(Stream.iterate(0) { i -> i + 1 })
        .filter { i -> i % 5 != 0 }
        .map { i -> if (i == 118) throw Exception("Something bad!") else i to "NUMBER $i" }
        // .map { i -> i to "NUMBER $i" }
        .onErrorComplete { println(it.message); true }
        .take(100)
        .forEach { pair ->
            // if (pair.first == 118) throw Exception("Something bad!")
            println("RXJAVA ENTRY: ${pair.second} (i ${pair.first})")
        }
}
