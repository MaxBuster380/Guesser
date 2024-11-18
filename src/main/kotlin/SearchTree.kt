/*
 * MIT License
 *
 * Copyright (c) 2024 MaxBuster
 *
 * This is the "SearchTree.kt" file from the Guesser project.
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
 * # SearchTree
 *
 * @param T Leaf object type.
 *
 * @param rootNode First node of the
 */
data class SearchTree<T>(
    val rootNode: Node<T>
) {

    /**
     * # SearchTree.Node
     *
     * @param T
     */
    interface Node<T> {

        val remaining: Int
        val depth: Int
    }

    /**
     * # SearchTree.LeafNode
     *
     * @param T
     *
     * @param values
     */
    data class LeafNode<T>(
        val values: List<T>,
    ) : Node<T> {

        override val remaining: Int get() = values.size
        override val depth: Int get() = 0
    }

    /**
     * # SearchTree.SplitterNode
     *
     * @param T
     *
     * @param comparator
     * @param reference
     * @param lower
     * @param higherOrEqual
     */
    data class SplitterNode<T>(
        val comparator: Comparator<T>,
        val reference: T,
        val lower: Node<T>,
        val higherOrEqual: Node<T>,
        override val remaining: Int,
        override val depth: Int,
    ): Node<T>

    val depth: Int get() = rootNode.depth
}
