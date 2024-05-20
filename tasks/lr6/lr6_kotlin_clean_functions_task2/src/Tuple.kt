/**
 * Класс данных для хранения кортежа из трех элементов.
 */
data class Tuple3<A, B, C>(val first: A, val second: B, val third: C)

/**
 * Создает список кортежей длины 3 из трех списков, сортируя каждый список по определенным правилам.
 * @param listA Первый список, сортируемый по убыванию.
 * @param listB Второй список, сортируемый по возрастанию суммы цифр элементов.
 * @param listC Третий список, сортируемый по убыванию количества делителей элементов.
 * @return Список кортежей длины 3.
 */
fun createTuples(listA: List<Int>, listB: List<Int>, listC: List<Int>): List<Tuple3<Int, Int, Int>> {
    // Сортируем первый список по убыванию
    val sortedA = listA.sortedDescending()

    // Сортируем второй список по возрастанию суммы цифр, при равенстве суммы по убыванию абсолютного значения
    val sortedB = listB.sortedWith(compareBy({ sumDigits(it) }, { -it }))

    // Сортируем третий список по убыванию количества делителей, при равенстве количества по убыванию абсолютного значения
    val sortedC = listC.sortedWith(compareByDescending<Int> { countDivisors(it) }.thenBy { -it })

    // Объединяем три отсортированных списка в список кортежей
    return (0 until minOf(listA.size, listB.size, listC.size)).map { i ->
        Tuple3(sortedA[i], sortedB[i], sortedC[i])
    }
}

/**
 * Вычисляет сумму цифр числа.
 * @param number Число, сумма цифр которого вычисляется.
 * @return Сумма цифр числа.
 */
fun sumDigits(number: Int): Int {
    return number.toString().map { it.toString().toInt() }.sum()
}

/**
 * Подсчитывает количество делителей числа.
 * @param number Число, количество делителей которого подсчитывается.
 * @return Количество делителей числа.
 */
fun countDivisors(number: Int): Int {
    return (1..number).count { number % it == 0 }
}

fun main() {
    val listA = listOf(1, 2, 3, 4, 5)
    val listB = listOf(10, 20, 30, 40, 50)
    val listC = listOf(6, 7, 8, 9, 10)

    val tuples = createTuples(listA, listB, listC)

    tuples.forEach { println("(${it.first}, ${it.second}, ${it.third})") }

}
