import Day02.Direction.*

class Day02(raw: List<String>) {

    private val input: Sequence<Pair<Direction, Int>> = raw.asSequence()
        .map { it.split(" ") }
        .map { (direction, amount) -> enumValueOf<Direction>(direction) to amount.toInt() }

    fun part1(): Int = input
        .fold(State()) { state, (direction, amount) ->
            when (direction) {
                down -> state.copy(depth = state.depth + amount)
                up -> state.copy(depth = state.depth - amount)
                forward -> state.copy(horizontal = state.horizontal + amount)
            }
        }.let { it.horizontal * it.depth }

    fun part2(): Int = input
        .fold(State()) { state, (direction, amount) ->
            when (direction) {
                down -> state.copy(aim = state.aim + amount)
                up -> state.copy(aim = state.aim - amount)
                forward -> state.copy(
                    horizontal = state.horizontal + amount,
                    depth = state.depth + state.aim * amount,
                )
            }
        }.let { it.horizontal * it.depth }

    @Suppress("EnumEntryName")
    enum class Direction { forward, down, up }

    data class State(val horizontal: Int = 0, val depth: Int = 0, val aim: Int = 0)

}