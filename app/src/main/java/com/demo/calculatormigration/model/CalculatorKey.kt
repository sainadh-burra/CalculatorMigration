package com.demo.calculatormigration.model

enum class CalculatorKey(val value: Int) {
    Zero(0),
    One(1),
    Two(2),
    Three(3),
    Four(4),
    Five(5),
    Six(6),
    Seven(7),
    Eight(8),
    Nine(9),
    Backspace(20),
    Clear(21),
    PlusMinus(22),
    Divide(23),
    Multiply(24),
    Minus(25),
    Plus(26),
    Equal(27),
    Point(28);

    companion object {
        fun fromValue(value: Int): CalculatorKey? {
            return entries.find { it.value == value }
        }
    }
}