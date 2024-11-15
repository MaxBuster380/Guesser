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