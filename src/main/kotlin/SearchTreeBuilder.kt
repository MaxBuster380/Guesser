/*
 * MIT License
 *
 * Copyright (c) 2024 MaxBuster
 *
 * This is the "SearchTreeBuilder.kt" file from the ListBrancher project.
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
 * # SearchTreeBuilder
 *
 * @param T
 *
 * @param comparators
 */
class SearchTreeBuilder<T>(
    private val comparators: List<Comparator<T>>
) {

    /**
     * # SearchTreeBuilder.BuilderNode
     *
     * @param T List's element type.
     *
     * @param list List to attempt to split.
     */
    private data class BuilderNode<T>(
        val list: List<T>
    ): SearchTree.Node<T>

    /**
     * # SearchTreeBuilder.BuilderSplitterNode
     *
     * @param T List's element type.
     *
     * @param comparator Comparator used to divide the list by.
     * @param reference
     * @param lowerNodeIndex Index of the "lower" node in the builder tree.
     * @param higherOrEqualNodeIndex Index of the "higher or equal" node in the builder tree.
     */
    private data class BuilderSplitterNode<T> (
        val comparator: Comparator<T>,
        val reference: T,
        val lowerNodeIndex: Int,
        val higherOrEqualNodeIndex: Int
    ): SearchTree.Node<T>

    /**
     * # SearchTreeBuilder.build
     *
     * @param objects
     *
     * @return
     */
    fun build(objects: List<T>): SearchTree<T> {

        val builderTree = treeCreator(objects)

        return treeCleaner(builderTree)
    }

    /**
     * # SearchTreeBuilder.treeCreator
     *
     * @param initialList
     *
     * @return
     */
    private fun treeCreator(initialList: List<T>): MutableList<SearchTree.Node<T>> {

        val builderTree = mutableListOf<SearchTree.Node<T>>()

        builderTree += BuilderNode(initialList)

        var nodeIndex = 0

        while(nodeIndex < builderTree.size) {

            completeNode(builderTree, nodeIndex)

            nodeIndex += 1
        }

        return builderTree
    }

    /**
     * # SearchTreeBuilder.completeNode
     *
     * @param builderTree
     * @param nodeIndex
     */
    private fun completeNode(builderTree: MutableList<SearchTree.Node<T>>, nodeIndex: Int) {

        val currentNode = builderTree[nodeIndex]

        if (currentNode !is BuilderNode) return

        if (currentNode.list.size <= 1) return

        val result = findBestResult(currentNode.list)

        val listIsUniform = result.second.score() == 0f
        if (listIsUniform) return

        builderTree[nodeIndex] = BuilderSplitterNode(
            comparator = result.first,
            reference = result.second.reference,
            lowerNodeIndex = builderTree.size,
            higherOrEqualNodeIndex = builderTree.size + 1
        )

        builderTree += BuilderNode(result.second.lower)
        builderTree += BuilderNode(result.second.higherOrEqual)
    }

    /**
     * # SearchTreeBuilder.treeCleaner
     *
     * @param builderTree
     *
     * @return
     */
    private fun treeCleaner(builderTree: MutableList<SearchTree.Node<T>>): SearchTree<T> {

        var nodeIndex = builderTree.lastIndex

        while(nodeIndex >= 0) {

            cleanNode(builderTree, nodeIndex)

            nodeIndex -= 1
        }

        return SearchTree(builderTree.first())
    }

    /**
     * # SearchTreeBuilder.cleanNode
     *
     * @param builderTree
     * @param nodeIndex
     *
     * @return
     */
    private fun cleanNode(builderTree: MutableList<SearchTree.Node<T>>, nodeIndex: Int) {

        val currentNode = builderTree[nodeIndex]

        if (currentNode is BuilderNode) {

            builderTree[nodeIndex] = SearchTree.LeafNode(
                values = currentNode.list
            )
        }

        if (currentNode is BuilderSplitterNode) {

            builderTree[nodeIndex] = SearchTree.SplitterNode(
                comparator = currentNode.comparator,
                reference = currentNode.reference,
                lower = builderTree[currentNode.lowerNodeIndex],
                higherOrEqual = builderTree[currentNode.higherOrEqualNodeIndex],
            )
        }
    }

    /**
     * # SearchTreeBuilder.findBestResult
     *
     * @param objects
     *
     * @return
     */
    private fun findBestResult(objects: List<T>): Pair<Comparator<T>, EvenSplitter.Result<T>> {

        val evenSplitter = EvenSplitter<T>()

        val res = comparators
            .map { it to evenSplitter.split(objects, it) }
            .maxBy { it.second.score() }

        return res
    }
}