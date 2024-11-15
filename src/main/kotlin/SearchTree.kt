package fr.maxbuster

data class SearchTree<T>(
    val rootNode: Node<T>
) {

    interface Node<T>

    data class LeafNode<T> (
        val values: List<T>
    ): Node<T>

    data class SplitterNode<T> (
        val comparator: Comparator<T>,
        val reference: T,
        val lower: Node<T>,
        val higherOrEqual: Node<T>
    ): Node<T>
}
