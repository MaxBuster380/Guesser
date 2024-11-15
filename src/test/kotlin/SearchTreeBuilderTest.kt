import fr.maxbuster.SearchTreeBuilder
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class SearchTreeBuilderTest {

    @Test
    fun test() {

        val list = listOf(0, 1, 2, 3, 4, 5, 6, 7, 8, 9)

        val intComparator = object: Comparator<Int> {

            override fun compare(p0: Int, p1: Int): Int = p0.compareTo(p1)

            override fun toString(): String {
                return "cmp"
            }
        }

        val searchTreeBuilder = SearchTreeBuilder(listOf(intComparator))

        println(searchTreeBuilder.build(list))
    }
}