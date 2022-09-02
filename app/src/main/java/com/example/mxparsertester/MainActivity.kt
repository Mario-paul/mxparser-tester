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
    private var scientificGroup1Visible = true

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

// ========================= mXparser Code =================================
        myMxparser = MxparserTester("unitInTheLast")
        myMxparser.confirmNonCommercialUse()
//        mxparser.runPresetTest("unitInTheLast") // test mXparser

// ========================= Application code ==============================
        binding.inputBox.post { binding.inputBox.requestFocus() } // sets focus input box on onCreate()

        // Digits group
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

        // Basic operators group
        binding.buttonAdd.setOnClickListener { onOperator(binding.buttonAdd) }
        binding.buttonSubtract.setOnClickListener { onOperator(binding.buttonSubtract) }
        binding.buttonMultiply.setOnClickListener { onOperator(binding.buttonMultiply) }
        binding.buttonDivide.setOnClickListener { onOperator(binding.buttonDivide) }
        binding.buttonPercentage.setOnClickListener { onOperator(binding.buttonPercentage) }
        binding.buttonParenthesisLeft.setOnClickListener { onOperator(binding.buttonParenthesisLeft) }
        binding.buttonParenthesisRight.setOnClickListener { onOperator(binding.buttonParenthesisRight) }

        // Scientific group 1
        binding.buttonSquareRoot.setOnClickListener { onOperator(binding.buttonSquareRoot) }
        binding.buttonPi.setOnClickListener { onOperator(binding.buttonPi) }
        binding.buttonExponent.setOnClickListener { onOperator(binding.buttonExponent) }
        binding.buttonFactorial.setOnClickListener { onOperator(binding.buttonFactorial) }

        binding.buttonAngleUnits.setOnClickListener { onOperator(binding.buttonAngleUnits) }
        binding.buttonSine.setOnClickListener { onOperator(binding.buttonSine) }
        binding.buttonCosine.setOnClickListener { onOperator(binding.buttonCosine) }
        binding.buttonTangent.setOnClickListener { onOperator(binding.buttonTangent) }

        binding.buttonInvert.setOnClickListener { onOperator(binding.buttonInvert) }
        binding.buttonEulersConstant.setOnClickListener { onOperator(binding.buttonEulersConstant) }
        binding.buttonNaturalLogarithm.setOnClickListener { onOperator(binding.buttonNaturalLogarithm) }
        binding.buttonLogarithm.setOnClickListener { onOperator(binding.buttonLogarithm) }

        // Scientific group 2
        binding.buttonSquare.setOnClickListener { onOperator(binding.buttonSquare) }
        binding.buttonPiDummy.setOnClickListener { onOperator(binding.buttonPiDummy) }
        binding.buttonExponentDummy.setOnClickListener { onOperator(binding.buttonExponentDummy) }
        binding.buttonFactorialDummy.setOnClickListener { onOperator(binding.buttonFactorialDummy) }

        binding.buttonAngleUnitsDummy.setOnClickListener { onOperator(binding.buttonAngleUnitsDummy) }
        binding.buttonSineInverse.setOnClickListener { onOperator(binding.buttonSineInverse) }
        binding.buttonCosineInverse.setOnClickListener { onOperator(binding.buttonCosineInverse) }
        binding.buttonTangentInverse.setOnClickListener { onOperator(binding.buttonTangentInverse) }

        binding.buttonInvertDummy.setOnClickListener { onOperator(binding.buttonInvertDummy) }
        binding.buttonEulersConstantDummy.setOnClickListener { onOperator(binding.buttonEulersConstantDummy) }
        binding.buttonEulerExponent.setOnClickListener { onOperator(binding.buttonEulerExponent) }
        binding.buttonPower10.setOnClickListener { onOperator(binding.buttonPower10) }

        // Decimal, clear, equal buttons
        binding.buttonPeriod.setOnClickListener { onDecimalPoint(binding.buttonPeriod) }
        binding.buttonClear.setOnClickListener { onClear() }
        binding.buttonEquals.setOnClickListener { onEqual() }
//        binding.buttonBackspace.setOnClickListener { onBackspace() }

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
                    binding.buttonAngleUnits.text = "DEG"
                    binding.buttonAngleUnitsDummy.text = "DEG"
                    myMxparser.toggleAngleUnit(myMxparser.getCurrentAngleUnit())
                }
                "DEG" -> {
                    binding.buttonAngleUnits.text = "RAD"
                    binding.buttonAngleUnitsDummy.text = "RAD"
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
                    toggleScientificButtons()
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
                "x²" -> {
                    binding.inputBox.append("^2")
                }
                "sin⁻¹" -> {
                    binding.inputBox.append("asin(")
                }
                "cos⁻¹" -> {
                    binding.inputBox.append("acos(")
                }
                "tan⁻¹" -> {
                    binding.inputBox.append("atan(")
                }
                "eˣ" -> {
                    binding.inputBox.append("exp(")
                }
                "10ˣ" -> {
                    binding.inputBox.append("10^")
                }
                else -> {
                    Log.e("onOperator() error", "Not a valid operator inputted")
                }

            }

        }

    }

    private fun toggleScientificButtons() {
        if (scientificGroup1Visible) {
            binding.scientificButtonsGroup1.visibility = View.INVISIBLE
            binding.scientificButtonsGroup2.visibility = View.VISIBLE
            scientificGroup1Visible = false
        } else {
            binding.scientificButtonsGroup1.visibility = View.VISIBLE
            binding.scientificButtonsGroup2.visibility = View.INVISIBLE
            scientificGroup1Visible = true
        }
    }


}