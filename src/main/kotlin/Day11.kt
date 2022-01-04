import Neighborhood.Moore

class Day11(val raw: List<String>) {

    private val cavern: Cavern get() = raw.toCavern()

    fun part1(): Int = with(cavern) {
        (1..100).sumOf { step() }
    }

    fun part2(): Int = with(cavern) {
        generateSequence(1) {
            it.inc()
        }.first {
            step() == octopuses.size
        }
    }

    class Cavern(val map: Array<Array<Octopus>>) {

        val octopuses = map.flatten()

        fun step(): Int {
            octopuses.forEach { it.energy++ }
            while (true) {
                val flashed = octopuses.filter {
                    it.energy > 9 && !it.flashed
                }.onEach { it.flash() }
                if (flashed.isEmpty()) break
            }
            return octopuses.filter {
                it.flashed
            }.onEach {
                it.energy = 0; it.flashed = false
            }.size
        }

        private fun Octopus.flash() = map.neighboursOf(Moore, x, y).forEach { it.energy++ }.also { flashed = true }

        override fun toString() = map.joinToString("\n") { line -> line.joinToString("") { it.energy.toString() } }

    }

    private fun List<String>.toCavern() = Cavern(mapIndexed { y, it -> it.toOctopuses(y) }.toTypedArray())

    data class Octopus(val x: Int, val y: Int, var energy: Int, var flashed: Boolean = false)

    private fun String.toOctopuses(y: Int) = mapIndexed { x, it -> it.toOctopus(x, y) }.toTypedArray()

    private fun Char.toOctopus(x: Int, y: Int) = Octopus(x, y, digitToInt())

}