package pl.bzagozda.aoc

import com.github.ajalt.clikt.core.CliktCommand
import com.github.ajalt.clikt.parameters.options.option
import com.github.ajalt.clikt.parameters.options.prompt
import com.github.ajalt.clikt.parameters.types.int
import pl.bzagozda.aoc.day1.Day1
import pl.bzagozda.aoc.util.measureExecutionTimeMillis

class CLI : CliktCommand() {
    private val day: Int by option(help = "AOC 2019 Day").int().prompt("Day of AOC-2019")
    private val part: Int by option(help = "Part of a task").int().prompt("Part of a task")

    override fun run() = measureExecutionTimeMillis {
        when(day) {
            1 -> Day1.run(part)
            else -> Unit
        }
    }.let { print("Measured milliseconds: $it") }

}

fun main(args: Array<String>) = CLI().main(args)