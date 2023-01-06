package com.example.mxparsertester

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import org.mariuszgromada.math.mxparser.Expression

class CalculatorViewModel(private val state: SavedStateHandle) : ViewModel() {

    companion object {
        private const val OUTPUT = "output"
        private const val INPUT = "input"
        private const val CURRENT_ANGLE_UNIT = "current_angle_unit"
    }

    init {
        state[OUTPUT] = ""
        state[INPUT] = ""
        state[CURRENT_ANGLE_UNIT] = "degrees"
    }

    // Expose an immutable LiveData
    fun getInputState(): LiveData<String> {
        return state.getLiveData(INPUT)
    }

    fun getOutputState(): LiveData<String> {
        return state.getLiveData(OUTPUT)
    }

    fun getCurrentAngleUnits(): LiveData<String> {
        return state.getLiveData(CURRENT_ANGLE_UNIT)
    }


    //        mXparser Code
    private lateinit var myMxparser: MxparserTester


    /* ==================================== Calculator Logic =================================== */

    fun onEqual() {
        calculate()
    }

    fun onClear() {
//        myMxparser.checkAngleUnit() // debug, check angle unit (degree or radian) in logcat

        state[INPUT] = ""
        state[OUTPUT] = ""
    }

    fun onDigit(digit: String) {
        val currentValue: String? = state[INPUT]
        state[INPUT] = currentValue + digit
    }

    fun onDecimalPoint(decimal: String) {
        val currentValue: String? = state[OUTPUT]
        state[INPUT] = currentValue + decimal
    }

    fun onOperator(operator: String) {

        when (operator) {

            "-" -> {
                appendCharacter(operator)
            }
            "+" -> {
                appendCharacter(operator)
            }
            "÷" -> {
                appendCharacter(operator)
            }
            "×" -> {
                appendCharacter(operator)
            }
            "^" -> {
                appendCharacter(operator)
            }
            "%" -> {
                appendCharacter(operator)
            }
            "π" -> {
                appendCharacter(operator)
            }
            "!" -> {
                appendCharacter(operator)
            }
            "√" -> {
                appendCharacter(operator)
            }
            "(" -> {
                appendCharacter(operator)
            }
            ")" -> {
                appendCharacter(operator)
            }
            "sin" -> {
                appendCharacter("$operator(")
            }
            "cos" -> {
                appendCharacter("$operator(")
            }
            "tan" -> {
                appendCharacter("$operator(")
            }
            "ⅇ" -> {
                appendCharacter(operator)
            }
            "ln" -> {
                appendCharacter("$operator(")
            }
            "log" -> {
                appendCharacter("$operator(")
            }
            "x²" -> {
                appendCharacter("^2")
            }
            "sin⁻¹" -> {
                appendCharacter("asin(")
            }
            "cos⁻¹" -> {
                appendCharacter("acos(")
            }
            "tan⁻¹" -> {
                appendCharacter("atan(")
            }
            "eˣ" -> {
                appendCharacter("exp(")
            }
            "10ˣ" -> {
                appendCharacter("10^")
            }
            else -> {
                Log.e("onOperator() error", "Not a valid operator inputted")
            }

        }

    }

    private fun calculate() {
        val input: String? = state[INPUT]
        val expression = Expression(input)
        val result = expression.calculate().toString()
        state[OUTPUT] = result
    }

    private fun appendCharacter(char: String) {
        val currentValue: String? = state[INPUT]
        state[INPUT] = currentValue + char
    }

    fun initializeMxparser() {
        myMxparser = MxparserTester("unitInTheLast", state[CURRENT_ANGLE_UNIT]!!, "Mario Paul")
        myMxparser.confirmNonCommercialUse()
//        mxparser.runPresetTest("unitInTheLast") // test mXparser
    }

    /**
     * Toggles the angle unit between Degrees and Radians.
     */
    fun toggleAngleUnits() {
//        vibrator.triggerVibration(chosenVibrationEffect)
        state[CURRENT_ANGLE_UNIT] = myMxparser.toggleAngleUnit()
        calculate()
    }

}