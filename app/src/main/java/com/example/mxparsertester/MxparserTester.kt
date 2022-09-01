package com.example.mxparsertester

import android.util.Log
import org.mariuszgromada.math.mxparser.Expression
import org.mariuszgromada.math.mxparser.mXparser

class MxparserTester() {

    fun testMxparserLibrary(smartRoundingOption: String) {

        when (smartRoundingOption) {
            "almostIntegerRounding" -> {

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
            "unitInTheLastPlaceRounding" -> {

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
                Log.e("mXparser tester error", "Not a valid smart rounding option selected")
            }
        }

    }

    fun calculate(operation: String) {

//        mXparser library TESTER CODE
        val expression = Expression(operation)
        val result = expression.calculate()
        Log.e(expression.expressionString, result.toString())

    }

}