package fr.maxbuster

class EvenSplitter {

    data class Result<T>(
        val reference: T,
        val lower: List<T>,
        val higherOrEqual: List<T>
    ) {

        /**
         * The score of an Even Split result is a value rating how close to half split the operation got.
         * If either `lower` or `higherOrEqual` is empty, the score is 0.
         * If `lower` and `higherOrEqual` are the same size, the score is 1.
         *
         * @return the split result's score.
         */
        fun score(): Float {

            val (smallest, biggest) = if (lower.size < higherOrEqual.size) {
                lower to higherOrEqual
            } else {
                higherOrEqual to lower
            }

            return smallest.size.toFloat() / biggest.size
        }
    }

    fun <T> split(list: List<T>, comparator: Comparator<T>): Result<T> {

        if (list.isEmpty())
            throw Exception("List cannot be empty.")

        val sortedList = list.sortedWith(comparator)
        val middle = sortedList[sortedList.size / 2]
        val lowestIndex = firstOccurrenceBinarySearch(sortedList, comparator, middle)
        val highestIndex = lastOccurrenceBinarySearch(sortedList, comparator, middle)

        val listIsUniform = lowestIndex == 0 && highestIndex == sortedList.lastIndex
        val lowestIsBestSplitter = (sortedList.lastIndex - highestIndex) <= lowestIndex
        val referenceIndex = if (listIsUniform || lowestIsBestSplitter) {
            lowestIndex
        } else {
            highestIndex + 1
        }

        return Result(
            reference = sortedList[referenceIndex],
            lower = sortedList.subList(0, referenceIndex),
            higherOrEqual = sortedList.subList(referenceIndex, sortedList.size)
        )
    }

    fun <T : Comparable<T>> split(list: List<T>) =
        split(list) { it1, it2 -> it1.compareTo(it2) }

    private fun <T> firstOccurrenceBinarySearch(list: List<T>, comparator: Comparator<T>, target: T): Int {

        if (comparator.compare(list.first(), target) == 0)
            return 0

        var lowerBound = -1
        var upperBound = list.lastIndex + 1

        while (lowerBound < upperBound - 1) {

            val middleIndex = (lowerBound + upperBound) / 2
            val middle = list[middleIndex]

            if (comparator.compare(middle, target) < 0) {
                lowerBound = middleIndex
            } else if (comparator.compare(middle, target) > 0) {
                upperBound = middleIndex
            } else if (lowerBound != middleIndex) {
                upperBound = middleIndex
            } else {
                return middleIndex
            }
        }

        return lowerBound + 1
    }

    private fun <T> lastOccurrenceBinarySearch(list: List<T>, comparator: Comparator<T>, target: T): Int {

        if (comparator.compare(list.last(), target) == 0)
            return list.lastIndex

        var lowerBound = -1
        var upperBound = list.lastIndex + 1

        while (lowerBound < upperBound - 1) {

            val middleIndex = (lowerBound + upperBound) / 2
            val middle = list[middleIndex]

            if (comparator.compare(middle, target) < 0) {
                lowerBound = middleIndex
            } else if (comparator.compare(middle, target) > 0) {
                upperBound = middleIndex
            } else if (upperBound != middleIndex) {
                lowerBound = middleIndex
            } else {
                return middleIndex
            }
        }

        return lowerBound
    }
}