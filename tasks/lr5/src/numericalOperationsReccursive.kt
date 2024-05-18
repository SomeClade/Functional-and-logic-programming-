// Обычная рекурсия: Найти максимальную цифру числа
fun maxDigitRecursive(number: Int): Int {
    if (number == 0) return 0
    val lastDigit = number % 10
    return maxOf(lastDigit, maxDigitRecursive(number / 10))
}

// Обычная рекурсия: Найти минимальную нечетную цифру числа
fun minOddDigitRecursive(number: Int, minOdd: Int = 10): Int {
    if (number == 0) return if (minOdd == 10) -1 else minOdd
    val lastDigit = number % 10
    return if (lastDigit % 2 != 0) {
        minOddDigitRecursive(number / 10, minOf(minOdd, lastDigit))
    } else {
        minOddDigitRecursive(number / 10, minOdd)
    }
}

// Обычная рекурсия: Найти НОД двух чисел
fun gcdRecursive(a: Int, b: Int): Int {
    return if (b == 0) a else gcdRecursive(b, a % b)
}

// Хвостовая рекурсия: Найти максимальную цифру числа
fun maxDigitTailRecursive(number: Int, maxDigit: Int = 0): Int {
    return if (number == 0) {
        maxDigit
    } else {
        val lastDigit = number % 10
        maxDigitTailRecursive(number / 10, maxOf(maxDigit, lastDigit))
    }
}

// Хвостовая рекурсия: Найти минимальную нечетную цифру числа
fun minOddDigitTailRecursive(number: Int, minOdd: Int = 10): Int {
    return if (number == 0) {
        if (minOdd == 10) -1 else minOdd
    } else {
        val lastDigit = number % 10
        if (lastDigit % 2 != 0) {
            minOddDigitTailRecursive(number / 10, minOf(minOdd, lastDigit))
        } else {
            minOddDigitTailRecursive(number / 10, minOdd)
        }
    }
}

// Хвостовая рекурсия: Найти НОД двух чисел
fun gcdTailRecursive(a: Int, b: Int): Int {
    return if (b == 0) {
        a
    } else {
        gcdTailRecursive(b, a % b)
    }
}

// Тесты для функций
fun main() {
    // Тесты для maxDigitRecursive и maxDigitTailRecursive
    println("Testing maxDigitRecursive and maxDigitTailRecursive functions:")
    val numbersForMaxDigit = listOf(12345, 987654, 1111, 0)
    for (number in numbersForMaxDigit) {
        println("maxDigitRecursive($number) = ${maxDigitRecursive(number)}")
        println("maxDigitTailRecursive($number) = ${maxDigitTailRecursive(number)}")
    }

    // Тесты для minOddDigitRecursive и minOddDigitTailRecursive
    println("\nTesting minOddDigitRecursive and minOddDigitTailRecursive functions:")
    val numbersForMinOddDigit = listOf(123456, 86420, 97531, 24680)
    for (number in numbersForMinOddDigit) {
        println("minOddDigitRecursive($number) = ${minOddDigitRecursive(number)}")
        println("minOddDigitTailRecursive($number) = ${minOddDigitTailRecursive(number)}")
    }

    // Тесты для gcdRecursive и gcdTailRecursive
    println("\nTesting gcdRecursive and gcdTailRecursive functions:")
    val pairsForGcd = listOf(Pair(54, 24), Pair(48, 180), Pair(101, 103), Pair(270, 192))
    for (pair in pairsForGcd) {
        println("gcdRecursive(${pair.first}, ${pair.second}) = ${gcdRecursive(pair.first, pair.second)}")
        println("gcdTailRecursive(${pair.first}, ${pair.second}) = ${gcdTailRecursive(pair.first, pair.second)}")
    }
}
