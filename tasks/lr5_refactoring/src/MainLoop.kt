package lr5_refactoring

class MainLoop {
    // Функция для нахождения максимальной цифры числа с использованием цикла
    fun maxDigit(number: Int): Int {
        return maxDigitLoop(number)
    }

    // Функция для нахождения минимальной нечетной цифры числа с использованием цикла
    fun minOddDigit(number: Int): Int {
        return minOddDigitLoop(number)
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
