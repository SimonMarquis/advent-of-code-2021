import Neighborhood.VonNeumann

class Day09(raw: List<String>) {

    private val input = raw.mapIndexed { y, line ->
        line.mapIndexed { x, height ->
            Point(x, y, height.toString().toInt())
        }.toTypedArray()
    }.toTypedArray().let(::HeightMap)

    fun part1(): Int = with(input) {
        points.filter { it.isLow() }
    }.sumOf { it.riskLevel }

    fun part2(): Int = with(input) {
        points.groupingBy { it.flood() }.eachCount()
    }.filterKeys { it != null }
        .entries.sortedByDescending { it.value }
        .take(3)
        .fold(1) { acc, next -> acc * next.value }

    class HeightMap(private val map: Array<Array<Point>>) {

        val points: List<Point> get() = map.flatten()

        operator fun get(x: Int, y: Int): Point = map[y][x]

        private fun Point.neighbours() = map.neighboursOf(VonNeumann, x, y)

        fun Point.isLow() = neighbours().all { height < it.height }

        fun Point.flood(seen: Set<Point> = setOf(this)): Point? {
            if (height == 9) return null
            if (isLow()) return this
            val candidates = neighbours().filter { it.height < height } - seen
            return candidates.firstNotNullOfOrNull { it.flood(seen + candidates) }
        }

    }

    data class Point(val x: Int, val y: Int, val height: Int) {
        val riskLevel = height.inc()
    }

}