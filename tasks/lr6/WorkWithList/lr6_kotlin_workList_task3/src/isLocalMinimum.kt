/**
 * Проверяет, является ли элемент по указанному индексу локальным минимумом.
 * @param array Входной массив целых чисел.
 * @param index Индекс элемента для проверки.
 * @return true, если элемент является локальным минимумом, иначе false.
 */
fun isLocalMinimum(array: IntArray, index: Int): Boolean {
    if (index !in array.indices) {
        throw IllegalArgumentException("Index out of bounds")
    }

    // Получаем значения соседних элементов (если они существуют)
    val left = if (index > 0) array[index - 1] else Int.MAX_VALUE
    val right = if (index < array.size - 1) array[index + 1] else Int.MAX_VALUE

    // Проверяем, является ли элемент локальным минимумом
    return array[index] < left && array[index] < right
}

fun main() {
    val array = intArrayOf(4, 2, 7, 1, 3)
    val index = 3
    // Проверяем, является ли элемент по указанному индексу локальным минимумом
    println(isLocalMinimum(array, index)) // Output: true
}
