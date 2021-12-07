import Resources.readText
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

@DisplayName("Day 06")
class Day06Test {

    private val sampleInput = readText("Day06-sample.txt")
    private val actualInput = readText("Day06.txt")

    @Nested
    @DisplayName("Part 1")
    inner class Part1 {

        @Test
        fun `Matches example`() = assertEquals(
            expected = 5934,
            actual = Day06(sampleInput).part1(),
        )

        @Test
        fun `Actual answer`() = assertEquals(
            expected = 373378,
            actual = Day06(actualInput).part1(),
        )

    }

    @Nested
    @DisplayName("Part 2")
    inner class Part2 {

        @Test
        fun `Matches example`() = assertEquals(
            expected = 26984457539,
            actual = Day06(sampleInput).part2(),
        )

        @Test
        fun `Actual answer`() = assertEquals(
            expected = 1682576647495,
            actual = Day06(actualInput).part2(),
        )

    }

}