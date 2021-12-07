import Resources.readText
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

@DisplayName("Day 07")
class Day07Test {

    private val sampleInput = readText("Day07-sample.txt")
    private val actualInput = readText("Day07.txt")

    @Nested
    @DisplayName("Part 1")
    inner class Part1 {

        @Test
        fun `Matches example`() = assertEquals(
            expected = 37,
            actual = Day07(sampleInput).part1(),
        )

        @Test
        fun `Actual answer`() = assertEquals(
            expected = 340056,
            actual = Day07(actualInput).part1(),
        )

    }

    @Nested
    @DisplayName("Part 2")
    inner class Part2 {

        @Test
        fun `Matches example`() = assertEquals(
            expected = 168,
            actual = Day07(sampleInput).part2(),
        )

        @Test
        fun `Actual answer`() = assertEquals(
            expected = 96592275,
            actual = Day07(actualInput).part2(),
        )

    }

}