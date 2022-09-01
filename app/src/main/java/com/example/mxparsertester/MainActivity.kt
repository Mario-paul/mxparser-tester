package com.example.mxparsertester

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.mxparsertester.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val mxparser = MxparserTester()
        mxparser.testMxparserLibrary("unitInTheLast") // test mXparser

        binding.calculateButton.setOnClickListener {
            binding.outputScreen.text = mxparser.calculate(binding.inputBox.text.toString())
        }

    }

}