package com.example.mxparsertester

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.mxparsertester.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private lateinit var myMxparser: MxparserTester

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

// ========================= mXparser Code =================================
        myMxparser = MxparserTester("unitInTheLast")
        myMxparser.confirmNonCommercialUse()
//        mxparser.runPresetTest("unitInTheLast") // test mXparser

// ========================= Application code ==============================
        binding.buttonOne.setOnClickListener { onDigit(binding.buttonOne) }
        binding.buttonTwo.setOnClickListener { onDigit(binding.buttonTwo) }
        binding.buttonThree.setOnClickListener { onDigit(binding.buttonThree) }
        binding.buttonFour.setOnClickListener { onDigit(binding.buttonFour) }
        binding.buttonFive.setOnClickListener { onDigit(binding.buttonFive) }
        binding.buttonSix.setOnClickListener { onDigit(binding.buttonSix) }
        binding.buttonSeven.setOnClickListener { onDigit(binding.buttonSeven) }
        binding.buttonEight.setOnClickListener { onDigit(binding.buttonEight) }
        binding.buttonNine.setOnClickListener { onDigit(binding.buttonNine) }
        binding.buttonZero.setOnClickListener { onDigit(binding.buttonZero) }

        binding.buttonAdd.setOnClickListener { onOperator(binding.buttonAdd) }
        binding.buttonSubtract.setOnClickListener { onOperator(binding.buttonSubtract) }
        binding.buttonMultiply.setOnClickListener { onOperator(binding.buttonMultiply) }
        binding.buttonDivide.setOnClickListener { onOperator(binding.buttonDivide) }
        binding.buttonPercentage.setOnClickListener { onOperator(binding.buttonPercentage) }
        binding.buttonParenthesisLeft.setOnClickListener { onOperator(binding.buttonParenthesisLeft) }
        binding.buttonParenthesisRight.setOnClickListener { onOperator(binding.buttonParenthesisRight) }

        binding.buttonSquareRoot.setOnClickListener { onOperator(binding.buttonSquareRoot) }
        binding.buttonPi.setOnClickListener { onOperator(binding.buttonPi) }
        binding.buttonPower.setOnClickListener { onOperator(binding.buttonPower) }
        binding.buttonFactorial.setOnClickListener { onOperator(binding.buttonFactorial) }

        binding.buttonDegreeRadian.setOnClickListener { onOperator(binding.buttonDegreeRadian) }
        binding.buttonSine.setOnClickListener { onOperator(binding.buttonSine) }
        binding.buttonCosine.setOnClickListener { onOperator(binding.buttonCosine) }
        binding.buttonTangent.setOnClickListener { onOperator(binding.buttonTangent) }

        binding.buttonInverse.setOnClickListener { onOperator(binding.buttonInverse) }
        binding.buttonNapiersConstant.setOnClickListener { onOperator(binding.buttonNapiersConstant) }
        binding.buttonNaturalLogarithm.setOnClickListener { onOperator(binding.buttonNaturalLogarithm) }
        binding.buttonLogarithm.setOnClickListener { onOperator(binding.buttonLogarithm) }

        binding.buttonPeriod.setOnClickListener { onDecimalPoint(binding.buttonPeriod) }
        binding.buttonClear.setOnClickListener { onClear() }
        binding.buttonEquals.setOnClickListener { onEqual() }
//        binding.buttonBackspace.setOnClickListener { onBackspace() }

        binding.inputBox.post { binding.inputBox.requestFocus() } // sets focus input box on onCreate()

    }

//    todo: backspace is essential, find a way to add it

    private fun onEqual() {
        binding.outputScreen.text = myMxparser.calculate(binding.inputBox.text.toString())
    }

    private fun onClear() {
        binding.inputBox.text.clear()
        binding.outputScreen.text = "0.0"
//        myMxparser.checkAngleUnit() // debug, check angle unit (degree or radian) in logcat
    }

    private fun onDigit(view: View) {
        val digit = (view as Button).text
        binding.inputBox.append(digit) // add digit to lcd screen (input)
    }

    private fun onDecimalPoint(view: View) {
        val decimal = (view as Button).text.toString()

        binding.inputBox.append(decimal)
    }

    private fun onOperator(view: View) {

        val operator = (view as Button).text.toString()

        binding.inputBox.text.let {

            when (operator) {

                "-" -> {
                    binding.inputBox.append(operator)
                }
                "+" -> {
                    binding.inputBox.append(operator)
                }
                "÷" -> {
                    binding.inputBox.append(operator)
                }
                "×" -> {
                    binding.inputBox.append(operator)
                }
                "^" -> {
                    binding.inputBox.append(operator)
                }
                "%" -> {
                    binding.inputBox.append(operator)
                }
                "π" -> {
                    binding.inputBox.append(operator)
                }
                "!" -> {
                    binding.inputBox.append(operator)
                }
                "√" -> {
                    binding.inputBox.append(operator)
                }

                "(" -> {
                    binding.inputBox.append(operator)
                }
                ")" -> {
                    binding.inputBox.append(operator)
                }
                "RAD" -> {
                    binding.buttonDegreeRadian.text = "DEG"
                    myMxparser.toggleAngleUnit(myMxparser.getCurrentAngleUnit())
                }
                "DEG" -> {
                    binding.buttonDegreeRadian.text = "RAD"
                    myMxparser.toggleAngleUnit(myMxparser.getCurrentAngleUnit())
                }
                "sin" -> {
                    binding.inputBox.append("sin(")
                }
                "cos" -> {
                    binding.inputBox.append("cos(")
                }
                "tan" -> {
                    binding.inputBox.append("tan(")
                }
                "INV" -> {
//                    binding.inputBox.append(operator)
                }
                "ⅇ" -> {
                    binding.inputBox.append(operator)
                }
                "ln" -> {
                    binding.inputBox.append("ln(")
                }
                "log" -> {
                    binding.inputBox.append("log(")
                }
                else -> {
                    Log.e("onOperator() error", "Not a valid operator inputted")
                }

            }

        }

    }

}