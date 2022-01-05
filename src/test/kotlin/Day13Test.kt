import Resources.readText
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

@DisplayName("Day 13")
class Day13Test {

    private val sampleInput = readText("Day13-sample.txt")
    private val actualInput = readText("Day13.txt")

    @Nested
    @DisplayName("Part 1")
    inner class Part1 {

        @Test
        fun `Matches example`() = assertEquals(
            expected = 17,
            actual = Day13(sampleInput).part1(),
        )

        @Test
        fun `Actual answer`() = assertEquals(
            expected = 687,
            actual = Day13(actualInput).part1(),
        )

    }

    @Nested
    @DisplayName("Part 2")
    inner class Part2 {

        @Test
        fun `Matches example`() = assertEquals(
            expected = """
                █████
                █   █
                █   █
                █   █
                █████
            """.trimIndent(),
            actual = Day13(sampleInput).part2(),
        )

        @Test
        fun `Actual answer`() = assertEquals(
            expected = """
                ████  ██  █  █  ██  █  █ ███  ████  ██ 
                █    █  █ █ █  █  █ █ █  █  █    █ █  █
                ███  █    ██   █    ██   ███    █  █   
                █    █ ██ █ █  █    █ █  █  █  █   █ ██
                █    █  █ █ █  █  █ █ █  █  █ █    █  █
                █     ███ █  █  ██  █  █ ███  ████  ███
            """.trimIndent(),
            actual = Day13(actualInput).part2(),
        )

    }

}