package com.example.mxparsertester

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.mxparsertester.databinding.ActivityMainBinding
import org.mariuszgromada.math.mxparser.License

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

    }

}