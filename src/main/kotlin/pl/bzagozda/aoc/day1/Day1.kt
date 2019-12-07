package pl.bzagozda.aoc.day1

import pl.bzagozda.aoc.util.getResourceFile

object Day1 {
    fun run(part: Int) = when(part) {
        1 -> part1()
        2 -> part2()
        else -> -1
    }

    private fun part1() = procesInput { massList ->
        massList
            .map { massToFuel(it) }
            .sum()
    }

    private fun part2() = procesInput { massList ->
        massList
            .map { massToFuelAdvanced(it) }
            .sum()
    }

    private fun massToFuel(mass: Int): Int = mass / 3 - 2

    private fun massToFuelAdvanced(mass: Int): Int {
        var currentFuelNeeded = massToFuel(mass)
        var totalFuelNeeded = currentFuelNeeded

        while(currentFuelNeeded > 6) {
            currentFuelNeeded = massToFuel(currentFuelNeeded)
            totalFuelNeeded += currentFuelNeeded
        }

        return totalFuelNeeded
    }

    private fun procesInput(block: (List<Int>) -> Any) =
        "/day1/input.txt"
            .getResourceFile()
            .readLines()
            .map { it.toInt() }
            .let(block)
            .also { println("Result: $it") }

}