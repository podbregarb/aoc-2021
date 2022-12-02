package Day04

import readInput
import java.util.stream.Collectors

fun main() {

    fun getDrawnNumbers(input: List<String>): List<Int> {
        return input.get(0).split(",").stream().map { it.toInt() }.collect(Collectors.toList())
    }

    fun getBingoBoards(input: List<String>): MutableList<MutableList<List<Int>>> {
        var mutableList = input.toMutableList()
        var boards = mutableListOf<MutableList<List<Int>>>()
        mutableList.removeAt(0)
        for (i in 0 until mutableList.size step 6) {
            var board: MutableList<List<Int>> = mutableListOf()
            (1..5).forEach {
                var row: MutableList<Int> = mutableListOf()
                for (index in 0 until mutableList[it + i].length step 3) {
                    row.add(mutableList[it + i].substring(index, index + 2).trim().toInt())
                }
                board.add(row)
            }
            boards.add(board)
        }
        return boards
    }

    fun getLowestWinningIndex(row: List<Int>, drawnNumbers: List<Int>): Int {
        for (i in 0..drawnNumbers.size - 1) {
            if (drawnNumbers.subList(0, i).containsAll(row)) {
                return i - 1
            }
        }
        return Int.MAX_VALUE
    }

    fun part1(input: List<String>): Int {
        val drawnNumbers: List<Int> = getDrawnNumbers(input)
        val bingoBoards = getBingoBoards(input)
        var winningIndex = Int.MAX_VALUE
        var winningBoard: MutableList<List<Int>> = mutableListOf()
        for (bingoBoard in bingoBoards) {
            // get when board wins (for every row and column), override in case it wins in lower steps than the current one, if it doesn't or needs more
            // steps, then do not override
            for (row in bingoBoard) {
                var newWinningIndex = getLowestWinningIndex(row, drawnNumbers)
                if (newWinningIndex < winningIndex) {
                    winningIndex = newWinningIndex
                    winningBoard = bingoBoard
                }
            }
            for (index in 0..bingoBoard.size - 1) {
                var newWinningIndex = getLowestWinningIndex(bingoBoard.stream().map { it[index] }.collect(Collectors.toList()), drawnNumbers)
                if (newWinningIndex < winningIndex) {
                    winningIndex = newWinningIndex
                    winningBoard = bingoBoard
                }
            }
        }

        val winningNumbers = drawnNumbers.subList(0, winningIndex + 1)
        var sum = 0
        for (row in winningBoard) {
            sum += row.stream().filter { !winningNumbers.contains(it) }.collect(Collectors.toList()).sum()
        }
        return sum * drawnNumbers[winningIndex]
    }

    fun part2(input: List<String>): Int {
        val drawnNumbers: List<Int> = getDrawnNumbers(input)
        val bingoBoards = getBingoBoards(input)
        var winningIndex = 0
        var winningBoard: MutableList<List<Int>> = mutableListOf()
        for (bingoBoard in bingoBoards) {
            // get when board wins (for every row and column), override in case it wins in higher steps than the current one, if it doesn't or needs less
            // steps, then do not override
            var bingoBoardIndex = Int.MAX_VALUE
            for (row in bingoBoard) {
                var newWinningIndex = getLowestWinningIndex(row, drawnNumbers)
                if (newWinningIndex < bingoBoardIndex) {
                    bingoBoardIndex = newWinningIndex
                }
            }
            for (index in 0..bingoBoard.size - 1) {
                var newWinningIndex = getLowestWinningIndex(bingoBoard.stream().map { it[index] }.collect(Collectors.toList()), drawnNumbers)
                if (newWinningIndex < bingoBoardIndex) {
                    bingoBoardIndex = newWinningIndex
                }
            }
            if (bingoBoardIndex > winningIndex) {
                winningIndex = bingoBoardIndex
                winningBoard = bingoBoard
            }
        }

        val winningNumbers = drawnNumbers.subList(0, winningIndex + 1)
        var sum = 0
        for (row in winningBoard) {
            sum += row.stream().filter { !winningNumbers.contains(it) }.collect(Collectors.toList()).sum()
        }
        return sum * drawnNumbers[winningIndex]
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day04/Data_test")
    val input = readInput("Day04/Data")
    println("1st part")
    println(part1(testInput))
    println(part1(input))

    println("2nd part")
    println(part2(testInput))
    println(part2(input))
}
