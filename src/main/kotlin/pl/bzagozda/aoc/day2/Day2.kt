package pl.bzagozda.aoc.day2

import pl.bzagozda.aoc.util.getResourceFile


typealias IsProgramFinished = Boolean

object Day2 {
    fun run(part: Int): Unit = when(part) {
        1 -> part1()
        2 -> part2()
        else -> Unit
    }

    private fun part1() {
        inputToIntcodeProgram()
            .apply { this[1] = 12 }
            .apply { this[2] = 2 }
            .let { runIntcodeProgram(it) }
            .also { println("Result: ${it[0]}") }
    }

    private fun part2() {
        val intCodeComputer = IntCodeComputer(inputToIntcodeProgram())

        for(noun in 0..99) {
            for(verb in 0..99) {
                val result = intCodeComputer.apply {
                    reset()
                    setNoun(noun)
                    setVerb(verb)
                    run()
                }.getResult()

                if(result == 19690720) {
                    println(100 * noun + verb)
                    return
                }
            }
        }
    }

    private fun runIntcodeProgram(intcode: IntArray): IntArray {
        for((index, i) in intcode.withIndex()) {
            if(index % 4 != 0) {
                continue
            }
            if (handleOpcode(i, index, intcode)) break
        }
        return intcode
    }

    private fun handleOpcode(opcode: Int, index: Int, intcode: IntArray): IsProgramFinished {
        when (opcode) {
            1 -> processSumIntcode(index, intcode)
            2 -> processMulIntcode(index, intcode)
            99 -> return true
        }
        return false
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