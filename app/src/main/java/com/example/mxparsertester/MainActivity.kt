package com.example.mxparsertester

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.mxparsertester.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

// ========================= mXparser Code =================================
//        License.iConfirmNonCommercialUse("Mario Paul")
//        License.consolePrintLicense()
        val mxparser = MxparserTester("unitInTheLast")
        mxparser.confirmNonCommercialUse()
//        mxparser.runPresetTest("unitInTheLast") // test mXparser

// ========================= Application code ==============================
        binding.calculateButton.setOnClickListener {
            binding.outputScreen.text = mxparser.calculate(binding.inputBox.text.toString())
        }

        binding.inputBox.post { binding.inputBox.requestFocus() } // focus input box on onCreate()

    }

}