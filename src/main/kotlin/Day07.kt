import kotlin.math.abs

class Day07(raw: String) {

    private val input: List<Int> = raw.split(",").map(String::toInt).sorted()

    fun part1(): Int = (0..input.maxOrNull()!!).map { position ->
        input.fold(0) { acc, dst -> acc + abs(dst - position) }
    }.minOf { it }

    fun part2(): Int = (0..input.maxOrNull()!!).map { position ->
        input.fold(0) { acc, dst ->
            acc + abs(dst - position).let { (it * (it + 1)) / 2 }
        }
    }.minOf { it }

}
