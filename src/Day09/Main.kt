package Day09

import readInput

fun main() {
    fun isLowestPoint(point: Int, left: Int, right: Int, upper: Int, lower: Int): Boolean {
        return point < left && point < right && point < upper && point < lower
    }

    fun findLowestPoints(input: List<String>): List<Int> {
        var lowestPoints = mutableListOf<Int>()
        for (i in 0 until input.size) {
            for (j in 0 until input[0].length) {
                val point = input[i][j].toString().toInt()
                var left = if (j == 0) 10 else input[i][j - 1].toString().toInt()
                var right = if (j == input[0].length - 1) 10 else input[i][j + 1].toString().toInt()
                var upper = if (i == 0) 10 else input[i - 1][j].toString().toInt()
                var lower = if (i == input.size - 1) 10 else input[i + 1][j].toString().toInt()
                if (isLowestPoint(point, left, right, lower, upper)) {
                    lowestPoints.add(point)
                }
            }
        }
        return lowestPoints
    }

    fun part1(input: List<String>): Int {
        var findLowestPoints = findLowestPoints(input)
        return findLowestPoints.sum() + findLowestPoints.size
    }

    fun findBasinSize(input: List<String>, point: Int, i: Int, j: Int, visitedPoints: MutableList<String>, allVisitedPoints: MutableList<String>): Unit {
        val currentVisitedPoint = "${i},${j}"
        if (!allVisitedPoints.contains(currentVisitedPoint)) {
            visitedPoints.add(currentVisitedPoint)
            if (j != 0) {
                var newPoint = input[i][j - 1].toString().toInt()
                if (newPoint != 9 && newPoint == point + 1) {
                    findBasinSize(input, newPoint, i, j - 1, visitedPoints, allVisitedPoints)
                }
            }
            if (j != input[0].length - 1) {
                var newPoint = input[i][j + 1].toString().toInt()
                if (newPoint != 9 && newPoint == point + 1) {
                    findBasinSize(input, newPoint, i, j + 1, visitedPoints, allVisitedPoints)
                }
            }
            if (i != 0) {
                var newPoint = input[i - 1][j].toString().toInt()
                if (newPoint != 9 && newPoint == point + 1) {
                    findBasinSize(input, newPoint, i - 1, j, visitedPoints, allVisitedPoints)
                }
            }
            if (i != input.size - 1) {
                var newPoint = input[i + 1][j].toString().toInt()
                if (newPoint != 9 && newPoint == point + 1) {
                    findBasinSize(input, newPoint, i + 1, j, visitedPoints, allVisitedPoints)
                }
            }
        }
    }

    fun findBasinSizes(input: List<String>): List<Int> {
        var basinSizes = mutableListOf<Int>()
        val allVisitedPoints = mutableListOf<String>()
        for (i in 0 until input.size) {
            for (j in 0 until input[0].length) {
                val point = input[i][j].toString().toInt()
                var left = if (j == 0) 10 else input[i][j - 1].toString().toInt()
                var right = if (j == input[0].length - 1) 10 else input[i][j + 1].toString().toInt()
                var upper = if (i == 0) 10 else input[i - 1][j].toString().toInt()
                var lower = if (i == input.size - 1) 10 else input[i + 1][j].toString().toInt()
                val visitedPoints = mutableListOf<String>()
                if (isLowestPoint(point, left, right, lower, upper)) {
                    // Find basin size recursively
                    findBasinSize(input, point, i, j, visitedPoints, allVisitedPoints)
                    //println(visitedPoints.toMutableSet().size)
                    /* if (visitedPoints.toMutableSet().size == 82) {
                         println("${i},${j}")
                     }*/
                    if (visitedPoints.toMutableSet().size > 1) {
                        basinSizes.add(visitedPoints.toMutableSet().size)
                    }
                    //println(visitedPoints)
                }
                allVisitedPoints.addAll(visitedPoints)
            }
        }
        return basinSizes
    }

    fun part2(input: List<String>): Int {
        var basinSizes = findBasinSizes(input).sorted()
        return basinSizes.last() * basinSizes.get(basinSizes.size - 2) * basinSizes.get(basinSizes.size - 3)
    }

    fun part2Cheat(lines: List<String>): Int {
        val basins = mutableListOf<Int>()
        val visited = Array(lines.size) { i ->
            BooleanArray(lines[i].length) { j ->
                val c = lines[i][j]
                !c.isDigit() || c.digitToInt() >= 9
            }
        }
        lines.forEachIndexed { i, line ->
            line.forEachIndexed inner@{ j, c ->
                if (visited[i][j] || !c.isDigit() || c.digitToInt() >= 9) return@inner
                visited[i][j] = true
                var basin = 0
                val stack = mutableListOf(i to j)
                while (true) {
                    val (i2, j2) = stack.removeLastOrNull() ?: break
                    basin++
                    CardinalDirection.forEach(i2, j2) { i3, j3 ->
                        if (i3 in visited.indices && j3 in visited[i3].indices && !visited[i3][j3]) {
                            visited[i3][j3] = true
                            stack.add(i3 to j3)
                        }
                    }
                }
                basins.add(basin)
            }
        }
        basins.sort()
        return basins.asReversed().take(3).fold(1) { x, y -> x * y }
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day09/Data_test")
    val input = readInput("Day09/Data")
    println("1st part")
    println(part1(testInput))
    println(part1(input))

    println("2nd part")
    println(part2(testInput))
    println("Too low 435666")
    println(part2(input))
    println(part2Cheat(input))

}
