package Day02

import readInput
import java.awt.Point

fun main() {
    fun move(point: Point, movement: String): Unit {
        val split = movement.split(" ")
        var moveTo = split[0]
        val movement = split[1].toInt()
        when (moveTo) {
            "forward" -> point.translate(movement, 0)
            "up" -> point.translate(0, -movement)
            "down" -> point.translate(0, movement)
            else -> {
                throw Exception("Illegal movement $moveTo")
            }
        }
    }

    fun part1(input: List<String>): Int {
        val point: Point = Point(0, 0);
        for (movement in input) {
            move(point, movement)
        }
        return point.x * point.y
    }

    fun transform(triple: Triple<Int, Int, Int>, movement: String): Triple<Int, Int, Int> {
        val split = movement.split(" ")
        var moveTo = split[0]
        val movement = split[1].toInt()
        when (moveTo) {
            "forward" -> return Triple(triple.first + movement, triple.second + (triple.third * movement), triple.third)
            "up" -> return Triple(triple.first, triple.second, triple.third - movement)
            "down" -> return Triple(triple.first, triple.second, triple.third + movement)
            else -> {
                throw Exception("Illegal movement $moveTo")
            }
        }
    }

    fun part2(input: List<String>): Int {
        var triple: Triple<Int, Int, Int> = Triple(0, 0, 0);
        for (movement in input) {
            triple = transform(triple, movement)
        }
        return triple.first * triple.second
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day02/Day02_test")
    println(part1(testInput))
    println(part2(testInput))

    val input = readInput("Day02/Day02")
      println(part1(input))
      println(part2(input))
}
