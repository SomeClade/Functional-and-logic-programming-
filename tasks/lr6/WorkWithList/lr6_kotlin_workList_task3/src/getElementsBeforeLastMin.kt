/**
 * Возвращает элементы массива, расположенные перед последним минимальным элементом.
 * @param array Входной массив целых чисел.
 * @return Список элементов, расположенных перед последним минимальным элементом.
 */
fun getElementsBeforeLastMin(array: IntArray): List<Int> {
    // Находим индекс последнего минимального элемента
    val lastMinIndex = array.indices.last { array[it] == array.minOrNull() }

    // Возвращаем элементы, расположенные перед последним минимальным элементом
    return array.take(lastMinIndex)
}

fun main() {
    val array = intArrayOf(4, 2, 7, 1, 3, 1, 5)
    // Выводим элементы, расположенные перед последним минимальным элементом
    println(getElementsBeforeLastMin(array)) // Output: [4, 2, 7, 1, 3]
}
