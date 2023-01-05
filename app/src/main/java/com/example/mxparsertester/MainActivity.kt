package com.example.mxparsertester

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.mxparsertester.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private lateinit var mCalculatorViewModel: CalculatorViewModel

    private lateinit var myMxparser: MxparserTester
    private var scientificGroup1Visible = true
    private var scientificRowsHidden = true

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        Obtain ViewModel
        mCalculatorViewModel = ViewModelProvider(this).get(CalculatorViewModel::class.java)

//        ViewModel Livedata Observers
        mCalculatorViewModel.getInputState().observe(this) {
            binding.inputBox.setText(it)
        }
        mCalculatorViewModel.getOutputState().observe(this) {
            binding.outputScreen.text = it
        }

//        mXparser Code
        myMxparser = MxparserTester("unitInTheLast", "Degrees", "Mario Paul")
        myMxparser.confirmNonCommercialUse()
//        mxparser.runPresetTest("unitInTheLast") // test mXparser

//        Application code
        binding.inputBox.post { binding.inputBox.requestFocus() } // sets focus input box on onCreate()
        showGroup1() // set group 1 visibility as default

//        Initialize onClickListeners
        setOnClickListeners()

    }


//    todo: backspace is essential, find a way to add it

    private fun onEqual() {
        mCalculatorViewModel.onEqual()
    }

    private fun onClear() {
        mCalculatorViewModel.onClear()
    }

    private fun onDigit(view: View) {
        val digit = (view as Button).text.toString()
        mCalculatorViewModel.onDigit(digit)
    }

    private fun onDecimalPoint(view: View) {
        val decimal = (view as Button).text.toString()
        mCalculatorViewModel.onDecimalPoint(decimal)
    }

    private fun onOperator(view: View) {
        val operator = (view as Button).text.toString()
        mCalculatorViewModel.onOperator(operator)
    }

    private fun toggleRows() {
        if (scientificRowsHidden) {
            binding.buttonToggleRows.text = resources.getString(R.string.button_toggle_up)
            binding.linearLayoutScientificRow2.visibility = View.VISIBLE
            binding.linearLayoutScientificRow3.visibility = View.VISIBLE
            binding.linearLayoutScientificRow5.visibility = View.VISIBLE
            binding.linearLayoutScientificRow6.visibility = View.VISIBLE
            scientificRowsHidden = false
        } else {
            binding.buttonToggleRows.text = resources.getString(R.string.button_toggle_down)
            binding.linearLayoutScientificRow2.visibility = View.GONE
            binding.linearLayoutScientificRow3.visibility = View.GONE
            binding.linearLayoutScientificRow5.visibility = View.GONE
            binding.linearLayoutScientificRow6.visibility = View.GONE
            scientificRowsHidden = true
        }
    }

    private fun toggleScientificButtons() {

        if (scientificGroup1Visible) {
            showGroup2()
        } else {
            showGroup1()
        }

    }

    private fun showGroup1() {
        binding.buttonSquareRoot.visibility = View.VISIBLE
        binding.buttonSine.visibility = View.VISIBLE
        binding.buttonCosine.visibility = View.VISIBLE
        binding.buttonTangent.visibility = View.VISIBLE
        binding.buttonNaturalLogarithm.visibility = View.VISIBLE
        binding.buttonLogarithm.visibility = View.VISIBLE

        binding.buttonSquare.visibility = View.INVISIBLE
        binding.buttonSineInverse.visibility = View.INVISIBLE
        binding.buttonCosineInverse.visibility = View.INVISIBLE
        binding.buttonTangentInverse.visibility = View.INVISIBLE
        binding.buttonEulerExponent.visibility = View.INVISIBLE
        binding.buttonPower10.visibility = View.INVISIBLE

        scientificGroup1Visible = true
    }

    private fun showGroup2() {
        binding.buttonSquareRoot.visibility = View.INVISIBLE
        binding.buttonSine.visibility = View.INVISIBLE
        binding.buttonCosine.visibility = View.INVISIBLE
        binding.buttonTangent.visibility = View.INVISIBLE
        binding.buttonNaturalLogarithm.visibility = View.INVISIBLE
        binding.buttonLogarithm.visibility = View.INVISIBLE

        binding.buttonSquare.visibility = View.VISIBLE
        binding.buttonSineInverse.visibility = View.VISIBLE
        binding.buttonCosineInverse.visibility = View.VISIBLE
        binding.buttonTangentInverse.visibility = View.VISIBLE
        binding.buttonEulerExponent.visibility = View.VISIBLE
        binding.buttonPower10.visibility = View.VISIBLE

        scientificGroup1Visible = false
    }

    private fun setOnClickListeners() {
        // Digits group
        binding.buttonOne.setOnClickListener { onDigit(it) }
        binding.buttonTwo.setOnClickListener { onDigit(it) }
        binding.buttonThree.setOnClickListener { onDigit(it) }
        binding.buttonFour.setOnClickListener { onDigit(it) }
        binding.buttonFive.setOnClickListener { onDigit(it) }
        binding.buttonSix.setOnClickListener { onDigit(it) }
        binding.buttonSeven.setOnClickListener { onDigit(it) }
        binding.buttonEight.setOnClickListener { onDigit(it) }
        binding.buttonNine.setOnClickListener { onDigit(it) }
        binding.buttonZero.setOnClickListener { onDigit(it) }

        // Basic operators group
        binding.buttonAdd.setOnClickListener { onOperator(it) }
        binding.buttonSubtract.setOnClickListener { onOperator(it) }
        binding.buttonMultiply.setOnClickListener { onOperator(it) }
        binding.buttonDivide.setOnClickListener { onOperator(it) }
        binding.buttonPercentage.setOnClickListener { onOperator(it) }
        binding.buttonParenthesisLeft.setOnClickListener { onOperator(it) }
        binding.buttonParenthesisRight.setOnClickListener { onOperator(it) }

        // Scientific group 1
        binding.buttonSquareRoot.setOnClickListener { onOperator(it) }
        binding.buttonPi.setOnClickListener { onOperator(it) }
        binding.buttonExponent.setOnClickListener { onOperator(it) }
        binding.buttonFactorial.setOnClickListener { onOperator(it) }

        binding.buttonAngleUnits.setOnClickListener { onOperator(it) } // TODO FINISH MIGRATING TO VIEWMODEL
        binding.buttonSine.setOnClickListener { onOperator(it) }
        binding.buttonCosine.setOnClickListener { onOperator(it) }
        binding.buttonTangent.setOnClickListener { onOperator(it) }

        binding.buttonInvert.setOnClickListener { onOperator(it) }
        binding.buttonEulersConstant.setOnClickListener { onOperator(it) }
        binding.buttonNaturalLogarithm.setOnClickListener { onOperator(it) }
        binding.buttonLogarithm.setOnClickListener { onOperator(it) }

        // Scientific group 2
        binding.buttonSquare.setOnClickListener { onOperator(it) }
        binding.buttonPiDummy.setOnClickListener { onOperator(it) }
        binding.buttonExponentDummy.setOnClickListener { onOperator(it) }
        binding.buttonFactorialDummy.setOnClickListener { onOperator(it) }

        binding.buttonAngleUnitsDummy.setOnClickListener { onOperator(it) }
        binding.buttonSineInverse.setOnClickListener { onOperator(it) }
        binding.buttonCosineInverse.setOnClickListener { onOperator(it) }
        binding.buttonTangentInverse.setOnClickListener { onOperator(it) }

        binding.buttonInvertDummy.setOnClickListener { onOperator(it) }
        binding.buttonEulersConstantDummy.setOnClickListener { onOperator(it) }
        binding.buttonEulerExponent.setOnClickListener { onOperator(it) }
        binding.buttonPower10.setOnClickListener { onOperator(it) }

        // Decimal, clear, equal buttons
        binding.buttonPeriod.setOnClickListener { onDecimalPoint(it) }
        binding.buttonClear.setOnClickListener { onClear() }
        binding.buttonEquals.setOnClickListener { onEqual() }
//        binding.buttonBackspace.setOnClickListener { onBackspace() } // TODO ADD THIS FEATURE

        binding.buttonToggleRows.setOnClickListener { toggleRows() }
    }


}