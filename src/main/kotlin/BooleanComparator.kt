package fr.maxbuster

abstract class BooleanComparator<T>: Comparator<T> {

    abstract fun matches(element: T): Boolean

    override fun compare(p0: T, p1: T): Int {
        return if (matches(p0))
            if (matches(p1))
                0
            else
                1
        else
            if (matches(p1))
                -1
            else
                0
    }
}