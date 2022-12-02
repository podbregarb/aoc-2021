package Day10

import readInput

fun main() {
    var bracketPoints = mapOf(')' to 3, ']' to 57, '}' to 1197, '>' to 25137)
    var bracketPointsPart2 = mapOf(')' to 1, ']' to 2, '}' to 3, '>' to 4)
    var leftBrackets = listOf('(', '[', '{', '<')
    var rightBrackets = listOf(')', ']', '}', '>')
    var bracketPairs = mapOf('(' to ')', '[' to ']', '{' to '}', '<' to '>')
    val bracket1Pair = "()"
    val bracket2Pair = "[]"
    val bracket3Pair = "{}"
    val bracket4Pair = "<>"
    val bracketPairList = listOf<String>(bracket1Pair, bracket2Pair, bracket3Pair, bracket4Pair)

    fun removeAllBracketPairs(mutableInput: String): String {
        var mutableInput1 = mutableInput
        while (bracketPairList.any { mutableInput1.contains(it) }) {
            for (s in bracketPairList) {
                mutableInput1 = mutableInput1.replace(s, "")
            }
        }
        return mutableInput1
    }

    fun findIllegalBracket(input: String): Char {
        val mutableInput = removeAllBracketPairs(input)
        val map: Map<Int, Char> = rightBrackets.map { mutableInput.indexOf(it) to it }.toMap().filter { it.key != -1 }
        if (map.isNotEmpty()) {
            return map.get(map.keys.sorted().first())!!
        } else {
            return '0'
        }
    }

    fun part1(input: List<String>): Int {
        var list = input.map { findIllegalBracket(it) }.filter { it != '0' }
        var result = 0
        for (rightBracket in rightBrackets) {
            result += list.count { it == rightBracket } * bracketPoints.get(rightBracket)!!
        }
        return result
    }

    fun getOneRowScore(input: String): Long {
        val notClosedBrackets = removeAllBracketPairs(input)
        var result = 0L
        for (i in notClosedBrackets.length-1 downTo 0) {
            result *=5
            result += bracketPointsPart2.get(bracketPairs.get(notClosedBrackets[i]))!!
        }
        return result
    }

    fun part2(input: List<String>): Long {
        var results = mutableListOf<Long>()
        for (s in input) {
            if (findIllegalBracket(s) =='0') {
                results.add(getOneRowScore(s))
            }
        }
        results.sort()
        return results[results.size/2]
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day10/Data_test")
    val input = readInput("Day10/Data")
    println("1st part")
    println(part1(testInput))
    println(part1(input))

    println("2nd part")
      println(part2(testInput))
    println("224349074 too low")
      println(part2(input))
}
