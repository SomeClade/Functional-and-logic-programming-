/**
 * Считает количество локальных максимумов в массиве.
 * @param array Входной массив целых чисел.
 * @return Количество локальных максимумов.
 */
fun countLocalMaxima(array: IntArray): Long {
    // Считаем количество локальных максимумов
    return array.indices.drop(1).dropLast(1).count { array[it] > array[it - 1] && array[it] > array[it + 1] }.toLong()
}

fun main() {
    val array = intArrayOf(4, 2, 7, 1, 3, 6, 5)
    // Выводим количество локальных максимумов
    println(countLocalMaxima(array)) // Output: 2
}
