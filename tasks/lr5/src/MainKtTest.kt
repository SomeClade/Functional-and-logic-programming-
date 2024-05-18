package lab5

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*

internal class MainKtTest {

    @Test
    fun maxDigit() {
        val inputs = listOf(987654, 123456, 1111, 0)
        val expectedResults = listOf(9, 6, 1, 0)

        for ((input, expected) in inputs.zip(expectedResults)) {
            assertEquals(expected, maxDigitLoop(input))
            assertEquals(expected, maxDigitRecursive(input))
            assertEquals(expected, maxDigitTailRecursive(input))
        }
    }

    @Test
    fun minOddDigit() {
        fun checkAll(input: Int, expected: Int) {
            assertEquals(expected, minOddDigitLoop(input))
            assertEquals(expected, minOddDigitRecursive(input))
            assertEquals(expected, minOddDigitTailRecursive(input))
        }

        checkAll(123456, 1)
        checkAll(86420, -1) // нет нечетных цифр
        checkAll(97531, 1)
        checkAll(24680, -1) // нет нечетных цифр
    }

    @Test
    fun gcd() {
        fun checkAll(a: Int, b: Int, expected: Int) {
            assertEquals(expected, gcdRecursive(a, b))
            assertEquals(expected, gcdTailRecursive(a, b))
        }

        checkAll(54, 24, 6)
        checkAll(48, 180, 12)
        checkAll(101, 103, 1)
        checkAll(270, 192, 6)
    }
}
