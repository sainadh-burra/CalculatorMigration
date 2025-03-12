package com.demo.calculatormigration

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.demo.calculatormigration.model.CalculatorKey
import com.demo.calculatormigration.viewmodel.CalculatorEngineViewModel
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.mockito.MockitoAnnotations

class CalculatorEngineViewModelTest {
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var calculatorEngine: CalculatorEngineViewModel

    @Before
    fun setUp() {
        calculatorEngine = CalculatorEngineViewModel()
    }

    private fun pressKeys(vararg keys: CalculatorKey) {
        keys.forEach { calculatorEngine.processKeyPress(it) }
    }

    @Test
    fun twoPressed() {
        pressKeys(CalculatorKey.Two)
        assertEquals("2", calculatorEngine.calculationText.value)
    }

    @Test
    fun twoThenBackspace() {
        pressKeys(CalculatorKey.Two, CalculatorKey.Backspace)
        assertEquals("", calculatorEngine.calculationText.value)
    }

    @Test
    fun twoThenClear() {
        pressKeys(CalculatorKey.Two, CalculatorKey.Clear)
        assertEquals("", calculatorEngine.calculationText.value)
    }

    @Test
    fun twoNumbersThenBackspace() {
        pressKeys(CalculatorKey.One, CalculatorKey.Two, CalculatorKey.Backspace)
        assertEquals("1", calculatorEngine.calculationText.value)
    }

    @Test
    fun pointPressed() {
        pressKeys(CalculatorKey.Point)
        assertEquals("0.", calculatorEngine.calculationText.value)
    }

    @Test
    fun plusPressed() {
        pressKeys(CalculatorKey.Plus)
        assertEquals("", calculatorEngine.calculationText.value)
    }

    @Test
    fun onePlusTwo() {
        pressKeys(CalculatorKey.One, CalculatorKey.Plus, CalculatorKey.Two)
        assertEquals("1 + 2", calculatorEngine.calculationText.value)
    }

    @Test
    fun onePlusTwoEquals() {
        pressKeys(CalculatorKey.One, CalculatorKey.Plus, CalculatorKey.Two, CalculatorKey.Equal)
        assertEquals("", calculatorEngine.calculationText.value)
        assertEquals("3", calculatorEngine.resultText.value)
    }

    @Test
    fun onePointTwoTimesThreeEquals() {
        pressKeys(CalculatorKey.One, CalculatorKey.Point, CalculatorKey.Two, CalculatorKey.Multiply, CalculatorKey.Three, CalculatorKey.Equal)
        assertEquals("3.6", calculatorEngine.resultText.value)
    }

    @Test
    fun threeDividedByTwoEquals() {
        pressKeys(CalculatorKey.Three, CalculatorKey.Divide, CalculatorKey.Two, CalculatorKey.Equal)
        assertEquals("1.5", calculatorEngine.resultText.value)
    }

    @Test
    fun threeMinusTwoEquals() {
        pressKeys(CalculatorKey.Three, CalculatorKey.Minus, CalculatorKey.Two, CalculatorKey.Equal)
        assertEquals("1", calculatorEngine.resultText.value)
    }

    @Test
    fun threeMinusOneEqualsThenOneKeyPressedThenClear() {
        pressKeys(CalculatorKey.Three, CalculatorKey.Minus, CalculatorKey.One, CalculatorKey.Equal, CalculatorKey.One, CalculatorKey.Clear)
        assertEquals("", calculatorEngine.calculationText.value)
        assertEquals("", calculatorEngine.resultText.value)
    }

    @Test
    fun onePlusTwoThenBackspace() {
        pressKeys(CalculatorKey.One, CalculatorKey.Plus, CalculatorKey.Two, CalculatorKey.Backspace)
        assertEquals("1 +", calculatorEngine.calculationText.value)
    }

