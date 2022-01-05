class Day12(raw: List<String>) {

    companion object {
        val START = Cave("start")
        val END = Cave("end")
    }

    private val input = raw.toGraph()

    fun part1(): Int = input.paths(allowTwoSmalls = false).size

    fun part2(): Int = input.paths(allowTwoSmalls = true).size

    @JvmInline
    value class Cave(private val name: String) {
        private val isSmall: Boolean get() = name.all { it.isLowerCase() }
        private val isBig: Boolean get() = name.all { it.isUpperCase() }

        private fun List<Cave>.hasLessThanTwoSmalls() = filter { it.isSmall }.let { smalls -> smalls.none { s -> smalls.count { it == s } > 1 } }

        fun isAllowedIn(path: List<Cave>, allowTwoSmalls: Boolean = false): Boolean = when {
            this == START -> false
            this.isBig -> true
            this.isSmall -> this !in path || allowTwoSmalls && path.hasLessThanTwoSmalls()
            else -> TODO()
        }
    }

    private fun List<Cave>.isComplete() = last() == END

    private fun List<String>.toGraph(): Map<Cave, Set<Cave>> = buildMap {
        this@toGraph.forEach { line ->
            val (src, dst) = line.split("-").map(::Cave)
            put(src, this[src].orEmpty() + dst)
            put(dst, this[dst].orEmpty() + src)
        }
    }

    private fun Map<Cave, Set<Cave>>.paths(allowTwoSmalls: Boolean): List<List<Cave>> {
        val paths = mutableListOf<List<Cave>>()
        val acc = mutableListOf(listOf(START))
        while (acc.isNotEmpty()) {
            val path = acc.removeLast()
            val reachableCaves = this[path.last()]?.filter { it.isAllowedIn(path, allowTwoSmalls) }.orEmpty()
            val newPaths = reachableCaves.map { path + it }
            val (completePaths, incompletePaths) = newPaths.partition { it.isComplete() }
            acc += incompletePaths
            paths += completePaths
        }
        return paths
    }

}