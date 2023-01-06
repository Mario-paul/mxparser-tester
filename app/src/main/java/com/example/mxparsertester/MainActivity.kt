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

    private var defaultScientificButtonsLayer = true
    private var scientificButtonsVisible = false
    private lateinit var currentAngleUnits: String

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
        mCalculatorViewModel.getCurrentAngleUnits().observe(this) {
            currentAngleUnits = it
        }

//        Application code
        binding.inputBox.post { binding.inputBox.requestFocus() } // sets focus input box on onCreate()

//        Initialize onClickListeners
        setOnClickListeners()

//        Initialize mXparser
        mCalculatorViewModel.initializeMxparser()

    }


//    todo: backspace is essential, find a way to add it

    /* ================================== ViewModel Calls ==================================== */
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

    /* ======================================= UI Functions ================================== */
    private fun toggleRows() {

//        vibrator.triggerVibration(chosenVibrationEffect)

        val drawer = binding.buttonDrawer

        if (scientificButtonsVisible) {

            drawer.visibility = View.GONE

            scientificButtonsVisible = false
            binding.buttonDrawerToggle.text = "˅"
//            setMargin(5)

        } else {
            drawer.visibility = View.VISIBLE

            scientificButtonsVisible = true
            binding.buttonDrawerToggle.text = "˄"
//            setMargin(2)
        }

    }

    private fun toggleScientificButtons() {

//        vibrator.triggerVibration(chosenVibrationEffect)

        fun setStringResources(
            button1: Int,
            button2: Int,
            button3: Int,
            button4: Int,
            button5: Int,
            button6: Int,
            defaultLayer: Boolean,
        ) {
            binding.buttonSquareRoot.text = resources.getString(button1)
            binding.buttonSine.text = resources.getString(button2)
            binding.buttonCosine.text = resources.getString(button3)
            binding.buttonTangent.text = resources.getString(button4)
            binding.buttonNaturalLogarithm.text =
                resources.getString(button5)
            binding.buttonLogarithm.text = resources.getString(button6)
            defaultScientificButtonsLayer = defaultLayer
        }

        if (defaultScientificButtonsLayer) {
            setStringResources(
                R.string.button_square,
                R.string.button_sine_inverse,
                R.string.button_cosine_inverse,
                R.string.button_tangent_inverse,
                R.string.button_euler_exponent,
                R.string.button_power_10,
                false,
            )
        } else {
            setStringResources(
                R.string.button_square_root,
                R.string.button_sine,
                R.string.button_cosine,
                R.string.button_tangent,
                R.string.button_natural_logarithm,
                R.string.button_logarithm,
                true,
            )
        }

    }

    private fun toggleAngleUnits() {

//        vibrator.triggerVibration(chosenVibrationEffect)

        mCalculatorViewModel.toggleAngleUnits()

        if (currentAngleUnits == "Radians") {
            binding.buttonAngleUnits.text = resources.getString(R.string.degrees)
        } else {
            binding.buttonAngleUnits.text = resources.getString(R.string.radians)
        }

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
//        binding.buttonParenthesisLeft.setOnClickListener { onOperator(it) }
//        binding.buttonParenthesisRight.setOnClickListener { onOperator(it) }
        binding.buttonParenthesis.setOnClickListener { onOperator(it) } // TODO - TEMPORARY. FIX PARENTHESIS FUNCTIONALITY

        // Scientific group
        binding.buttonSquareRoot.setOnClickListener { onOperator(it) }
        binding.buttonPi.setOnClickListener { onOperator(it) }
        binding.buttonExponent.setOnClickListener { onOperator(it) }
        binding.buttonFactorial.setOnClickListener { onOperator(it) }

        binding.buttonAngleUnits.setOnClickListener { toggleAngleUnits() }
        binding.buttonSine.setOnClickListener { onOperator(it) }
        binding.buttonCosine.setOnClickListener { onOperator(it) }
        binding.buttonTangent.setOnClickListener { onOperator(it) }

        binding.buttonInvert.setOnClickListener { toggleScientificButtons() }
        binding.buttonEulersConstant.setOnClickListener { onOperator(it) }
        binding.buttonNaturalLogarithm.setOnClickListener { onOperator(it) }
        binding.buttonLogarithm.setOnClickListener { onOperator(it) }

        // Decimal, clear, equal buttons
        binding.buttonPeriod.setOnClickListener { onDecimalPoint(it) }
        binding.buttonClear.setOnClickListener { onClear() }
        binding.buttonEquals.setOnClickListener { onEqual() }
//        binding.buttonBackspace.setOnClickListener { onBackspace() } // TODO ADD THIS FEATURE

        binding.buttonDrawerToggle.setOnClickListener { toggleRows() }
    }


}