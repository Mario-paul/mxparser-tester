package com.example.mxparsertester

import android.util.Log
import org.mariuszgromada.math.mxparser.Expression
import org.mariuszgromada.math.mxparser.License
import org.mariuszgromada.math.mxparser.mXparser

/**
 * Helper class for Mariuz Gromada's [mXparser] math expression parser.
 */
class MxparserTester(smartRoundingOption: String, defaultAngleUnit: String, signature: String) {

    private val mSmartRoundingOption = smartRoundingOption
    private val mDefaultAngleUnit = defaultAngleUnit
    private val mSignature = signature

    init {
        setRoundingOption(mSmartRoundingOption)
        setAngleUnit(mDefaultAngleUnit)
    }

    /**
     * Set the [roundingOption] to the parameter in this function.
     */
    private fun setRoundingOption(roundingOption: String) {

        when (roundingOption) {
            "almostIntegerRounding" -> {
                mXparser.consolePrintln("'Almost Integer Rounding' option selected")
                mXparser.enableAlmostIntRounding()
                mXparser.disableUlpRounding()
                mXparser.disableCanonicalRounding()
            }
            "unitInTheLast" -> {
                mXparser.consolePrintln("'Unit in the Last Place Rounding' option selected")
                mXparser.disableAlmostIntRounding()
                mXparser.enableUlpRounding()
                mXparser.disableCanonicalRounding()
            }
            "canonicalRounding" -> {
                mXparser.consolePrintln("'Canonical Rounding' option selected")
                mXparser.disableAlmostIntRounding()
                mXparser.disableUlpRounding()
                mXparser.enableCanonicalRounding()
            }
            "none" -> {
                mXparser.consolePrintln("Rounding options disabled")
                mXparser.disableAlmostIntRounding()
                mXparser.disableUlpRounding()
                mXparser.disableCanonicalRounding()
            }
            else -> {
                Log.e("mXparser", "Not a valid smart rounding option selected")
            }
        }

    }

    fun calculate(operation: String): String {

        val expression = Expression(operation)
        val result = expression.calculate()

        Log.e(expression.expressionString, result.toString())
        return result.toString()

    }

    /**
     * Set the [angleUnit] to the parameter in this function.
     */
    private fun setAngleUnit(angleUnit: String) {
        if (angleUnit == "radians") {
            mXparser.setRadiansMode()
            mXparser.consolePrintln("Angle units initialized to: ${getCurrentAngleUnit()}")
        } else {
            mXparser.setDegreesMode()
            mXparser.consolePrintln("Angle units initialized to: ${getCurrentAngleUnit()}")
        }
    }

    /**
     * Toggles the angle unit between Degrees and Radians, and returns a string with the new unit type.
     */
    fun toggleAngleUnit(): String {
        return if (mXparser.checkIfRadiansMode()) {
            mXparser.setDegreesMode()
//            mXparser.consolePrintln("Angle units set to: [${getCurrentAngleUnit()}]")
            getCurrentAngleUnit()

        } else {
            mXparser.setRadiansMode()
//            mXparser.consolePrintln("Angle units set to: [${getCurrentAngleUnit()}]")
            getCurrentAngleUnit()
        }
    }

    /**
     * Returns the current angle units.
     */
    fun getCurrentAngleUnit(): String {
        return if (mXparser.checkIfRadiansMode()) {
//            Log.e("current angle units", "radians")
            "radians"
        } else {
//            Log.e("current angle units", "degrees")
            "degrees"
        }
    }