    @Test
    fun onePlusEqualsShouldNotThrowException() {
        pressKeys(CalculatorKey.One, CalculatorKey.Plus, CalculatorKey.Equal)
        assertEquals("1 +", calculatorEngine.calculationText.value)
        assertEquals("", calculatorEngine.resultText.value)
    }

    @Test
    fun plusMinus() {
        pressKeys(CalculatorKey.PlusMinus)
        assertEquals("-", calculatorEngine.calculationText.value)
    }

    @Test
    fun plusMinusPlus() {
        pressKeys(CalculatorKey.PlusMinus, CalculatorKey.Plus)
        assertEquals("-", calculatorEngine.calculationText.value)
    }

    @Test
    fun plusMinusTwo() {
        pressKeys(CalculatorKey.PlusMinus, CalculatorKey.Two)
        assertEquals("-2", calculatorEngine.calculationText.value)
    }

    @Test
    fun plusMinusTwoPlusOneEqual() {
        pressKeys(CalculatorKey.PlusMinus, CalculatorKey.Two, CalculatorKey.Plus, CalculatorKey.One, CalculatorKey.Equal)
        assertEquals("-1", calculatorEngine.resultText.value)
    }

    @Test
    fun plusMinusEquals() {
        pressKeys(CalculatorKey.PlusMinus, CalculatorKey.Equal)
        assertEquals("-", calculatorEngine.calculationText.value)
    }

    @Test
    fun onePlusPlusMinusEight() {
        pressKeys(CalculatorKey.One, CalculatorKey.Plus, CalculatorKey.PlusMinus, CalculatorKey.Eight)
        assertEquals("1 + -8", calculatorEngine.calculationText.value)
    }

    @Test
    fun onePlusPlusMinusEightEqual() {
        pressKeys(CalculatorKey.One, CalculatorKey.Plus, CalculatorKey.PlusMinus, CalculatorKey.Eight, CalculatorKey.Equal)
        assertEquals("-7", calculatorEngine.resultText.value)
    }

    @Test
    fun plusMinusPlusMinus() {
        pressKeys(CalculatorKey.PlusMinus, CalculatorKey.PlusMinus)
        assertEquals("", calculatorEngine.calculationText.value)
    }

    @Test
    fun plusMinusOnePlusMinus() {
        pressKeys(CalculatorKey.PlusMinus, CalculatorKey.One, CalculatorKey.PlusMinus)
        assertEquals("1", calculatorEngine.calculationText.value)
    }

    @Test
    fun onePlusTwoPlusMinus() {
        pressKeys(CalculatorKey.One, CalculatorKey.Plus, CalculatorKey.Two, CalculatorKey.PlusMinus)
        assertEquals("1 + -2", calculatorEngine.calculationText.value)
    }

    @Test
    fun onePlusTwoPlusMinusPlusMinus() {
        pressKeys(CalculatorKey.One, CalculatorKey.Plus, CalculatorKey.Two, CalculatorKey.PlusMinus, CalculatorKey.PlusMinus)
        assertEquals("1 + 2", calculatorEngine.calculationText.value)
    }

    @Test
    fun onePointTwoPlusMinus() {
        pressKeys(CalculatorKey.One, CalculatorKey.Point, CalculatorKey.Two, CalculatorKey.PlusMinus)
        assertEquals("-1.2", calculatorEngine.calculationText.value)
    }

    @Test
    fun onePointTwoPlusMinusPlusMinus() {
        pressKeys(CalculatorKey.One, CalculatorKey.Point, CalculatorKey.Two, CalculatorKey.PlusMinus, CalculatorKey.PlusMinus)
        assertEquals("1.2", calculatorEngine.calculationText.value)
    }

    @Test
    fun oneTwoThreePlusMinus() {
        pressKeys(CalculatorKey.One, CalculatorKey.Two, CalculatorKey.Three, CalculatorKey.PlusMinus)
        assertEquals("-123", calculatorEngine.calculationText.value)
    }
}