package com.example.bmicalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity() {
    private lateinit var weightTxt: EditText
    private lateinit var heightTxt: EditText
    private lateinit var calBtn: Button
    private lateinit var countTxt: TextView
    private lateinit var resultTxt: TextView
    private lateinit var rangeTxt: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        weightTxt = findViewById(R.id.wightEt)
        heightTxt = findViewById(R.id.hightEt)
        calBtn = findViewById(R.id.calBtn)
        countTxt = findViewById(R.id.countTxt)
        resultTxt = findViewById(R.id.resultTxt)
        rangeTxt = findViewById(R.id.rangeTxt)

        calBtn.setOnClickListener {
            val weight = weightTxt.text.toString()
            val height = heightTxt.text.toString()

            if (validateInput(weight, height)) {
                val bmi = weight.toFloat() / ((height.toFloat() / 100) * (height.toFloat() / 100))

                val bmiDigit = String.format("%.2f", bmi).toFloat()

                displayResult(bmiDigit)
            }
        }

    }

    private fun validateInput(weight: String?, height: String?): Boolean {
        return when {
            weight.isNullOrEmpty() -> {
                Toast.makeText(this, "Weight is empty", Toast.LENGTH_LONG).show()
                return false
            }

            height.isNullOrEmpty() -> {
                Toast.makeText(this, "Height is empty", Toast.LENGTH_LONG).show()
                return false
            }

            else -> {
                return true
            }
        }
    }

    private fun displayResult(bmi: Float) {
        countTxt.text = bmi.toString()
        resultTxt.text = "You are healthy"
        rangeTxt.text = "(Normal range is 18.5-24.5)"

        var result = ""
        var color = 0
        var range = ""

        when {
            bmi < 18.50 -> {
                result = "Underweight"
                color = R.color.under_weight
                range = "(Underweight range is less than 18.50)"
            }

            bmi in 18.50..24.99 -> {
                result = "Healty"
                color = R.color.normal
                range = "(Healty range is 18.50-24.99)"
            }

            bmi in 25.00..29.99 -> {
                result = "Overweight"
                color = R.color.over_weight
                range = "(Overweight range is 24.99-29.99)"
            }

            bmi > 29.99 -> {
                result = "Obese"
                color = R.color.obese
                range = "(Obese range is greater than 29.99)"
            }
        }
        resultTxt.setTextColor(ContextCompat.getColor(this, color))
        resultTxt.text = result
        rangeTxt.setTextColor(ContextCompat.getColor(this, color))
        rangeTxt.text = range
    }
}