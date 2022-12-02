package Day21

import readInput

fun main() {
    fun part1(input: List<String>): Int {
        return input.size
    }

    fun part2(input: List<String>): Int {
        return part1(input)
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day21/Data_test")
    val input = readInput("Day21/Data")
    println("1st part")
    println(part1(testInput))
    println(part1(input))

    println("2nd part")
    println(part2(testInput))
    println(part2(input))
}
