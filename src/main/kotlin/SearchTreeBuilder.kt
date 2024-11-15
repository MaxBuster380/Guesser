package fr.maxbuster

class SearchTreeBuilder<T>(
    private val comparators: List<Comparator<T>>
) {

    private data class BuilderNode<T>(
        val list: List<T>
    ): SearchTree.Node<T>

    private data class BuilderSplitterNode<T> (
        val comparator: Comparator<T>,
        val reference: T,
        val lowerNodeIndex: Int,
        val higherOrEqualNodeIndex: Int
    ): SearchTree.Node<T>

    fun build(objects: List<T>): SearchTree<T> {

        val builderTree = treeCreator(objects)

        return treeCleaner(builderTree)
    }

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

    private fun treeCleaner(builderTree: MutableList<SearchTree.Node<T>>): SearchTree<T> {

        var nodeIndex = builderTree.lastIndex

        while(nodeIndex >= 0) {

            cleanNode(builderTree, nodeIndex)

            nodeIndex -= 1
        }

        return SearchTree(builderTree.first())
    }

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

    private fun findBestResult(objects: List<T>): Pair<Comparator<T>, EvenSplitter.Result<T>> {

        val evenSplitter = EvenSplitter()

        val res = comparators
            .map { it to evenSplitter.split(objects, it) }
            .maxBy { it.second.score() }

        return res
    }
}