fun main() {
    // Примеры для всех задач
    val arr = listOf(3, 2, 4, 7, 10, 5)
    val numberList = listOf<Number>(1, 1.1, 2, 2.2, 3, 3.3)
    val integerList = listOf(1, 2, 2, 3, 3, 3, 4)
    val sumList = listOf(1, 2, 3, 4, 5)

    // Задача 1
    val minEven = findMinEven(arr)
    println(minEven ?: "Нет четных элементов")

    // Задача 2
    val alternates = isAlternating(numberList)
    println(alternates)

    // Задача 3
    val (L1, L2) = buildL1AndL2(integerList)
    println("L1: $L1")
    println("L2: $L2")

    // Задача 4
    val count = countElementsAsSumOfOthers(sumList)
    println(count)
}

// Задача 1: Найти минимальный четный элемент
fun findMinEven(arr: List<Int>): Int? {
    return arr.filter { it % 2 == 0 }
        .minOrNull()
}

// Задача 2: Проверить, чередуются ли целые и вещественные числа
fun isAlternating(list: List<Number>): Boolean {
    return list.zipWithNext { a, b -> a.isInteger() != b.isInteger() }
        .all { it }
}

fun Number.isInteger(): Boolean = this is Int

// Задача 3: Построить списки L1 и L2
fun buildL1AndL2(list: List<Int>): Pair<List<Int>, List<Int>> {
    val frequencies = list.groupingBy { it }.eachCount()
    val L1 = frequencies.keys.toList()
    val L2 = frequencies.values.toList()
    return Pair(L1, L2)
}

// Задача 4: Количество элементов, которые могут быть получены как сумма двух любых других элементов
fun countElementsAsSumOfOthers(list: List<Int>): Int {
    val sumSet = list.flatMap { x -> list.map { y -> x + y } }
        .toSet()
    return list.count { it in sumSet }
}
