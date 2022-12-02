package Day13

import readInput
import java.awt.Point

private val FOLD = "fold along "

fun main() {
    fun getPointsFromInput(input: List<String>): MutableList<Point> {
        var points = mutableListOf<Point>()
        for (s in input) {
            if (!s.isEmpty() && !s.startsWith(FOLD)) {
                var split = s.split(",")
                points.add(Point(split[0].toInt(), split[1].toInt()))
            }
        }
        return points
    }

    fun getFolds(input: List<String>): List<String> {
        var folds = mutableListOf<String>()
        for (s in input) {
            if (!s.isEmpty() && s.startsWith(FOLD)) {
                folds.add(s.replace(FOLD, ""))
            }
        }
        return folds
    }

    fun countDots(mutableList: MutableList<Point>): Int {
        return mutableList.map { "${it.x},${it.y}" }.distinct().size
    }

    fun fold(input: MutableList<Point>, toInt: Int, direction: String): MutableList<Point> {
        for (point in input) {
            if (direction == "x") {
                if (point.x > toInt) {
                    point.translate(-2 * (point.x - toInt), 0)
                }
            } else {
                if (point.y > toInt) {
                    point.translate(0, -2 * (point.y - toInt))
                }
            }
        }
        return input
    }

    fun part1(input: List<String>): Int {
        var points = getPointsFromInput(input)
        var foldSplit = getFolds(input)[0].split("=")
        points = fold(points, foldSplit[1].toInt(), foldSplit[0])
        return countDots(points)
    }

    fun part2(input: List<String>): Int {
        var points = getPointsFromInput(input)
        for (fold in getFolds(input)) {
            var foldSplit = fold.split("=")
            points = fold(points, foldSplit[1].toInt(), foldSplit[0])
        }
        var maxXOrNull = points.map { it.x }.toList().maxOrNull()
        var maxYOrNull = points.map { it.y }.toList().maxOrNull()
        for (j in 0 until (maxYOrNull!! + 1)) {
            var row = ""
            for (i in 0 until (maxXOrNull!! + 1)) {
                var point = points.find { it.x == i && it.y == j }
                if (point != null) {
                    row += "#"
                } else {
                    row += "."
                }
            }
            println(row)
        }
        return part1(input)
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day13/Data_test")
    val input = readInput("Day13/Data")
    println("1st part")
    println(part1(testInput))
    println(part1(input))

    println("2nd part")
    println(part2(testInput))
    println("Wrong SKAUOFUO")
    println(part2(input))

}
