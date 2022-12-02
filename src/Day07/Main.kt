package Day07

import readInput

fun main() {

    fun getFuelCost(input: List<Int>, finalPosition: Int): Long {
        var fuelCost = 0L
        for (i in input) {
            fuelCost += Math.abs(finalPosition - i)
        }
        return fuelCost
    }

    fun part1(input: List<String>): Long {
        var data = input[0].split(",").map { it.toInt() }.toList()
        var fuelCost = Long.MAX_VALUE
        for (s in data.minByOrNull { it }!! until data.maxByOrNull { it }!!) {
            var newFuelCost = getFuelCost(data, s)
            if (newFuelCost < fuelCost) {
                fuelCost = newFuelCost
            }
        }
        return fuelCost
    }

    fun getIncrementalFuelCost(input: List<Int>, finalPosition: Int): Long {
        var fuelCost = 0L
        for (i in input) {
            for (j in 1..Math.abs(finalPosition - i)) {
                fuelCost += j
            }
        }
        return fuelCost
    }

    fun part2(input: List<String>): Long {
        var data = input[0].split(",").map { it.toInt() }.toList()
        var fuelCost = Long.MAX_VALUE
        var finalPosition = 0
        for (s in data.minByOrNull { it }!! until data.maxByOrNull { it }!!) {
            var newFuelCost = getIncrementalFuelCost(data, s)
            if (newFuelCost < fuelCost) {
                fuelCost = newFuelCost
                finalPosition = s
            }
        }
        println(finalPosition)
        return fuelCost
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day07/Data_test")
    val input = readInput("Day07/Data")
    println("1st part")
    println(part1(testInput))
    println(part1(input))

    println("2nd part")
    println(part2(testInput))
    println(part2(input))
}
