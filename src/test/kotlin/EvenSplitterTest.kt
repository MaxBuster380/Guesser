/*
 * MIT License
 *
 * Copyright (c) 2024 MaxBuster
 *
 * This is the "EvenSplitterTest.kt" file from the ListBrancher project.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

import com.github.MaxBuster380.EvenSplitter
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import kotlin.test.assertFails

class EvenSplitterTest {

    val intComparator = Comparator<Int> { p0, p1 -> p0.compareTo(p1) }

    @Test
    fun case1() {

        val list = listOf(0, 1, 1, 1, 1, 1, 2, 2, 2).shuffled()
        val expected = EvenSplitter.Result(
            lower = listOf(0, 1, 1, 1, 1, 1),
            higherOrEqual = listOf(2, 2, 2))
        val expectedReference = 2

        val actual = EvenSplitter<Int>().split(list, intComparator)
        assertEquals(expected, actual)
        assertEquals(expectedReference, actual.reference)
    }

    @Test
    fun case2() {

        val list = listOf(0, 1, 1, 1, 1, 2, 2, 2, 2).shuffled()
        val expected = EvenSplitter.Result(
            lower = listOf(0, 1, 1, 1, 1),
            higherOrEqual =  listOf(2, 2, 2, 2)
        )
        val expectedReference = 2

        val actual = EvenSplitter<Int>().split(list, intComparator)
        assertEquals(expected, actual)
        assertEquals(expectedReference, actual.reference)
    }

    @Test
    fun case3() {

        val list = listOf(0, 1, 1, 1, 1, 1, 1, 1, 1).shuffled()
        val expected = EvenSplitter.Result(
            lower = listOf(0),
            higherOrEqual =  listOf(1, 1, 1, 1, 1, 1, 1, 1)
        )
        val expectedReference = 1

        val actual = EvenSplitter<Int>().split(list, intComparator)
        assertEquals(expected, actual)
        assertEquals(expectedReference, actual.reference)
    }

    @Test
    fun case4() {

        val list = listOf(0, 0, 0, 0, 1, 1, 1, 1).shuffled()
        val expected = EvenSplitter.Result(
            lower = listOf(0, 0, 0, 0),
            higherOrEqual =  listOf(1, 1, 1, 1)
        )
        val expectedReference = 1

        val actual = EvenSplitter<Int>().split(list, intComparator)
        assertEquals(expected, actual)
        assertEquals(expectedReference, actual.reference)
    }

    @Test
    fun case5() {

        val list = listOf<Int>()

        assertFails {
            EvenSplitter<Int>().split(list, intComparator)
        }

    }

    @Test
    fun case6() {

        val list = listOf(1, 1, 1, 1, 1).shuffled()
        val expected = EvenSplitter.Result(
            lower = listOf(),
            higherOrEqual =  listOf(1, 1, 1, 1, 1)
        )
        val expectedReference = 1

        val actual = EvenSplitter<Int>().split(list, intComparator)
        assertEquals(expected, actual)
        assertEquals(expectedReference, actual.reference)
    }

    @Test
    fun case7() {

        val list = listOf(1).shuffled()
        val expected = EvenSplitter.Result(
            lower = listOf(),
            higherOrEqual = listOf(1)
        )
        val expectedReference = 1

        val actual = EvenSplitter<Int>().split(list, intComparator)
        assertEquals(expected, actual)
        assertEquals(expectedReference, actual.reference)
    }

    @Test
    fun case8() {

        val list = listOf(0, 0, 0, 1).shuffled()
        val expected = EvenSplitter.Result(
            lower = listOf(0, 0, 0),
            higherOrEqual =  listOf(1)
        )
        val expectedReference = 1

        val actual = EvenSplitter<Int>().split(list, intComparator)
        assertEquals(expected, actual)
        assertEquals(expectedReference, actual.reference)
    }

    @Test
    fun case9() {

        val list = listOf(0, 1).shuffled()
        val expected = EvenSplitter.Result(
            lower = listOf(0),
            higherOrEqual =  listOf(1)
        )
        val expectedReference = 1

        val actual = EvenSplitter<Int>().split(list, intComparator)
        assertEquals(expected, actual)
        assertEquals(expectedReference, actual.reference)
    }

    @Test
    fun case10() {

        val list = listOf(1, 1).shuffled()
        val expected = EvenSplitter.Result(
            lower = listOf(),
            higherOrEqual = listOf(1, 1)
        )
        val expectedReference = 1

        val actual = EvenSplitter<Int>().split(list, intComparator)
        assertEquals(expected, actual)
        assertEquals(expectedReference, actual.reference)
    }

    @Test
    fun case11() {

        val list = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9).shuffled()

        val expected = EvenSplitter.Result(
            lower = listOf(1, 2, 3, 4),
            higherOrEqual = listOf(5, 6, 7, 8, 9)
        )
        val expectedReference = 5

        val actual = EvenSplitter<Int>().split(list, intComparator)
        assertEquals(expected, actual)
        assertEquals(expectedReference, actual.reference)
    }
}