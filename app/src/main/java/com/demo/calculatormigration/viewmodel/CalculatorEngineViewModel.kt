package com.demo.calculatormigration.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.demo.calculatormigration.model.Calculation
import com.demo.calculatormigration.model.CalculatorKey
import com.demo.calculatormigration.model.getText
import com.demo.calculatormigration.model.isNumber
import com.demo.calculatormigration.model.isOperationKey

class CalculatorEngineViewModel : ViewModel() {

    private val keysPressed = mutableListOf<CalculatorKey>()

    private val _calculationText = MutableLiveData<String>()
    val calculationText: LiveData<String> get() = _calculationText

    private val _resultText = MutableLiveData<String>()
    val resultText: LiveData<String> get() = _resultText

    init {
        _calculationText.value = ""
        _resultText.value = ""
    }

    fun processKeyPress(key: CalculatorKey) {
        var processed = true
        when (key) {
            CalculatorKey.Clear -> {
                clear()
                processed = false
            }
            CalculatorKey.Nine, CalculatorKey.Eight, CalculatorKey.Seven, CalculatorKey.Six,
            CalculatorKey.Five, CalculatorKey.Four, CalculatorKey.Three, CalculatorKey.Two,
            CalculatorKey.One, CalculatorKey.Zero -> processed = true
            CalculatorKey.Plus, CalculatorKey.Minus, CalculatorKey.Multiply, CalculatorKey.Divide -> processed = onOperationKeyPressed(key)
            CalculatorKey.Point -> processed = onPointKeyPressed()
            CalculatorKey.Equal -> processed = onEqualKeyPressed()
            CalculatorKey.Backspace -> processed = onBackspaceKeyPressed()
            CalculatorKey.PlusMinus -> processed = onPlusMinusPressed()
        }

        if (processed) {
            keysPressed.add(key)
            generateCalculationText()
        }
    }

    private fun clear() {
        keysPressed.clear()
        _calculationText.value = ""
        _resultText.value = ""
    }

    private fun onOperationKeyPressed(key: CalculatorKey): Boolean {
        if (keysPressed.isEmpty()) return false
        if (lastKeyPressedIsPlusMinus()) return false
        if (lastKeyPressedIsOperation()) removeLastKeyPressed()
        return true
    }

    private fun lastKeyPressedIsOperation(): Boolean {
        return keysPressed.isNotEmpty() && keysPressed.last().isOperationKey()
    }

    private fun lastKeyPressedIsPlusMinus(): Boolean {
        return keysPressed.isNotEmpty() && keysPressed.last() == CalculatorKey.PlusMinus
    }

    private fun removeLastKeyPressed() {
        keysPressed.removeAt(keysPressed.size - 1)
    }

    private fun onEqualKeyPressed(): Boolean {
        if (keysPressed.isEmpty()) return false
        if (lastKeyPressedIsOperation() || lastKeyPressedIsPlusMinus()) return false

        _calculationText.value = ""
        _resultText.value = Calculation.getResult(keysPressed)

        keysPressed.clear()
        return false
    }

    private fun onBackspaceKeyPressed(): Boolean {
        if (keysPressed.isNotEmpty()) {
            removeLastKeyPressed()
            generateCalculationText()
        }
        return false
    }

    private fun onPointKeyPressed(): Boolean {
        if (keysPressed.isEmpty() || lastKeyPressedIsOperation()) {
            keysPressed.add(CalculatorKey.Zero)
            return true
        }
        return !pointAlreadyPressedForCurrentNumber()
    }

    private fun pointAlreadyPressedForCurrentNumber(): Boolean {
        for (i in keysPressed.indices.reversed()) {
            val key = keysPressed[i]
            if (key == CalculatorKey.Point) return true
            if (key.isOperationKey()) return false
        }
        return false
    }

    private fun generateCalculationText() {
        val builder = StringBuilder()
        for (key in keysPressed) {
            if (key.isOperationKey()) {
                builder.append(" ${key.getText()} ")
            } else {
                builder.append(key.getText())
            }
        }
        _calculationText.value = builder.toString().trimEnd()
    }

    private fun onPlusMinusPressed(): Boolean {
        if (keysPressed.isEmpty()) return true

        val lastKeyPressed = keysPressed.last()
        if (lastKeyPressed == CalculatorKey.PlusMinus) {
            removeLastKeyPressed()
            generateCalculationText()
            return false
        }

        if (lastKeyPressed.isNumber()) {
            val index = findStartOfCurrentNumber()
            if (keysPressed[index] == CalculatorKey.PlusMinus) {
                keysPressed.removeAt(index)
            } else {
                keysPressed.add(index, CalculatorKey.PlusMinus)
            }
            generateCalculationText()
            return false
        }
        return true
    }

    private fun findStartOfCurrentNumber(): Int {
        for (i in keysPressed.indices.reversed()) {
            val key = keysPressed[i]
            if (key.isOperationKey()) {
                return i + 1
            }
        }
        return 0
    }
}