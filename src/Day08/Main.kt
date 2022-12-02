package Day08

import readInput

fun main() {
    fun part1(input: List<String>): Int {
        var sum = 0
        for (s in input) {
            var split = s.split("|")[1].trim().split(" ")
            for (signal in split) {
                if (listOf<Int>(2, 3, 4, 7).contains(signal.length)) {
                    sum++
                }
            }
        }
        return sum
    }

    fun mapToNumber(signal: String): Int {
        var joinToString = signal.toCharArray().sorted().joinToString("")
        when (joinToString) {
            "abcdefg" -> return 8
            "bcdef" -> return 5
            "abcdg" -> return 2
            "abcfg" -> return 3
            "bfg" -> return 7
            "bcdefg" -> return 9
            "acdefg" -> return 6
            "bceg" -> return 4
            "abcdeg" -> return 0
            "cg" -> return 1
            else -> {
                throw Exception("Can not map signal ${signal} to number!")
            }
        }
    }

    fun getMapping(input: List<String>): MutableMap<String, Int> {
        var mapping = mutableMapOf<String, Int>()
        var one = ""
        var four = ""
        var seven = ""
        var eight = ""
        // Get base numbers
        for (signal in input) {
            if (signal.length == 2) {
                one = signal
                mapping.put(signal.toCharArray().sorted().joinToString(""), 1)
            } else if (signal.length == 4) {
                four = signal
                mapping.put(signal.toCharArray().sorted().joinToString(""), 4)
            } else if (signal.length == 3) {
                seven = signal
                mapping.put(signal.toCharArray().sorted().joinToString(""), 7)
            } else if (signal.length == 7) {
                eight = signal
                mapping.put(signal.toCharArray().sorted().joinToString(""), 8)
            }
        }
        // 6
        for (signal in input) {
            if (signal.length == 6) {
                if (one.toCharArray().filter{ !signal.toCharArray().contains(it) }.size == 1) {
                    mapping.put(signal.toCharArray().sorted().joinToString(""), 6)
                } else if (four.toCharArray().all { signal.contains(it) }) {
                    mapping.put(signal.toCharArray().sorted().joinToString(""), 9)
                } else {
                    mapping.put(signal.toCharArray().sorted().joinToString(""), 0)
                }
            }
        }
        for (signal in input) {
            if (signal.length == 5) {
                if (four.toCharArray().filter{ !signal.toCharArray().contains(it) }.size == 2) {
                    mapping.put(signal.toCharArray().sorted().joinToString(""), 2)
                } else {
                    if (one.toCharArray().all { signal.contains(it) }) {
                        mapping.put(signal.toCharArray().sorted().joinToString(""), 3)
                    } else {
                        mapping.put(signal.toCharArray().sorted().joinToString(""), 5)
                    }
                }
            }
        }
        //println(mapping)
        return mapping
    }

    fun part2(input: List<String>): Int {
        var sum = 0
        for (s in input) {
            var inputOutput = s.split("|")
            var inputSignal = inputOutput[0].trim().split(" ")
            val mapping = getMapping(inputSignal)
            var outputSignal = inputOutput[1].trim().split(" ")
            var numberAsString = ""
            for (signal in outputSignal) {
                numberAsString += mapping.get(signal.toCharArray().sorted().joinToString("")).toString()
            }
            //println(numberAsString)
            sum += numberAsString.toInt()
        }
        return sum
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day08/Data_test")
    val input = readInput("Day08/Data")
    println("1st part")
    println(part1(testInput))
    println(part1(input))

    println("2nd part")
    println(part2(testInput))
    println(part2(input))
}
