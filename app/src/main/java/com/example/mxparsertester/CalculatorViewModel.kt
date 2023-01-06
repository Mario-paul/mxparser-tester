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
    }

    init {
        state[OUTPUT] = ""
        state[INPUT] = ""
    }

    // Expose an immutable LiveData
    fun getInputState(): LiveData<String?> {
        return state.getLiveData(INPUT)
    }

    fun getOutputState(): LiveData<String?> {
        return state.getLiveData(OUTPUT)
    }


    /* ==================================== Calculator Logic =================================== */

    fun onEqual() {
//        binding.outputScreen.text = myMxparser.calculate(binding.inputBox.text.toString())
        calculate()
    }

    fun onClear() {
//        binding.inputBox.text.clear()
//        binding.outputScreen.text = "0.0"
////        myMxparser.checkAngleUnit() // debug, check angle unit (degree or radian) in logcat

        state[INPUT] = ""
        state[OUTPUT] = ""
    }

    fun onDigit(digit: String) {
//        val digit = (view as Button).text
//        binding.inputBox.append(digit) // add digit to lcd screen (input)

        val currentValue: String? = state[INPUT]
        state[INPUT] = currentValue + digit
    }

    fun onDecimalPoint(decimal: String) {
//        val decimal = (view as Button).text.toString()
//        binding.inputBox.append(decimal)

        val currentValue: String? = state[OUTPUT]
        state[INPUT] = currentValue + decimal
    }

    fun onOperator(operator: String) {

//        val operator = (view as Button).text.toString()

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
//            "RAD" -> {
//                binding.buttonAngleUnits.text = getString(R.string.degrees)
//                binding.buttonAngleUnitsDummy.text = getString(R.string.degrees)
//                myMxparser.toggleAngleUnit(myMxparser.getCurrentAngleUnit())
//            }
//            "DEG" -> {
//                binding.buttonAngleUnits.text = getString(R.string.radians)
//                binding.buttonAngleUnitsDummy.text = getString(R.string.radians)
//                myMxparser.toggleAngleUnit(myMxparser.getCurrentAngleUnit())
//            }
            "sin" -> {
                appendCharacter("$operator(")
            }
            "cos" -> {
                appendCharacter("$operator(")
            }
            "tan" -> {
                appendCharacter("$operator(")
            }
//            "INV" -> {
////                toggleScientificButtons()
//            }
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

    fun calculate() {

        val input: String? = state[INPUT]
        val expression = Expression(input)
        val result = expression.calculate().toString()
        state[OUTPUT] = result

    }

    private fun appendCharacter(char: String) {
        val currentValue: String? = state[INPUT]
        state[INPUT] = currentValue + char
    }

}