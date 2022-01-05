import Resources.readText
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

@DisplayName("Day 14")
class Day14Test {

    private val sampleInput = readText("Day14-sample.txt")
    private val actualInput = readText("Day14.txt")

    @Nested
    @DisplayName("Part 1")
    inner class Part1 {

        @Test
        fun `Matches example`() = assertEquals(
            expected = 1588,
            actual = Day14(sampleInput).part1(),
        )

        @Test
        fun `Actual answer`() = assertEquals(
            expected = 2587,
            actual = Day14(actualInput).part1(),
        )

    }

    @Nested
    @DisplayName("Part 2")
    inner class Part2 {

        @Test
        fun `Matches example`() = assertEquals(
            expected = 2188189693529,
            actual = Day14(sampleInput).part2(),
        )

        @Test
        fun `Actual answer`() = assertEquals(
            expected = 3318837563123,
            actual = Day14(actualInput).part2(),
        )

    }

}