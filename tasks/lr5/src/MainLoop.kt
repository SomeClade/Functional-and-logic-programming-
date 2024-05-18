package lab5

class MainLoop {
    // Функция для нахождения максимальной цифры числа с использованием цикла
    fun maxDigit(number: Int): Int {
        var maxDigit = 0
        var n = number

        while (n != 0) {
            val digit = n % 10
            if (digit > maxDigit) {
                maxDigit = digit
            }
            n /= 10
        }
        return maxDigit
    }

    // Функция для нахождения минимальной нечетной цифры числа с использованием цикла
    fun minOddDigit(number: Int): Int {
        var minOddDigit = 10
        var n = number

        while (n != 0) {
            val digit = n % 10
            if (digit % 2 != 0 && digit < minOddDigit) {
                minOddDigit = digit
            }
            n /= 10
        }
        return if (minOddDigit == 10) -1 else minOddDigit // Если не найдена ни одна нечетная цифра, возвращаем -1
    }

    // Функция для нахождения НОД двух чисел с использованием цикла
    fun gcd(a: Int, b: Int): Int {
        var x = a
        var y = b

        while (y != 0) {
            val temp = y
            y = x % y
            x = temp
        }
        return x
    }
}
