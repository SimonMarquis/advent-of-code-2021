import Day03.Criteria.LEAST
import Day03.Criteria.MOST
import Day03.Fallback.ONES
import Day03.Fallback.ZEROS

class Day03(raw: List<String>) {

    private val input: List<String> = raw

    fun part1(): Int = input
        .map { number -> number.map { char -> char.toBit() } }
        .fold(IntArray(input.first().length)) { acc, bits ->
            bits.forEachIndexed { index, bit ->
                when (bit) {
                    false -> acc[index]--
                    true -> acc[index]++
                }
            }; acc
        }
        .reduceToBooleanArray()
        .let { it.epsilon() * it.gamma() }

    fun part2(): Int = input.oxygenGeneratorRating() * input.c02ScrubberRating()

    private fun Char.toBit() = when (this) {
        '1' -> true
        '0' -> false
        else -> throw IllegalStateException()
    }

    private fun BooleanArray.gamma() = toBinary()

    private fun BooleanArray.epsilon() = toBinary { !it }

    private fun BooleanArray.toBinary(
        eval: (Boolean) -> Boolean = { it }
    ) = joinToString(separator = "") { if (eval(it)) "1" else "0" }.toInt(2)

    private fun IntArray.reduceToBooleanArray(): BooleanArray = map {
        when {
            it > 0 -> true
            it < 0 -> false
            else -> throw IllegalStateException()
        }
    }.toBooleanArray()

    private fun List<String>.oxygenGeneratorRating() = findRating(criteria = MOST, fallback = ONES).toInt(2)

    private fun List<String>.c02ScrubberRating() = findRating(criteria = LEAST, fallback = ZEROS).toInt(2)

    private fun List<String>.findRating(criteria: Criteria, fallback: Fallback): String {
        var result = this
        for (index in first().indices) {
            result = result.filterBy(index, criteria, fallback)
            if (result.size == 1) return result.single()
        }
        throw IllegalStateException()
    }

    private fun List<String>.filterBy(
        index: Int,
        criteria: Criteria,
        fallback: Fallback,
    ): List<String> = partition {
        when (it[index]) {
            '1' -> true
            '0' -> false
            else -> throw IllegalStateException()
        }
    }.let { (ones, zeros) ->
        when {
            zeros.size > ones.size -> zeros to ones
            zeros.size < ones.size -> ones to zeros
            else -> return when (fallback) {
                ONES -> ones
                ZEROS -> zeros
            }
        }
    }.let { (mostCommon, leastCommon) ->
        when (criteria) {
            MOST -> mostCommon
            LEAST -> leastCommon
        }
    }

    private enum class Criteria { MOST, LEAST }
    private enum class Fallback { ONES, ZEROS }

}



