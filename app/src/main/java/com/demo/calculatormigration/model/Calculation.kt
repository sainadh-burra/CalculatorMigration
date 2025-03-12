package com.demo.calculatormigration.model

import java.math.RoundingMode

class Calculation(private val keys: List<CalculatorKey>) {

    private val number = StringBuilder()
    private var result = 0.0
    private var firstNumber = true
    private var operation: CalculatorKey? = null

    companion object {
        fun getResult(keys: List<CalculatorKey>): String {
            val calculation = Calculation(keys)
            return calculation.calculateResult()
        }
    }

    private fun calculateResult(): String {
        for (key in keys) {
            if (key.isOperationKey()) {
                processOperationKey(key)
            } else {
                number.append(key.getText())
            }
        }
        return if (operation != null) {
            result = applyOperation(result, convertCurrentNumberTextToDouble())
            result.toBigDecimal().setScale(2, RoundingMode.HALF_UP)
                .stripTrailingZeros().toPlainString()
        } else {
            number.toString()
        }
    }

    private fun processOperationKey(key: CalculatorKey) {
        if (firstNumber) {
            operation = key
            firstNumber = false
            result = convertCurrentNumberTextToDouble()
        } else {
            result = applyOperation(result, convertCurrentNumberTextToDouble())
        }
        number.clear()
    }

    private fun convertCurrentNumberTextToDouble(): Double {
        return number.toString().toDouble()
    }

    private fun applyOperation(left: Double, right: Double): Double {
        return when (operation) {
            CalculatorKey.Divide -> left / right
            CalculatorKey.Minus -> left - right
            CalculatorKey.Plus -> left + right
            CalculatorKey.Multiply -> left * right
            else -> throw IllegalStateException()
        }
    }
}