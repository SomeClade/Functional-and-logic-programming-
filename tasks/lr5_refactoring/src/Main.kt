package lr5_refactoring

import java.io.File

fun main() {
    val inputFilePath = "D:\\javaprojects\\lr5_refactoring\\src\\input.txt"
    val inputFile = File(inputFilePath)

    if (!inputFile.exists()) {
        println("File not found: $inputFilePath")
        return
    }

    val outputFilePath = inputFilePath.replace(".txt", "_output.txt")
    val outputFile = File(outputFilePath)

    inputFile.forEachLine { line ->
        val parts = line.split(" ")
        if (parts.size == 2) {
            val number = parts[0].toIntOrNull()
            val functionName = parts[1]

            if (number != null) {
                val function = getFunctionByName(functionName)
                if (function != null) {
                    val result = function(number)
                    outputFile.appendText("$number $functionName $result\n")
                } else {
                    outputFile.appendText("Invalid function name: $functionName\n")
                }
            } else {
                outputFile.appendText("Invalid number format: ${parts[0]}\n")
            }
        } else if (parts.size == 3) {
            val a = parts[0].toIntOrNull()
            val b = parts[1].toIntOrNull()
            val functionName = parts[2]

            if (a != null && b != null) {
                val function = getGcdFunctionByName(functionName)
                if (function != null) {
                    val result = function(a, b)
                    outputFile.appendText("$a $b $functionName $result\n")
                } else {
                    outputFile.appendText("Invalid function name: $functionName\n")
                }
            } else {
                outputFile.appendText("Invalid number format: ${parts[0]} or ${parts[1]}\n")
            }
        } else {
            outputFile.appendText("Invalid input format: $line\n")
        }
    }

    println("Processing complete. Results saved to $outputFilePath")
}
