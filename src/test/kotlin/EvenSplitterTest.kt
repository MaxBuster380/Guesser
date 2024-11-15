import fr.maxbuster.EvenSplitter
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*
import kotlin.test.assertFails

class EvenSplitterTest {

    @Test
    fun case1() {

        val list = listOf(0, 1, 1, 1, 1, 1, 2, 2, 2)
        val expected = EvenSplitter.Result(
            reference = 2,
            lower = listOf(0, 1, 1, 1, 1, 1),
            higherOrEqual = listOf(2, 2, 2))

        val actual = EvenSplitter().split(list)
        assertEquals(expected, actual)
    }

    @Test
    fun case2() {

        val list = listOf(0, 1, 1, 1, 1, 2, 2, 2, 2)
        val expected = EvenSplitter.Result(
            reference = 2,
            lower = listOf(0, 1, 1, 1, 1),
            higherOrEqual =  listOf(2, 2, 2, 2)
        )

        val actual = EvenSplitter().split(list)
        assertEquals(expected, actual)
    }

    @Test
    fun case3() {

        val list = listOf(0, 1, 1, 1, 1, 1, 1, 1, 1)
        val expected = EvenSplitter.Result(
            reference = 1,
            lower = listOf(0),
            higherOrEqual =  listOf(1, 1, 1, 1, 1, 1, 1, 1)
        )

        val actual = EvenSplitter().split(list)
        assertEquals(expected, actual)
    }

    @Test
    fun case4() {

        val list = listOf(0, 0, 0, 0, 1, 1, 1, 1)
        val expected = EvenSplitter.Result(
            reference = 1,
            lower = listOf(0, 0, 0, 0),
            higherOrEqual =  listOf(1, 1, 1, 1)
        )

        val actual = EvenSplitter().split(list)
        assertEquals(expected, actual)
    }

    @Test
    fun case5() {

        val list = listOf<Int>()

        assertFails {
            EvenSplitter().split(list)
        }

    }

    @Test
    fun case6() {

        val list = listOf(1, 1, 1, 1, 1)
        val expected = EvenSplitter.Result(
            reference = 1,
            lower = listOf(),
            higherOrEqual =  listOf(1, 1, 1, 1, 1)
        )

        val actual = EvenSplitter().split(list)
        assertEquals(expected, actual)
    }

    @Test
    fun case7() {

        val list = listOf(0)
        val expected = EvenSplitter.Result(
            reference = 0,
            lower = listOf(),
            higherOrEqual =  listOf(0)
        )

        val actual = EvenSplitter().split(list)
        assertEquals(expected, actual)
    }

    @Test
    fun case8() {

        val list = listOf(0, 0, 0, 1)
        val expected = EvenSplitter.Result(
            reference = 1,
            lower = listOf(0, 0, 0),
            higherOrEqual =  listOf(1)
        )

        val actual = EvenSplitter().split(list)
        assertEquals(expected, actual)
    }

    @Test
    fun case9() {

        val list = listOf(0, 1)
        val expected = EvenSplitter.Result(
            reference = 1,
            lower = listOf(0),
            higherOrEqual =  listOf(1)
        )

        val actual = EvenSplitter().split(list)
        assertEquals(expected, actual)
    }

    @Test
    fun case10() {

        val list = listOf(0, 0)
        val expected = EvenSplitter.Result(
            reference = 0,
            lower = listOf(),
            higherOrEqual =  listOf(0, 0)
        )

        val actual = EvenSplitter().split(list)
        assertEquals(expected, actual)
    }

    @Test
    fun case11() {

        val list = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9)
        val expected = EvenSplitter.Result(
            reference = 5,
            lower = listOf(1, 2, 3, 4),
            higherOrEqual = listOf(5, 6, 7, 8, 9)
        )

        val actual = EvenSplitter().split(list)
        assertEquals(expected, actual)
    }
}