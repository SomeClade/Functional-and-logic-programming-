package lab5

class MainRecursion {

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
     tailrec fun maxDigitTailRecursive(number: Int): Int {
        return processDigitsTailRecursive(number, 0) { maxDigit, digit -> maxOf(maxDigit, digit) }
    }

    // Хвостовая рекурсия: Найти минимальную нечетную цифру числа
    fun minOddDigitTailRecursive(number: Int): Int {
        return processDigitsTailRecursive(number, 10) { minOdd, digit -> if (digit % 2 != 0) minOf(minOdd, digit) else minOdd }.let {
            if (it == 10) -1 else it
        }
    }

    // Обычная рекурсия: Найти НОД двух чисел
    fun gcdRecursive(a: Int, b: Int): Int {
        return gcdOperation(a, b) { x, y -> if (y == 0) x else gcdRecursive(y, x % y) }
    }

    // Хвостовая рекурсия: Найти НОД двух чисел
    fun gcdTailRecursive(a: Int, b: Int): Int {
        return gcdOperation(a, b) { x, y -> if (y == 0) x else gcdTailRecursive(y, x % y) }
    }
}
