package lab5

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*

internal class MainKtTest {

    @Test
    fun maxDigit() {
        val mainLoop = MainLoop()
        val mainRecursion = MainRecursion()
        val input = 987654
        val expected = 9
        assertEquals(expected, mainLoop.maxDigit(input))
        assertEquals(expected, mainRecursion.maxDigitRecursive(input))
        assertEquals(expected, mainRecursion.maxDigitTailRecursive(input))
    }

    @Test
    fun minOddDigit() {
        val mainLoop = MainLoop()
        val mainRecursion = MainRecursion()

        fun checkAll(input: Int, expected: Int) {
            assertEquals(expected, mainLoop.minOddDigit(input))
            assertEquals(expected, mainRecursion.minOddDigitRecursive(input))
            assertEquals(expected, mainRecursion.minOddDigitTailRecursive(input))
        }

        checkAll(123456, 1)
        checkAll(86420, -1) // нет нечетных цифр
        checkAll(97531, 1)
        checkAll(24680, -1) // нет нечетных цифр
    }

    @Test
    fun gcd() {
        val mainLoop = MainLoop()
        val mainRecursion = MainRecursion()

        fun checkAll(a: Int, b: Int, expected: Int) {
            assertEquals(expected, mainLoop.gcd(a, b))
            assertEquals(expected, mainRecursion.gcdRecursive(a, b))
            assertEquals(expected, mainRecursion.gcdTailRecursive(a, b))
        }

        checkAll(54, 24, 6)
        checkAll(48, 180, 12)
        checkAll(101, 103, 1)
        checkAll(270, 192, 6)
    }
}
