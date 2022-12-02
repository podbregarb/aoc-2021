package Day24

import readInput

private val INPUT = "inp w"

fun main() {
    fun getInstructions(input: List<String>): List<List<String>> {
        var mutableList = mutableListOf<MutableList<String>>()
        var mutableListOf = mutableListOf<String>()
        for (s in input) {
            if (s.equals(INPUT)) {
                if (mutableListOf.isNotEmpty()) {
                    mutableList.add(mutableListOf)
                }
                mutableListOf = mutableListOf<String>()
            } else {
                mutableListOf.add(s)
            }
        }
        mutableList.add(mutableListOf)
        return mutableList
    }

    fun getInteger(number2: String, z: Int, x: Int, y: Int, w: Int): Int {
        return when (number2) {
            "z" -> return z
            "x" -> return x
            "y" -> return y
            "w" -> return w
            else -> {
               return number2.toInt()
            }
        }
    }

    fun applyInstructions(list: List<String>, i: Int): Int {
        var toBinaryString = Integer.toBinaryString(i)
        var z = toBinaryString[toBinaryString.length-1].toString().toInt()
        var y = if (toBinaryString.length > 1) toBinaryString[toBinaryString.length-2].toString().toInt() else 0
        var x = if (toBinaryString.length > 2) toBinaryString[toBinaryString.length-3].toString().toInt() else 0
        var w = if (toBinaryString.length > 3) toBinaryString[toBinaryString.length-4].toString().toInt() else 0
        for (s in list) {
            var split = s.split(" ")
            var instruction = split[0]
            var number1 = split[1]
            var number2 = split[2]
            var integer = getInteger(number2, z, x, y, w)
            when (instruction) {
                "add" -> {
                    if (number1 == "z") {
                        z += integer
                    } else if (number1 == "y") {
                        y += integer
                    } else if (number1 == "x") {
                        x += integer
                    } else {
                        w += integer
                    }
                }
                "mul" -> {
                    if (number1 == "z") {
                        z *= integer
                    } else if (number1 == "y") {
                        y *= integer
                    } else if (number1 == "x") {
                        x *= integer
                    } else {
                        w *= integer
                    }
                }
                "div" -> {
                    if (number1 == "z") {
                        z /= integer
                    } else if (number1 == "y") {
                        y /= integer
                    } else if (number1 == "x") {
                        x /= integer
                    } else {
                        w /= integer
                    }
                }
                "mod" -> {
                    if (number1 == "z") {
                        z %= integer
                    } else if (number1 == "y") {
                        y %= integer
                    } else if (number1 == "x") {
                        x %= integer
                    } else {
                        w %= integer
                    }
                }
                "eql" -> {
                    if (number1 == "z") {
                        z = if (z == integer) 1 else 0
                    } else if (number1 == "y") {
                        y = if (y == integer) 1 else 0
                    } else if (number1 == "x") {
                        x = if (x == integer) 1 else 0
                    } else {
                        w = if (w == integer) 1 else 0
                    }
                }
                else -> {
                    throw Exception("Operation ${instruction} not supported!")
                }
            }
        }
        return z
    }

    fun part1(input: List<String>): Int {
        var instructions = getInstructions(input)
        var mutableListOf = mutableListOf<Int>()
        for (j in 0 until instructions.size) {
            println("j: ${j}")
            for (i in 1 until 10) {
                var applyInstructions = applyInstructions(instructions[j], i)
                println("applyInstructions: ${applyInstructions}")
                if (applyInstructions == 0) {
                    mutableListOf.add(i)
                }
                println("***************")
            }
            println("-------------------")
        }
        println("mutableListOf: ${mutableListOf}")
        return mutableListOf.joinToString("").toInt()
    }

    fun part2(input: List<String>): Int {
        return part1(input)
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day24/Data_test")
    val input = readInput("Day24/Data")
    println("1st part")
    println("part1(testInput)")
    println(part1(testInput))
    println("part1(input)")
    println(part1(input))

    println("2nd part")
    /*println(part2(testInput))
    println(part2(input))*/
}
