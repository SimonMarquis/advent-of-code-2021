class Day08(raw: List<String>) {

    private val input: List<Entry> = raw.map { entry ->
        entry.split(" | ").map { it.split(" ") }
    }.map { (patterns, digits) -> Entry(Signal(patterns), Output(digits)) }


    data class Entry(val signal: Signal, val output: Output)
    data class Signal(val patterns: List<String>) : List<String> by patterns
    data class Output(val digits: List<String>) : List<String> by digits

    private val uniqueSignalsLength = listOf(
        2, // 1
        4, // 4
        3, // 7
        7, // 8
    )

    fun part1(): Int = input.sumOf { entry ->
        entry.output.count { it.length in uniqueSignalsLength }
    }

    fun part2(): Int = TODO()

}