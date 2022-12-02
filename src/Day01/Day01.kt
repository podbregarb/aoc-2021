package Day01

import readInput

fun main() {
    fun part1(input: List<String>): Int {
        var numberOfIncreases = 0
        input.forEachIndexed { index, element ->
            if (index > 0 && element.toInt() > input[index - 1].toInt()) {
                numberOfIncreases++
            }
        }
        return numberOfIncreases
    }

    fun part2(input: List<String>): Int {
        val data = mutableListOf<String>()
        for (i in 0..input.size) {
            if (i + 2 >= input.size || i + 1 >= input.size || i >= input.size) {
                continue
            } else {
                data.add((input[i].toInt() + input[i + 1].toInt() + input[i + 2].toInt()).toString())
            }
        }
        println(data)
        return part1(data)
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day01/Day01_test")
    println(part1(testInput))
    println(part2(testInput))

    val input = readInput("Day01/Day01")
    println(part1(input))
    println(part2(input))
}
