// Функция для нахождения максимальной цифры числа
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

// Функция для нахождения минимальной нечетной цифры числа
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

// Функция для нахождения НОД двух чисел
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

// Тесты для функций
fun main() {
    // Тесты для maxDigit
    println("Testing maxDigit function:")
    println(maxDigit(12345)) // 5
    println(maxDigit(987654)) // 9
    println(maxDigit(1111)) // 1
    println(maxDigit(0)) // 0

    // Тесты для minOddDigit
    println("Testing minOddDigit function:")
    println(minOddDigit(123456)) // 1
    println(minOddDigit(86420)) // -1 (нет нечетных цифр)
    println(minOddDigit(97531)) // 1
    println(minOddDigit(24680)) // -1 (нет нечетных цифр)

    // Тесты для gcd
    println("Testing gcd function:")
    println(gcd(54, 24)) // 6
    println(gcd(48, 180)) // 12
    println(gcd(101, 103)) // 1
    println(gcd(270, 192)) // 6
}
