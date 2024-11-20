/*
 * MIT License
 *
 * Copyright (c) 2024 MaxBuster
 *
 * This is the "ComparatorChooser.kt" file from the Guesser project.
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

package com.github.MaxBuster380.comparatorchooser

import com.github.MaxBuster380.EvenSplitter

/**
 * # ComparatorChooser
 *
 * A comparator chooser analyses a list, chooses a comparator, applies the `EvenSplitter.split` method and returns both the comparator and the split result.
 *
 * @param T Lists' elements' type.
 *
 * @see EvenSplitter
 */
interface ComparatorChooser<T> {

    /**
     * # ComparatorChooser.get
     *
     * Picks the comparator to use for a given list, then returns it and the list split with it.
     *
     * @param objects List to find a comparator for.
     *
     * @return A pair of the chosen comparator and the resulting split with it.
     */
    fun get(objects: List<T>): Pair<Comparator<T>, EvenSplitter.Result<T>>
}