package lab5

// Функция высшего порядка для обработки каждой цифры числа
fun processDigits(number: Int, initial: Int, operation: (Int, Int) -> Int): Int {
    var result = initial
    var n = number

    while (n != 0) {
        val digit = n % 10
        result = operation(result, digit)
        n /= 10
    }
    return result
}

// Функция высшего порядка для рекурсивной обработки каждой цифры числа
fun processDigitsRecursive(number: Int, initial: Int, operation: (Int, Int) -> Int): Int {
    if (number == 0) return initial
    val lastDigit = number % 10
    val updated = operation(initial, lastDigit)
    return processDigitsRecursive(number / 10, updated, operation)
}

// Функция высшего порядка для хвостовой рекурсивной обработки каждой цифры числа
fun processDigitsTailRecursive(number: Int, initial: Int, operation: (Int, Int) -> Int): Int {
    fun helper(n: Int, acc: Int): Int {
        return if (n == 0) acc else helper(n / 10, operation(acc, n % 10))
    }
    return helper(number, initial)
}

// Функция высшего порядка для вычисления НОД
fun gcdOperation(a: Int, b: Int, gcdFunction: (Int, Int) -> Int): Int {
    return gcdFunction(a, b)
}

// Функции для обработки цифр числа с использованием высших порядков

fun maxDigitLoop(number: Int): Int {
    return processDigits(number, 0) { maxDigit, digit -> maxOf(maxDigit, digit) }
}

fun minOddDigitLoop(number: Int): Int {
    return processDigits(number, 10) { minOdd, digit -> if (digit % 2 != 0) minOf(minOdd, digit) else minOdd }.let {
        if (it == 10) -1 else it
    }
}

// Обычная рекурсия: Найти максимальную цифру числа
fun maxDigitRecursive(number: Int): Int {
    return processDigitsRecursive(number, 0) { maxDigit, digit -> maxOf(maxDigit, digit) }
}

// Обычная рекурсия: Найти минимальную нечетную цифру числа
fun minOddDigitRecursive(number: Int): Int {
    return processDigitsRecursive(number, 10) { minOdd, digit -> if (digit % 2 != 0) minOf(minOdd, digit) else minOdd }.let {
        if (it == 10) -1 else it
    }
}

// Хвостовая рекурсия: Найти максимальную цифру числа
fun maxDigitTailRecursive(number: Int): Int {
    return processDigitsTailRecursive(number, 0) { maxDigit, digit -> maxOf(maxDigit, digit) }
}

// Хвостовая рекурсия: Найти минимальную нечетную цифру числа
fun minOddDigitTailRecursive(number: Int): Int {
    return processDigitsTailRecursive(number, 10) { minOdd, digit -> if (digit % 2 != 0) minOf(minOdd, digit) else minOdd }.let {
        if (it == 10) -1 else it
    }
}

// Функции для вычисления НОД

// Обычная рекурсия: Найти НОД двух чисел
fun gcdRecursive(a: Int, b: Int): Int {
    return if (b == 0) a else gcdRecursive(b, a % b)
}

// Хвостовая рекурсия: Найти НОД двух чисел
fun gcdTailRecursive(a: Int, b: Int): Int {
    return gcdOperation(a, b) { x, y -> if (y == 0) x else gcdTailRecursive(y, x % y) }
}
