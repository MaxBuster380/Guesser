/*
 * MIT License
 *
 * Copyright (c) 2024 MaxBuster
 *
 * This is the "Main.kt" file from the ListBrancher project.
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

package fr.maxbuster

fun main() {

    val list = listOf(0, 1, 2, 3, 4, 5, 6, 7, 8, 9)

    val valueComparator = object: Comparator<Int> {
        override fun compare(p0: Int, p1: Int): Int = p0.compareTo(p1)
        override fun toString(): String = "value"
    }

    val evenComparator = object: BooleanComparator<Int>() {
        override fun matches(element: Int): Boolean {
            return element % 2 == 0
        }
        override fun toString(): String = "even"
    }

    val searchTreeBuilder = SearchTreeBuilder(listOf(valueComparator))
    val searchTree = searchTreeBuilder.build(list)

    var node = searchTree.rootNode

    while(node !is SearchTree.LeafNode) {

        val splitter = node as SearchTree.SplitterNode

        println(splitter.comparator)
        println("? < ${splitter.reference}")
        println(node)

        val answer = readlnOrNull()

        if (answer != null) {

            if (answer == "y")
                node = splitter.lower

            if (answer == "n")
                node = splitter.higherOrEqual
        }
    }

    println("FOUND")

    println(node.values)
}