class Day05(raw: List<String>) {

    private val lines: List<Line> = raw.map(Line::parse)

    fun part1(): Int = lines
        .filter { it.isHorizontal() || it.isVertical() }
        .flatMap { it.points() }
        .groupingBy { it }
        .eachCount()
        .count { it.value >= 2 }

    fun part2(): Int = lines
        .flatMap { it.points() }
        .groupingBy { it }
        .eachCount()
        .count { it.value >= 2 }

    data class Point(val x: Int, val y: Int)

    data class Line(val src: Point, val dst: Point) {

        override fun toString(): String = "{$src -> $dst}"

        companion object {

            private val pattern = """(\d+),(\d+) -> (\d+),(\d+)""".toRegex()

            fun parse(string: String): Line {
                val (srcX, srcY, dstX, dstY) = pattern.find(string)!!.destructured
                return Line(
                    src = Point(x = srcX.toInt(), y = srcY.toInt()),
                    dst = Point(x = dstX.toInt(), y = dstY.toInt()),
                )
            }
        }

        fun isVertical(): Boolean = src.x == dst.x

        fun isHorizontal(): Boolean = src.y == dst.y

        fun points(): Sequence<Point> = sequence(src.x, dst.x)
            .zip(sequence(src.y, dst.y))
            .map { (x, y) -> Point(x, y) }

        private fun sequence(src: Int, dst: Int) = when {
            src < dst -> (src..dst).asSequence()
            src > dst -> (src downTo dst).asSequence()
            else -> generateSequence { src }
        }

    }

}