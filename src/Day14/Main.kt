package Day14

import readInput

fun main() {

    fun getPolymer(input: List<String>): String {
        return input[0]
    }

    fun getPolymerPairs(input: List<String>): Map<String, Int> {
        var polymer = getPolymer(input)
        var result = mutableMapOf<String, Int>()
        for (i in 0 until polymer.length - 1) {
            var key = "${polymer[i]}${polymer[i + 1]}"
            if (result.containsKey(key)) {
                result.put(key, result.get(key)!! + 1)
            } else {
                result.put(key, 1)
            }
        }
        return result
    }

    fun getRules(input: List<String>): Map<String, String> {
        var rules = mutableMapOf<String, String>()
        for (s in input) {
            if (s.contains("->")) {
                var split = s.split(" -> ")
                rules.put(split[0], split[1])
            }
        }
        return rules
    }

    fun applyOneStep(polymer: String, rules: Map<String, String>): String {
        var newPolymer = ""
        for (i in 0 until polymer.length - 1) {
            var rule = rules.get("${polymer[i]}${polymer[i + 1]}")
            if (rule != null) {
                newPolymer += "${polymer[i]}${rule}"
            } else {
                newPolymer += "${polymer[i]}"
            }
        }
        newPolymer += polymer[polymer.length - 1]
        return newPolymer
    }

    fun doSteps(input: List<String>, numberOfSteps: Int): Int {
        var polymer = getPolymer(input)
        var rules = getRules(input)
        for (step in 0 until numberOfSteps) {
            polymer = applyOneStep(polymer, rules)
        }
        var map = polymer.map { it to polymer.count { char -> it == char } }.toMap()
        return map.values.maxOrNull()!! - map.values.minOrNull()!!
    }

    fun part1(input: List<String>): Int {
        return doSteps(input, 10)
    }

    fun addEntry(polymer: MutableMap<String, Int>, key: String) {
        if (polymer.containsKey(key)) {
            polymer.put(key, polymer.get(key)!! + 1)
        } else {
            polymer.put(key, 1)
        }
    }

    fun applyOneStepPart2(polymer: MutableMap<String, Int>, rules: Map<String, String>, occurences: MutableMap<String, Int>): MutableMap<String, Int> {
        for (entry in polymer) {
            var value = rules.get(entry.key)
            if (value != null) {
                var newPair1 = entry.key[0] + value
                var newPair2 = value + entry.key[1]
                addEntry(polymer, newPair1)
                addEntry(polymer, newPair2)
                addEntry(occurences, value)
            }
        }
        return polymer
    }

    fun part2(input: List<String>): Int {
        var polymer = getPolymer(input)
        var polymerPairs = getPolymerPairs(input).toMutableMap()
        var rules = getRules(input)
        var occurences = mutableMapOf<String, Int>()
        for (entry in polymer) {
            occurences.putIfAbsent(entry.toString(), polymer.count { it == entry })
        }
        for (step in 0 until 40) {
            polymerPairs = applyOneStepPart2(polymerPairs, rules, occurences)
        }
        return occurences.values.maxOrNull()!! - occurences.values.minOrNull()!!
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day14/Data_test")
    val input = readInput("Day14/Data")
    println("1st part")
    println(part1(testInput))
    println(part1(input))

    println("2nd part")
    println(part2(testInput))
    //println(part2(input))
}
