import Resources.readLines
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

@DisplayName("Day 11")
class Day11Test {

    private val sampleInput = readLines("Day11-sample.txt")
    private val actualInput = readLines("Day11.txt")

    @Nested
    @DisplayName("Part 1")
    inner class Part1 {

        @Test
        fun `Matches example`() = assertEquals(
            expected = 1656,
            actual = Day11(sampleInput).part1(),
        )

        @Test
        fun `Actual answer`() = assertEquals(
            expected = 1729,
            actual = Day11(actualInput).part1(),
        )

    }

    @Nested
    @DisplayName("Part 2")
    inner class Part2 {

        @Test
        fun `Matches example`() = assertEquals(
            expected = 195,
            actual = Day11(sampleInput).part2(),
        )

        @Test
        fun `Actual answer`() = assertEquals(
            expected = 237,
            actual = Day11(actualInput).part2(),
        )

    }

}