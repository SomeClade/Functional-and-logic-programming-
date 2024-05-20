/**
 * Возвращает элементы массива, расположенные после первого максимального элемента.
 * @param array Входной массив целых чисел.
 * @return Список элементов, расположенных после первого максимального элемента.
 */
fun getElementsAfterFirstMax(array: IntArray): List<Int> {
    // Находим индекс первого максимального элемента
    val firstMaxIndex = array.indices.maxByOrNull { array[it] } ?: -1

    // Возвращаем элементы, расположенные после первого максимального элемента
    return if (firstMaxIndex != -1 && firstMaxIndex < array.size - 1) {
        array.drop(firstMaxIndex + 1)
    } else {
        emptyList()
    }
}

fun main() {
    val array = intArrayOf(4, 2, 7, 1, 3, 7, 5)
    // Выводим элементы, расположенные после первого максимального элемента
    println(getElementsAfterFirstMax(array)) // Output: [1, 3, 7, 5]
}
