class Day10(raw: List<String>) {

    private val input: List<String> = raw.map { it.solve() }

    fun part1() = input.filter {
        it.any { c -> c in CTRL.keys }
    }.sumOf {
        it.first { c -> c in CTRL.keys }.toInvalidScore()
    }

    fun part2() = input.filter {
        it.none { c -> c in CTRL.keys }
    }.map {
        it.autocomplete().toAutocompleteScore()
    }.sortedBy {
        it
    }.let { it[it.size / 2] }

    private fun String.solve() = fold("") { acc, value ->
        when {
            value !in CTRL.keys -> acc + value
            CTRL[value] == acc.last() -> acc.dropLast(1)
            else -> acc + value
        }
    }

    private fun Char.toInvalidScore(): Long = when (this) {
        ')' -> 3L
        ']' -> 57L
        '}' -> 1197L
        '>' -> 25137L
        else -> TODO()
    }

    private fun String.autocomplete() = reversed().map { LRTC[it] }.joinToString(separator = "")

    private fun String.toAutocompleteScore() = fold(0L) { acc, value ->
        when (value) {
            ')' -> 1L
            ']' -> 2L
            '}' -> 3L
            '>' -> 4L
            else -> TODO()
        } + acc * 5
    }

    companion object {

        val CTRL = mapOf(
            ']' to '[',
            ')' to '(',
            '}' to '{',
            '>' to '<',
        )

        val LRTC = CTRL.map { (k, v) -> v to k }.toMap()

    }

}