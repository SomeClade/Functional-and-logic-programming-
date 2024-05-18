package lab5

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
