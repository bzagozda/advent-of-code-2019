package pl.bzagozda.aoc.day2

class IntCodeComputer(private val initialState: IntArray) {
    var instructionPointer: Int = 0
    private var program: IntArray = initialState

    private val instructionShift: Int = 4
    private val nounIndex = 1
    private val verbIndex = 2

    private fun isProgramFinished() = program[instructionPointer] == 99

    fun setNoun(noun: Int) { program[nounIndex] = noun }

    fun setVerb(verb: Int) { program[verbIndex] = verb }

    fun getResult() = program[0]

    fun reset() {
        program = initialState.clone()
        instructionPointer = 0
    }

    private fun getOpcode() = program[instructionPointer]

    private fun getFirstParamAddress() = program[instructionPointer+1]

    private fun getSecondParamAddress() = program[instructionPointer+2]

    private fun getTargetAddress() = program[instructionPointer+3]

    private fun nextOperation() { instructionPointer += instructionShift }

    private fun addOperation() {
        program[getTargetAddress()] = program[getFirstParamAddress()] + program[getSecondParamAddress()]
    }

    private fun mulOperation() {
        program[getTargetAddress()] = program[getFirstParamAddress()] * program[getSecondParamAddress()]
    }

    fun run() {
        while(!isProgramFinished()) {
            when (getOpcode()) {
                1 -> {
                    addOperation()
                    nextOperation()
                }
                2 -> {
                    mulOperation()
                    nextOperation()
                }
                99 -> {}
                else -> throw IllegalStateException()
            }
        }
    }
}