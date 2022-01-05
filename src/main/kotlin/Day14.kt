class Day14(raw: String) {

    private val input: Pair<String, Set<Rule>> = raw.split("\n\n").let { (polymer, rules) ->
        polymer to rules.lines().map { rule ->
            rule.split(" -> ").let { (i, o) -> Rule(i, o) }
        }.toSet()
    }

    fun part1(): Long = input.process(10)

    fun part2(): Long = input.process(40)

    private fun Pair<String, Set<Rule>>.process(step: Int) = let { (polymer, rules) ->
        val initial = polymer.windowed(2, partialWindows = true).groupingBy { it }.eachCount().mapValues { it.value.toLong() }
        (1..step).fold(initial) { acc, _ ->
            acc.step(rules)
        }.split().values.let {
            it.maxOrNull()!! - it.minOrNull()!!
        }
    }

    data class Rule(val input: String, val output: String)

    private fun Map<String, Long>.step(rules: Set<Rule>): Map<String, Long> = buildMap {
        this@step.entries.forEach { (k, v) ->
            when (val rule = rules.singleOrNull { k == it.input }) {
                null -> put(k, this[k] ?: v)
                else -> {
                    put(k, (this[k] ?: this@step[k] ?: 0) - v)
                    val left = rule.input.take(1) + rule.output
                    put(left, (this[left] ?: this@step[left] ?: 0) + v)
                    val right = rule.output + rule.input.takeLast(1)
                    put(right, (this[right] ?: this@step[right] ?: 0) + v)
                }
            }
        }
    }

    private fun Map<String, Long>.split(): Map<Char, Long> = buildMap {
        this@split.entries.forEach { (k, v) ->
            k.first().let { put(it, (this[it] ?: 0L) + v) }
        }
    }

}