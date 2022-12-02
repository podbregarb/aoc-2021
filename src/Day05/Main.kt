package Day05

import readInput
import java.util.stream.Collectors
import kotlin.math.max
import kotlin.math.min

fun main() {
    fun addToNumberOfCovers(numberOfCovers: MutableMap<String, Int>, s: String, s1: String) {
        val key = "${s},${s1}"
        if (numberOfCovers.containsKey(key)) {
            numberOfCovers.put(key, numberOfCovers.get(key)!! + 1)
        } else {
            numberOfCovers.put(key, 1)
        }
    }

    fun getNumberOfCoversMap(input: List<String>): Map<String, Int> {
        val numberOfCovers = mutableMapOf<String, Int>()
        for (s in input) {
            val split = s.split(" -> ")
            val from = split[0].split(",")
            val fromX = from[0].toInt()
            val fromY = from[1].toInt()
            val to = split[1].split(",")
            val toX = to[0].toInt()
            val toY = to[1].toInt()
            if (fromX == toX) {
                for (verticalIndex in min(fromY, toY) until max(fromY, toY) + 1) {
                    addToNumberOfCovers(numberOfCovers, fromX.toString(), verticalIndex.toString())
                }
            } else if (fromY == toY) {
                for (horizontalIndex in min(fromX, toX) until max(fromX, toX) + 1) {
                    addToNumberOfCovers(numberOfCovers, horizontalIndex.toString(), fromY.toString())
                }
            }
        }
        return numberOfCovers
    }

    fun part1(input: List<String>): Int {
        val numberOfCovers: Map<String, Int> = getNumberOfCoversMap(input)
        return numberOfCovers.values.stream().filter { it > 1 }.collect(Collectors.toList()).size
    }

    fun getNumberOfCoversMapPart2(input: List<String>): Map<String, Int> {
        val numberOfCovers = mutableMapOf<String, Int>()
        for (s in input) {
            val split = s.split(" -> ")
            val from = split[0].split(",")
            val fromX = from[0].toInt()
            val fromY = from[1].toInt()
            val to = split[1].split(",")
            val toX = to[0].toInt()
            val toY = to[1].toInt()
            if (fromX == toX) {
                for (verticalIndex in min(fromY, toY) until max(fromY, toY) + 1) {
                    addToNumberOfCovers(numberOfCovers, fromX.toString(), verticalIndex.toString())
                }
            } else if (fromY == toY) {
                for (horizontalIndex in min(fromX, toX) until max(fromX, toX) + 1) {
                    addToNumberOfCovers(numberOfCovers, horizontalIndex.toString(), fromY.toString())
                }
            }
            if (max(fromX, toX) - min(fromX, toX) == max(fromY, toY) - min(fromY, toY)) {
                if (fromX > toX) {
                    var index = 0
                    for (horizontalIndex in fromX downTo toX) {
                        var y = fromY
                        if (fromY < toY) {
                            y = fromY + index
                        } else {
                            y = fromY - index
                        }
                        addToNumberOfCovers(numberOfCovers, horizontalIndex.toString(), y.toString())
                        index++
                    }
                } else {
                    var index = 0
                    for (horizontalIndex in fromX until toX + 1) {
                        var y = fromY
                        if (fromY < toY) {
                            y = fromY + index
                        } else {
                            y = fromY - index
                        }
                        addToNumberOfCovers(numberOfCovers, horizontalIndex.toString(), y.toString())
                        index++
                    }
                }
            }
        }
        return numberOfCovers
    }

    fun part2(input: List<String>): Int {
        val numberOfCovers: Map<String, Int> = getNumberOfCoversMapPart2(input)
        return numberOfCovers.values.stream().filter { it > 1 }.collect(Collectors.toList()).size
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day05/Data_test")
    val input = readInput("Day05/Data")
    println("1st part")
    println(part1(testInput))
    println(part1(input))

    println("2nd part")
    println(part2(testInput))
    println(part2(input))
}
