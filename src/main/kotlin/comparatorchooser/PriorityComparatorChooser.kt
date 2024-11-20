/*
 * MIT License
 *
 * Copyright (c) 2024 MaxBuster
 *
 * This is the "PriorityComparatorChooser.kt" file from the Guesser project.
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

package com.github.MaxBuster380.comparatorchooser

import com.github.MaxBuster380.EvenSplitter

class PriorityComparatorChooser<T>(
    private val priority: ComparatorChooser<T>,
    private val secondary: ComparatorChooser<T>,
) : ComparatorChooser<T> {

    constructor(
        priority: List<Comparator<T>>,
        secondary: ComparatorChooser<T>,
    ) : this(
        priority = BestSplitterComparatorChooser(priority),
        secondary = secondary
    )

    constructor(
        priority: ComparatorChooser<T>,
        secondary: List<Comparator<T>>,
    ) : this(
        priority = priority,
        secondary = BestSplitterComparatorChooser(secondary)
    )

    constructor(
        priority: List<Comparator<T>>,
        secondary: List<Comparator<T>>,
    ) : this(
        priority = BestSplitterComparatorChooser(priority),
        secondary = BestSplitterComparatorChooser(secondary)
    )

    override fun get(objects: List<T>): Pair<Comparator<T>, EvenSplitter.Result<T>> {

        val priorityResult = priority.get(objects)

        if (priorityResult.second.failsSplit())
            return priorityResult

        return secondary.get(objects)
    }
}