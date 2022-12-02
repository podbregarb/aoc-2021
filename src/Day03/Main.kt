package Day03

import readInput
import java.util.stream.Collectors

fun main() {

    fun countBits(input: List<String>, index: Int, bit: Char): Int {
        var count: Int = 0
        for (s in input) {
            if (s[index] == bit) {
                count++
            }
        }
        return count
    }

    fun getMostCommon(input: List<String>): String {
        var mostCommon = ""
        for (i in 0..(input.get(0).length-1)) {
            if (countBits(input, i, '1') > countBits(input, i, '0')) {
                mostCommon += '1'
            } else {
                mostCommon += '0'
            }
        }
        return mostCommon
    }

    fun getLeastCommon(input: List<String>): String {
        var leastCommon = ""
        for (i in 0..(input.get(0).length-1)) {
            if (countBits(input, i, '1') > countBits(input, i, '0')) {
                leastCommon += '0'
            } else {
                leastCommon += '1'
            }
        }
        return leastCommon
    }

    fun part1(input: List<String>): Int {
        val mostCommon = getMostCommon(input).toInt(2)
        val leastCommon = getLeastCommon(input).toInt(2)
        return mostCommon * leastCommon
    }

    fun getMostLeastCommon(input: List<String>): Int {
        var mostCommon = input.toMutableList()
        var leastCommon = input.toMutableList()
        for (i in 0..(input.get(0).length-1)) {
            if (mostCommon.size > 1) {
                if (countBits(mostCommon, i, '1') >= countBits(mostCommon, i, '0')) {
                    mostCommon.removeAll(input.stream().filter({ s -> s[i] == '0' }).collect(Collectors.toList()))
                } else {
                    mostCommon.removeAll(input.stream().filter({ s -> s[i] == '1' }).collect(Collectors.toList()))
                }
            }
            if (leastCommon.size > 1) {
                if (countBits(leastCommon, i, '1') >= countBits(leastCommon, i, '0')) {
                    leastCommon.removeAll(input.stream().filter({ s -> s[i] == '1' }).collect(Collectors.toList()))
                } else {
                    leastCommon.removeAll(input.stream().filter({ s -> s[i] == '0' }).collect(Collectors.toList()))
                }
            }
        }
        return mostCommon.get(0).toInt(2) * leastCommon.get(0).toInt(2)
    }

    fun part2(input: List<String>): Int {
        return getMostLeastCommon(input)
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day03/Data_test")
    val input = readInput("Day03/Data")
    println("1st part")
    println(part1(testInput))
    println(part1(input))

    println("2nd part")
    println(part2(testInput))
    println(part2(input))
}
