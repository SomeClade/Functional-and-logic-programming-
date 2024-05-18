package lr5_refactoring

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*

internal class Variant4Test {

    @Test
    fun testCountEvenNonCoprimeNumbers() {
        assertEquals(10, countEvenNonCoprimeNumbers(20))  // Числа: 2, 4, 6, 8, 10, 12, 14, 16, 18, 20
        assertEquals(3, countEvenNonCoprimeNumbers(15))   // Числа: 6, 10, 12
        assertEquals(0, countEvenNonCoprimeNumbers(1))
        assertEquals(1, countEvenNonCoprimeNumbers(2))    // Число: 2
    }

    @Test
    fun testProductMaxNonCoprimeAndSumDigits() {
        assertEquals(5, productMaxNonCoprimeAndSumDigits(10))  // maxNonCoprime = 5, sumDigits = 1 -> 5*1 = 5
        assertEquals(0, productMaxNonCoprimeAndSumDigits(1))
        assertEquals(0, productMaxNonCoprimeAndSumDigits(2))
    }

    @Test
    fun testMinDivisor() {
        assertEquals(2, minDivisor(10))
        assertEquals(3, minDivisor(9))
        assertEquals(5, minDivisor(25))
        assertEquals(7, minDivisor(49))
    }

    @Test
    fun testSumDigitsLessThanFive() {
        assertEquals(10, sumDigitsLessThanFive(123456))  // 1+2+3+4 = 10
        assertEquals(6, sumDigitsLessThanFive(102030))   // 1+0+2+0+3+0 = 6
        assertEquals(4, sumDigitsLessThanFive(4000))     // 4+0+0+0 = 4
    }
}
