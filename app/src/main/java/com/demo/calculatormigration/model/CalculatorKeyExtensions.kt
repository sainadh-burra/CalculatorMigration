package com.demo.calculatormigration.model

val operationKeys = arrayOf(
    CalculatorKey.Multiply,
    CalculatorKey.Divide,
    CalculatorKey.Plus,
    CalculatorKey.Minus
)

val numberKeys = arrayOf(
    CalculatorKey.Nine,
    CalculatorKey.Eight,
    CalculatorKey.Seven,
    CalculatorKey.Six,
    CalculatorKey.Five,
    CalculatorKey.Four,
    CalculatorKey.Three,
    CalculatorKey.Two,
    CalculatorKey.One,
    CalculatorKey.Zero
)

fun CalculatorKey.getText(): String {
    return when (this) {
        CalculatorKey.Nine,
        CalculatorKey.Eight,
        CalculatorKey.Seven,
        CalculatorKey.Six,
        CalculatorKey.Five,
        CalculatorKey.Four,
        CalculatorKey.Three,
        CalculatorKey.Two,
        CalculatorKey.One,
        CalculatorKey.Zero -> this.value.toString()
        CalculatorKey.Plus -> "+"
        CalculatorKey.Minus -> "-"
        CalculatorKey.Multiply -> "*"
        CalculatorKey.Divide -> "/"
        CalculatorKey.Point -> "."
        CalculatorKey.PlusMinus -> "-"
        else -> ""
    }
}

fun CalculatorKey.isOperationKey(): Boolean {
    return operationKeys.contains(this)
}

fun CalculatorKey.isNumber(): Boolean {
    return numberKeys.contains(this)
}