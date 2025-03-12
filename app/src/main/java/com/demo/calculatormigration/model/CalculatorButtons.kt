package com.demo.calculatormigration.model

object CalculatorButtons {
    val Backspace = CalculatorButton(CalculatorKey.Backspace, "←")
    val Clear = CalculatorButton(CalculatorKey.Clear, "C")
    val PlusMinus = CalculatorButton(CalculatorKey.PlusMinus, "±")
    val Divide = CalculatorButton(CalculatorKey.Divide, "/")
    val Multiply = CalculatorButton(CalculatorKey.Multiply, "*")
    val Minus = CalculatorButton(CalculatorKey.Minus, "-")
    val Plus = CalculatorButton(CalculatorKey.Plus, "+")
    val Equal = CalculatorButton(CalculatorKey.Equal, "=")
    val Nine = CalculatorButton(CalculatorKey.Nine, "9")
    val Eight = CalculatorButton(CalculatorKey.Eight, "8")
    val Seven = CalculatorButton(CalculatorKey.Seven, "7")
    val Six = CalculatorButton(CalculatorKey.Six, "6")
    val Five = CalculatorButton(CalculatorKey.Five, "5")
    val Four = CalculatorButton(CalculatorKey.Four, "4")
    val Three = CalculatorButton(CalculatorKey.Three, "3")
    val Two = CalculatorButton(CalculatorKey.Two, "2")
    val One = CalculatorButton(CalculatorKey.One, "1")
    val Zero = CalculatorButton(CalculatorKey.Zero, "0")
    val Point = CalculatorButton(CalculatorKey.Point, ".")

    val All = arrayOf(
        Backspace, Clear, PlusMinus, Divide,
        Seven, Eight, Nine, Multiply,
        Four, Five, Six, Minus,
        One, Two, Three, Plus,
        Zero, Point, Equal
    )
}