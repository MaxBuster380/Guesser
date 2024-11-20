/*
 * MIT License
 *
 * Copyright (c) 2024 MaxBuster
 *
 * This is the "EvenSplitter.kt" file from the Guesser project.
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

@file:Suppress("PackageName")
package com.github.MaxBuster380

/**
 * # EvenSplitter
 *
 * Splits any list most evenly.
 *
 * @param T Lists' element type.
 */
class EvenSplitter<T> {

    /**
     * # EvenSplitter.Result
     *
     * Output of the `EvenSplitter.split` methods.
     *
     * @param T Lists' elements' type.
     *
     * @param lower Elements from the initial list that are strictly smaller than the `reference` by the comparator.
     * @param higherOrEqual Elements from the initial list that are bigger than or equal to the `reference` by the comparator.
     *
     * @see split
     */
    data class Result<T> (
        val lower: List<T>,
        val higherOrEqual: List<T>
    ) {

        /**
         * # EvenSplitter.Result.reference
         *
         * Element from the initial list to compare elements with.
         */
        val reference: T get() = higherOrEqual.first()

        /**
         * # EvenSplitter.Result.score
         *
         * The score of an Even Split result is a value rating how close to half split the operation got.
         * If either `lower` or `higherOrEqual` is empty, the score is 0.
         * If `lower` and `higherOrEqual` are the same size, the score is 1.
         *
         * @return The split result's score.
         */
        fun score(): Float {

            val (smallest, biggest) = if (lower.size < higherOrEqual.size) {
                lower to higherOrEqual
            } else {
                higherOrEqual to lower
            }

            return smallest.size.toFloat() / biggest.size
        }
    }

    /**
     * # EvenSplitter.split
     *
     * Based on the `comparator`, places each element of the `list` into one of two lists,
     * where the first list contains elements smaller than a reference object
     * and the second list contains elements bigger or equal to that same reference object.
     * The reference object is chosen so that the sizes of the two lists are as close as possible.
     *
     * - The reference object is taken from the initial `list`.
     * - The two lists concatenated are a permutation of the initial `list`, but do not necessarily give the initial `list`.
     *
     * @param list List of objects to split.
     * @param comparator Comparator by which to divide the list's element by.
     *
     * @return A EvenSplitter.Result instance.
     *
     * @see Result
     */
    fun split(list: List<T>, comparator: Comparator<T>): Result<T> {

        if (list.isEmpty())
            throw Exception("list is empty.")

        val sortedList = list.sortedWith(comparator)

        val referenceIndex = findReferenceIndex(sortedList, comparator)

        return Result(
            lower = sortedList.subList(0, referenceIndex),
            higherOrEqual = sortedList.subList(referenceIndex, sortedList.size)
        )
    }

    /**
     * # EvenSplitter.findReferenceIndex
     *
     * By the `comparator`, finds the index of the element in the `sortedList` that best splits the list.
     *
     * @param sortedList List of elements to split, sorted by the `comparator`.
     * @param comparator Comparator by which to divide the list's element by.
     *
     * @return The index of the element by which the list is best split by.
     */
    private fun findReferenceIndex(sortedList: List<T>, comparator: Comparator<T>): Int {

        val middle = sortedList[sortedList.size / 2]
        val lowestIndex = firstOccurrenceBinarySearch(sortedList, comparator, middle)
        val highestIndex = lastOccurrenceBinarySearch(sortedList, comparator, middle)

        val listIsUniform = lowestIndex == 0 && highestIndex == sortedList.lastIndex
        val lowestIsBestSplitter = (sortedList.lastIndex - highestIndex) <= lowestIndex
        val referenceIndex = if (listIsUniform || lowestIsBestSplitter) {
            lowestIndex
        } else {
            highestIndex + 1
        }

        return referenceIndex
    }

    @Suppress("KDocUnresolvedReference")
    /**
     * # EvenSplitter.firstOccurrenceBinarySearch
     *
     * Finds the lowest index for which the `target` object matches the element in the `list` based on the given `comparator`.
     * Assumes that the `list` is already sorted by the `comparator`. Assumes that such a match exists in the list.
     *
     * @param T `list`'s element type.
     *
     * @param list List of elements to look into, sorted by the `comparator`.
     * @param comparator Comparator by which the `list` is sorted and by which `target` must match.
     * @param target Object template to find the first occurrence of.
     *
     * @return The lowest index for which `comparator.compare(list[index], target) = 0`.
     */
    private fun firstOccurrenceBinarySearch(list: List<T>, comparator: Comparator<T>, target: T): Int {

        if (comparator.compare(list.first(), target) == 0)
            return 0

        var lowerBound = -1
        var upperBound = list.lastIndex + 1

        while (lowerBound < upperBound - 1) {

            val middleIndex = (lowerBound + upperBound) / 2
            val middle = list[middleIndex]

            if (comparator.compare(middle, target) < 0) {
                lowerBound = middleIndex
            } else if (comparator.compare(middle, target) > 0) {
                upperBound = middleIndex
            } else if (lowerBound != middleIndex) {
                upperBound = middleIndex
            } else {
                return middleIndex
            }
        }

        return lowerBound + 1
    }

    @Suppress("KDocUnresolvedReference")
    /**
     * # EvenSplitter.lastOccurrenceBinarySearch
     *
     * Finds the highest index for which the `target` object matches the element in the `list` based on the given `comparator`.
     * Assumes that the `list` is already sorted by the `comparator`. Assumes that such a match exists in the list.
     *
     * @param T `list`'s element type.
     *
     * @param list List of elements to look into, sorted by the `comparator`.
     * @param comparator Comparator by which the `list` is sorted and by which `target` must match.
     * @param target Object template to find the first occurrence of.
     *
     * @return The highest index for which `comparator.compare(list[index], target) = 0`.
     */
    private fun lastOccurrenceBinarySearch(list: List<T>, comparator: Comparator<T>, target: T): Int {

        if (comparator.compare(list.last(), target) == 0)
            return list.lastIndex

        var lowerBound = -1
        var upperBound = list.lastIndex + 1

        while (lowerBound < upperBound - 1) {

            val middleIndex = (lowerBound + upperBound) / 2
            val middle = list[middleIndex]

            if (comparator.compare(middle, target) < 0) {
                lowerBound = middleIndex
            } else if (comparator.compare(middle, target) > 0) {
                upperBound = middleIndex
            } else if (upperBound != middleIndex) {
                lowerBound = middleIndex
            } else {
                return middleIndex
            }
        }

        return lowerBound
    }
}