package lr5_refactoring

// Функция высшего порядка для нахождения количества элементов, удовлетворяющих условию
fun countByCondition(number: Int, condition: (Int) -> Boolean): Int {
    var count = 0
    for (i in 1..number) {
        if (condition(i)) {
            count++
        }
    }
    return count
}

// Функция высшего порядка для нахождения максимального элемента, удовлетворяющего условию
fun maxByCondition(number: Int, condition: (Int) -> Boolean): Int? {
    var max: Int? = null
    for (i in 1..number) {
        if (condition(i)) {
            if (max == null || i > max) {
                max = i
            }
        }
    }
    return max
}

// Функция для нахождения наименьшего делителя числа
fun minDivisor(number: Int): Int {
    for (i in 2..number) {
        if (number % i == 0) {
            return i
        }
    }
    return number
}

// Функция для нахождения суммы цифр числа, меньших 5
fun sumDigitsLessThanFive(number: Int): Int {
    return processDigits(number, 0) { sum, digit -> if (digit < 5) sum + digit else sum }
}

// Найти количество четных чисел, не взаимно простых с данным
fun countEvenNonCoprimeNumbers(number: Int): Int {
    return countByCondition(number) { i -> i % 2 == 0 && gcdOperation(i, number, ::gcdRecursive) != 1 }
}

// Найти произведение максимального числа, не взаимно простого с данным, не делящегося на наименьший делитель исходного числа, и суммы цифр числа, меньших 5
fun productMaxNonCoprimeAndSumDigits(number: Int): Int {
    val minDiv = minDivisor(number)
    val maxNonCoprime = maxByCondition(number) { i -> gcdOperation(i, number, ::gcdRecursive) != 1 && i % minDiv != 0 }
    val sumDigits = sumDigitsLessThanFive(number)
    return if (maxNonCoprime != null) maxNonCoprime * sumDigits else 0
}
