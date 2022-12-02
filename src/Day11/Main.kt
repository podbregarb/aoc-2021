package Day11

import readInput

fun main() {

    fun parseInput(input: List<String>): MutableList<MutableList<Int>> {
        var numbers = mutableListOf<MutableList<Int>>()
        for (row in input) {
            numbers.add(row.map { it.toString().toInt() }.toMutableList())
        }
        return numbers
    }

    fun oneStep(parsedInput: MutableList<MutableList<Int>>, i: Int, j: Int, increaseBasePoint: Boolean = false): Int {
        var numberOfFlashes = 0
        if (parsedInput[i][j] > 9) {
            numberOfFlashes++
            parsedInput[i][j] = 0
            if (i - 1 >= 0) {
                numberOfFlashes += oneStep(parsedInput, i - 1, j, true)
            }
            if (i + 1 <= parsedInput.size - 1) {
                numberOfFlashes += oneStep(parsedInput, i + 1, j, true)
            }
            if (j - 1 >= 0) {
                numberOfFlashes += oneStep(parsedInput, i, j - 1, true)
            }
            if (j + 1 <= parsedInput[0].size - 1) {
                numberOfFlashes += oneStep(parsedInput, i, j + 1, true)
            }
            if (i - 1 >= 0 && j - 1 >= 0) {
                numberOfFlashes += oneStep(parsedInput, i - 1, j - 1, true)
            }
            if (i - 1 >= 0 && j + 1 <= parsedInput[0].size - 1) {
                numberOfFlashes += oneStep(parsedInput, i - 1, j + 1, true)
            }
            if (i + 1 <= parsedInput.size - 1 && j - 1 >= 0) {
                numberOfFlashes += oneStep(parsedInput, i + 1, j - 1, true)
            }
            if (i + 1 <= parsedInput.size - 1 && j + 1 <= parsedInput[0].size - 1) {
                numberOfFlashes += oneStep(parsedInput, i + 1, j + 1, true)
            }
        } else {
            if (increaseBasePoint) {
                parsedInput[i][j]++
            }
        }
        return numberOfFlashes
    }

    fun printOutput(parsedInput: MutableList<MutableList<Int>>) {
        for (row in parsedInput) {
            println(row)
        }
    }

    fun increaseValue(parsedInput: MutableList<MutableList<Int>>, i: Int, j: Int) {
        parsedInput[i][j]++
    }

    fun step(parsedInput: MutableList<MutableList<Int>>, numberOfIterations: Int): Int {
        var numberOfFlashes = 0
        for (iterations in 0..numberOfIterations) {
            printOutput(parsedInput)
            for (i in 0 until parsedInput.size) {
                for (j in 0 until parsedInput[0].size) {
                    increaseValue(parsedInput, i, j)
                    numberOfFlashes += oneStep(parsedInput, i, j)
                }
            }
            println("------------------------------")
        }
        printOutput(parsedInput)
        return numberOfFlashes
    }

    fun part1(input: List<String>): Int {
        var parsedInput: MutableList<MutableList<Int>> = parseInput(input)
        return step(parsedInput, 100)
    }

    fun part2(input: List<String>): Int {
        return part1(input)
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day11/Data_test")
    val input = readInput("Day11/Data")
    println("1st part")
    println(part1(testInput))
    //println(part1(input))

    println("2nd part")
    /*println(part2(testInput))
    println(part2(input))*/
}
