package pl.bzagozda.aoc.day2

import pl.bzagozda.aoc.util.getResourceFile

object Day2 {
    fun run(part: Int) = when(part) {
        1 -> part1()
        2 -> part2()
        else -> -1
    }

    private fun part1() = inputToIntcodeProgram()
        .apply { this[1] = 12 }
        .apply { this[2] = 2 }
        .let { runIntcodeProgram(it) }
        .also { println("Result: ${it[0]}") }

    private fun part2(): Int = -1

    private fun runIntcodeProgram(intcode: IntArray): IntArray {
        loop@for((index, i) in intcode.withIndex()) {
            if(index % 4 != 0) {
                continue
            }
            when(i) {
                1 -> processSumIntcode(index, intcode)
                2 -> processMulIntcode(index, intcode)
                99 -> break@loop
            }
        }
        return intcode
    }

    private fun processSumIntcode(startingIndex: Int, intcode: IntArray) {
        intcode[intcode[targetPos(startingIndex)]] = intcode[intcode[firstValPos(startingIndex)]] + intcode[intcode[secondValPos(startingIndex)]]
    }

    private fun processMulIntcode(startingIndex: Int, intcode: IntArray) {
        intcode[intcode[targetPos(startingIndex)]] = intcode[intcode[firstValPos(startingIndex)]] * intcode[intcode[secondValPos(startingIndex)]]
    }

    private fun firstValPos(startingIndex: Int) = startingIndex + 1

    private fun secondValPos(startingIndex: Int) = startingIndex + 2

    private fun targetPos(startingIndex: Int) = startingIndex + 3

    private fun inputToIntcodeProgram(): IntArray = "/day2/input.txt"
        .getResourceFile()
        .readText()
        .split(",")
        .map { it.toInt() }
        .toIntArray()
}