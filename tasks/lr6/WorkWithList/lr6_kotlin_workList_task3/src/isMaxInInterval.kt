/**
 * Проверяет наличие максимального элемента массива в заданном интервале.
 * @param array Входной массив целых чисел.
 * @param a Начало интервала (включительно).
 * @param b Конец интервала (включительно).
 * @return true, если максимальный элемент массива находится в заданном интервале, иначе false.
 */
fun isMaxInInterval(array: IntArray, a: Int, b: Int): Boolean {
    if (a !in array.indices || b !in array.indices || a > b) {
        throw IllegalArgumentException("Invalid interval")
    }

    // Находим максимальный элемент в массиве
    val max = array.maxOrNull() ?: Int.MIN_VALUE

    // Проверяем, находится ли максимальный элемент в заданном интервале
    return (a..b).any { array[it] == max }
}

fun main() {
    val array = intArrayOf(4, 2, 7, 1, 3, 6, 5)
    val a = 2
    val b = 5
    // Проверяем, находится ли максимальный элемент массива в заданном интервале
    println(isMaxInInterval(array, a, b)) // Output: true
}
