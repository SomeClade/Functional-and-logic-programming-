/**
 * Возвращает индексы массива в порядке убывания значений элементов.
 * @param array Входной массив целых чисел.
 * @return Список индексов в порядке убывания значений элементов массива.
 */
fun getDescendingIndices(array: IntArray): List<Int> {
    // Создаем поток индексов массива, сортируем их по значениям элементов в убывающем порядке
    return array.indices.sortedByDescending { array[it] }
}

fun main() {
    val array = intArrayOf(4, 2, 7, 1, 3)
    // Выводим индексы элементов массива в порядке убывания значений
    println(getDescendingIndices(array)) // Output: [2, 0, 4, 1, 3]
}
