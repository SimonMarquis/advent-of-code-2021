import Neighborhood.VonNeumann
import kotlin.math.abs


enum class Neighborhood { VonNeumann, Moore }

fun <T> Array<Array<T>>.neighboursOf(neighborhood: Neighborhood, x: Int, y: Int) = buildSet<T> {
    (-1..1).forEach { dy ->
        (-1..1).forEach { dx ->
            when {
                dx == 0 && dy == 0 -> Unit
                neighborhood == VonNeumann && abs(dx) == abs(dy) -> Unit
                else -> getOrNull(y + dy)?.getOrNull(x + dx)?.let(::add)
            }
        }
    }
}
