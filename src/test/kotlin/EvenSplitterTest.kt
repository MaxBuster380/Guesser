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

import fr.maxbuster.EvenSplitter
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
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