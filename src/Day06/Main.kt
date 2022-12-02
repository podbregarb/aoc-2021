package Day06

import readInput

fun main() {
    fun getNumberOfLanternFish(input: List<String>, numberOfDays: Int): Int {
        var list: MutableList<Int> = input[0].split(",").map { it.toInt() }.toMutableList()
        for (i in 1..numberOfDays) {
            var newList: MutableList<Int> = mutableListOf()
            var numberOfNewLanternFish = 0
            for (j in 0..list.size - 1) {
                if (list[j] == 0) {
                    newList.add(6)
                    numberOfNewLanternFish++
                } else {
                    newList.add(list[j] - 1)
                }
            }
            for (i in 0..numberOfNewLanternFish - 1) {
                newList.add(8)
            }
            list = newList
        }
        return list.size
    }

    fun getNumberOfLanternFishOptimized(input: List<String>, numberOfDays: Int): Long {
        var list: List<Long> = input[0].split(",").map{ it.toLong()}
        var map: MutableMap<Long, Long> = mutableMapOf()
        for (j in 0L..8L) {
            map.put(j, list.count { it == j }.toLong())
        }
        for (i in 1..numberOfDays) {
            val numberOfZeros = map.get(0)
            for (j in 0L..7L) {
                map.put(j, map.get(j+1)!!)
            }
            map.put(6, map.get(6)!! + numberOfZeros!!)
            map.put(8, numberOfZeros)
        }
        var sum = 0L
        for (j in 0L..8L) {
            sum += map.get(j)!!
        }
        return sum
    }

    fun part1(input: List<String>): Int {
        return getNumberOfLanternFish(input, 80)
    }

    fun part2(input: List<String>): Long {
        return getNumberOfLanternFishOptimized(input, 256)
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day06/Data_test")
    val input = readInput("Day06/Data")
    println("1st part")
    println(part1(testInput))
    println(part1(input))

    println("2nd part")
    println(part2(testInput))
    println(part2(testInput) == 26984457539)
    println("too low 1893795463")
    println(part2(input))
}
