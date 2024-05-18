package lab5

class MainLoop {
    // Функция для нахождения максимальной цифры числа с использованием цикла
    fun maxDigit(number: Int): Int {
        return processDigits(number, 0) { maxDigit, digit -> maxOf(maxDigit, digit) }
    }

    // Функция для нахождения минимальной нечетной цифры числа с использованием цикла
    fun minOddDigit(number: Int): Int {
        return processDigits(number, 10) { minOdd, digit -> if (digit % 2 != 0) minOf(minOdd, digit) else minOdd }.let {
            if (it == 10) -1 else it
        }
    }

    // Функция для нахождения НОД двух чисел с использованием цикла
    fun gcd(a: Int, b: Int): Int {
        return gcdOperation(a, b) { x, y ->
            var m = x
            var n = y
            while (n != 0) {
                val temp = n
                n = m % n
                m = temp
            }
            m
        }
    }
}
