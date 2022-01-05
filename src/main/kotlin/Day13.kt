class Day13(raw: String) {

    private val input = raw.split("\n\n").let { (a, b) ->
        val points = a.lines().map {
            it.split(",").map { coords -> coords.toInt() }.let { (x, y) -> Point(x, y) }
        }.toSet()
        val folds = b.lines().map {
            it.split(" ", "=").takeLast(2).let { (d, c) -> Fold(d.toDir(), c.toInt()) }
        }
        points to folds
    }

    fun part1(): Int = input.let { (points, folds) ->
        points.fold(folds.first()).size
    }

    fun part2(): String = input.let { (points, folds) ->
        folds.fold(points) { pts, fold -> pts.fold(fold) }
    }.stringify()

    data class Point(val x: Int, val y: Int)

    data class Fold(val dir: Dir, val coordinate: Int)

    enum class Dir { X, Y }

    private fun String.toDir() = when (this) {
        "x" -> Dir.X
        "y" -> Dir.Y
        else -> TODO()
    }

    private fun Set<Point>.fold(fold: Fold) = map {
        when (fold.dir) {
            Dir.X -> if (it.x < fold.coordinate) it else it.copy(x = it.x - (it.x - fold.coordinate) * 2)
            Dir.Y -> if (it.y < fold.coordinate) it else it.copy(y = it.y - (it.y - fold.coordinate) * 2)
        }
    }.toSet()

    private fun Set<Point>.stringify(): String {
        val width = this@stringify.maxByOrNull { it.x }!!.x
        val height = this@stringify.maxByOrNull { it.y }!!.y
        return (0..height).joinToString(separator = "\n") { y ->
            (0..width).joinToString(separator = "") { x ->
                if (Point(x, y) in this@stringify) "█"
                else " "
            }
        }
    }

}
