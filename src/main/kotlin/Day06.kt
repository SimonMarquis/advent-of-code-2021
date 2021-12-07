@OptIn(ExperimentalStdlibApi::class)
class Day06(raw: String) {

    private val input: Map<Int, Long> = raw.split(",").map(String::toInt).group()

    fun part1(): Long = input
        .steps(80)
        .values.sum()

    fun part2(): Long = input
        .steps(256)
        .values.sum()

    private fun List<Int>.group(): Map<Int, Long> = groupingBy { it }.eachCount().mapValues { it.value.toLong() }

    private fun Map<Int, Long>.steps(count: Int): Map<Int, Long> = (0 until count).fold(this) { acc, _ -> acc.step() }

    private fun Map<Int, Long>.step(): Map<Int, Long> = buildMap {
        this@step.entries.forEach { (key, value) ->
            when (key) {
                0 -> {
                    update(key = 6) { it + value }
                    update(key = 8) { it + value }
                }
                else -> update(key = key - 1) { it + value }
            }
        }
    }

    private fun <K> MutableMap<K, Long>.update(key: K, remap: (old: Long) -> Long) = compute(key) { _, old -> remap(old ?: 0) }

}
