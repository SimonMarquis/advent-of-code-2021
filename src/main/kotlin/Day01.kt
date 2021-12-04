class Day01(raw: List<String>) {

    private val input = raw.map(String::toInt)

    fun part1(): Int = input
        .zipWithNext()
        .count { (l, r) -> l < r }

    fun part2(): Int = input
        .windowed(size = 3) { it.sum() }
        .zipWithNext()
        .count { (l, r) -> l < r }

}