    /**
     * Runs a preset test to visualize the kind of output that each set of settings will yield for
     * the mXparser library.
     */
    fun runPresetTest(smartRoundingOption: String) {

        Log.e("mXparser", "======================================================")
        Log.e("mXparser", "Starting preset test")

        when (smartRoundingOption) {
            "almostIntegerRounding" -> {

                mXparser.consolePrintln("'Almost Integer Rounding' option selected")
                mXparser.enableAlmostIntRounding()
                mXparser.disableUlpRounding()
                mXparser.disableCanonicalRounding()
                mXparser.consolePrintln("checkIfAlmostIntRounding = " + mXparser.checkIfAlmostIntRounding())
                mXparser.consolePrintln("checkIfUlpRounding = " + mXparser.checkIfUlpRounding())
                mXparser.consolePrintln("checkIfCanonicalRounding = " + mXparser.checkIfCanonicalRounding())
                mXparser.consolePrintln("-------------------")
                val e1 = Expression("0.1 + 0.1 + 0.1")
                val e2 = Expression("2.5 - 2.2")
                val e3 = Expression("0.1 + 0.1 + 0.1 + 0.1 + 0.1 + 0.1 + 0.1 + 0.1 + 0.1 + 0.1")
                val e4 = Expression("5.55 / 5")
                val e5 = Expression("(1/6.2)^(-3)")
                mXparser.consolePrintln(e1.expressionString + " = " + e1.calculate())
                mXparser.consolePrintln(e2.expressionString + " = " + e2.calculate())
                mXparser.consolePrintln(e3.expressionString + " = " + e3.calculate())
                mXparser.consolePrintln(e4.expressionString + " = " + e4.calculate())
                mXparser.consolePrintln(e5.expressionString + " = " + e5.calculate())

            }
            "unitInTheLast" -> {

                mXparser.consolePrintln("'Unit in the Last Place Rounding' option selected")
                mXparser.disableAlmostIntRounding()
                mXparser.enableUlpRounding()
                mXparser.disableCanonicalRounding()
                mXparser.consolePrintln("checkIfAlmostIntRounding = " + mXparser.checkIfAlmostIntRounding())
                mXparser.consolePrintln("checkIfUlpRounding = " + mXparser.checkIfUlpRounding())
                mXparser.consolePrintln("checkIfCanonicalRounding = " + mXparser.checkIfCanonicalRounding())
                mXparser.consolePrintln("-------------------")
                val e1 = Expression("0.1 + 0.1 + 0.1")
                val e2 = Expression("2.5 - 2.2")
                val e3 = Expression("0.1 + 0.1 + 0.1 + 0.1 + 0.1 + 0.1 + 0.1 + 0.1 + 0.1 + 0.1")
                val e4 = Expression("5.55 / 5")
                val e5 = Expression("(1/6.2)^(-3)")
                mXparser.consolePrintln(e1.expressionString + " = " + e1.calculate())
                mXparser.consolePrintln(e2.expressionString + " = " + e2.calculate())
                mXparser.consolePrintln(e3.expressionString + " = " + e3.calculate())
                mXparser.consolePrintln(e4.expressionString + " = " + e4.calculate())
                mXparser.consolePrintln(e5.expressionString + " = " + e5.calculate())

            }
            "canonicalRounding" -> {

                mXparser.consolePrintln("'Canonical Rounding' option selected")
                mXparser.disableAlmostIntRounding()
                mXparser.disableUlpRounding()
                mXparser.enableCanonicalRounding()
                mXparser.consolePrintln("checkIfAlmostIntRounding = " + mXparser.checkIfAlmostIntRounding())
                mXparser.consolePrintln("checkIfUlpRounding = " + mXparser.checkIfUlpRounding())
                mXparser.consolePrintln("checkIfCanonicalRounding = " + mXparser.checkIfCanonicalRounding())
                mXparser.consolePrintln("-------------------")
                val e1 = Expression("0.1 + 0.1 + 0.1")
                val e2 = Expression("2.5 - 2.2")
                val e3 = Expression("0.1 + 0.1 + 0.1 + 0.1 + 0.1 + 0.1 + 0.1 + 0.1 + 0.1 + 0.1")
                val e4 = Expression("5.55 / 5")
                val e5 = Expression("(1/6.2)^(-3)")
                mXparser.consolePrintln(e1.expressionString + " = " + e1.calculate())
                mXparser.consolePrintln(e2.expressionString + " = " + e2.calculate())
                mXparser.consolePrintln(e3.expressionString + " = " + e3.calculate())
                mXparser.consolePrintln(e4.expressionString + " = " + e4.calculate())
                mXparser.consolePrintln(e5.expressionString + " = " + e5.calculate())

            }
            "none" -> {

                mXparser.consolePrintln("Rounding options disabled")
                mXparser.disableAlmostIntRounding()
                mXparser.disableUlpRounding()
                mXparser.disableCanonicalRounding()
                mXparser.consolePrintln("checkIfAlmostIntRounding = " + mXparser.checkIfAlmostIntRounding())
                mXparser.consolePrintln("checkIfUlpRounding = " + mXparser.checkIfUlpRounding())
                mXparser.consolePrintln("checkIfCanonicalRounding = " + mXparser.checkIfCanonicalRounding())
                mXparser.consolePrintln("-------------------")
                val e1 = Expression("0.1 + 0.1 + 0.1")
                val e2 = Expression("2.5 - 2.2")
                val e3 = Expression("0.1 + 0.1 + 0.1 + 0.1 + 0.1 + 0.1 + 0.1 + 0.1 + 0.1 + 0.1")
                val e4 = Expression("5.55 / 5")
                val e5 = Expression("(1/6.2)^(-3)")
                mXparser.consolePrintln(e1.expressionString + " = " + e1.calculate())
                mXparser.consolePrintln(e2.expressionString + " = " + e2.calculate())
                mXparser.consolePrintln(e3.expressionString + " = " + e3.calculate())
                mXparser.consolePrintln(e4.expressionString + " = " + e4.calculate())
                mXparser.consolePrintln(e5.expressionString + " = " + e5.calculate())

            }
            else -> {
                Log.e("mXparser", "Not a valid smart rounding option selected")
            }
        }

        mXparser.consolePrintln("Setting rounding option back to init value:")
        setRoundingOption(mSmartRoundingOption)

        Log.e("mXparser", "Test complete")
        Log.e("mXparser", "======================================================")

    }

    fun confirmNonCommercialUse() {
        mXparser.consolePrintln("========== Confirming Non Commercial Use license ============")
        /* Non-Commercial Use Confirmation */
        val isCallSuccessful: Boolean = License.iConfirmNonCommercialUse(mSignature)
        /* Verification if use type has been already confirmed */
        val isConfirmed: Boolean = License.checkIfUseTypeConfirmed()
        /* Checking use type confirmation message */
        val message: String = License.getUseTypeConfirmationMessage()
        /* ----------- */
        mXparser.consolePrintln("isCallSuccessful = $isCallSuccessful")
        mXparser.consolePrintln("isConfirmed = $isConfirmed")
        mXparser.consolePrintln("message = $message")
    }

    fun confirmCommercialUse() {
        mXparser.consolePrintln("========== Confirming Non Commercial Use license ============")
        /* Commercial Use Confirmation */
        val isCallSuccessful = License.iConfirmCommercialUse(mSignature)
        /* Verification if use type has been already confirmed */
        val isConfirmed = License.checkIfUseTypeConfirmed()
        /* Checking use type confirmation message */
        val message = License.getUseTypeConfirmationMessage()
        /* ----------- */
        mXparser.consolePrintln("isCallSuccessful = $isCallSuccessful")
        mXparser.consolePrintln("isConfirmed = $isConfirmed")
        mXparser.consolePrintln("message = $message")
    }

    fun consolePrintLicense() {
        License.consolePrintLicense()
    }

}