package lr5_refactoring

fun getFunctionByName(name: String): ((Int) -> Int)? {
    val functions = mapOf(
        "maxDigitLoop" to ::maxDigitLoop,
        "minOddDigitLoop" to ::minOddDigitLoop,
        "maxDigitRecursive" to MainRecursion()::maxDigitRecursive,
        "minOddDigitRecursive" to MainRecursion()::minOddDigitRecursive,
        "maxDigitTailRecursive" to MainRecursion()::maxDigitTailRecursive,
        "minOddDigitTailRecursive" to MainRecursion()::minOddDigitTailRecursive,
        "countEvenNonCoprimeNumbers" to ::countEvenNonCoprimeNumbers,
        "productMaxNonCoprimeAndSumDigits" to ::productMaxNonCoprimeAndSumDigits
    )

    return functions[name]
}

fun getGcdFunctionByName(name: String): ((Int, Int) -> Int)? {
    val functions = mapOf(
        "gcdRecursive" to MainRecursion()::gcdRecursive,
        "gcdTailRecursive" to MainRecursion()::gcdTailRecursive
    )

    return functions[name]
}
