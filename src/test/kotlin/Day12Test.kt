import Resources.readLines
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

@DisplayName("Day 12")
class Day12Test {

    private val sampleInput = readLines("Day12-sample.txt")
    private val actualInput = readLines("Day12.txt")

    @Nested
    @DisplayName("Part 1")
    inner class Part1 {

        @Test
        fun `Matches example`() = assertEquals(
            expected = 226,
            actual = Day12(sampleInput).part1(),
        )

        @Test
        fun `Actual answer`() = assertEquals(
            expected = 5228,
            actual = Day12(actualInput).part1(),
        )

    }

    @Nested
    @DisplayName("Part 2")
    inner class Part2 {

        @Test
        fun `Matches example`() = assertEquals(
            expected = 3509,
            actual = Day12(sampleInput).part2(),
        )

        @Test
        fun `Actual answer`() = assertEquals(
            expected = 131228,
            actual = Day12(actualInput).part2(),
        )

    }

}