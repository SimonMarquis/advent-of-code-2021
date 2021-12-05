@OptIn(ExperimentalStdlibApi::class)
class Day04(raw: List<String>) {

    private val input: Bingo = raw.iterator().toBingo()

    fun part1(): Int = input.findFirstBoardScore()

    fun part2(): Int = input.findLastBoardScore()

    data class Bingo(val numbers: List<Int>, val boards: List<Board>) {

        private val draws = numbers.indices.map { numbers.take(it + 1) }

        fun findFirstBoardScore(): Int = draws
            .firstNotNullOf { draw -> boards.find { board -> board.win(draw) }?.score(draw) }

        fun findLastBoardScore(): Int {
            val remainingBoards = boards.toMutableList()
            draws.forEach draws@{ draw ->
                val iterator = remainingBoards.listIterator()
                while (iterator.hasNext()) {
                    iterator.forEach boards@{
                        if (it.win(draw)) iterator.remove() else return@boards
                        if (remainingBoards.isEmpty()) return it.score(draw)
                    }
                }
            }
            throw IllegalStateException()
        }

    }

    class Board(input: List<List<Int>>) {
        private val rows = input
        private val columns = List(input.size) { index -> input.map { it[index] } }
        private val rowsAndColumns = rows + columns
        private val allNumbers = rowsAndColumns.flatten().toSet()

        fun win(draw: List<Int>): Boolean {
            rowsAndColumns.forEach { if (draw.containsAll(it)) return true }
            return false
        }

        fun score(draw: List<Int>): Int {
            val unmarkedNumbers = allNumbers - draw.toSet()
            return unmarkedNumbers.sum() * draw.last()
        }

    }

    private fun Iterator<String>.toBingo(): Bingo = Bingo(
        numbers = next().split(",").map(String::toInt),
        boards = buildList {
            while (hasNext()) {
                next() // newLine
                add(nextBoard())
            }
        },
    )

    private fun Iterator<String>.nextBoard(): Board = Board(buildList {
        repeat(5) { add(next().trim().split("\\s+".toRegex()).map(String::toInt)) }
    })

}
