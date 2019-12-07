package pl.bzagozda.aoc.util

fun measureExecutionTimeMillis(block: () -> Unit): Long =
    System.currentTimeMillis().also { block() }.let { System.currentTimeMillis() - it }