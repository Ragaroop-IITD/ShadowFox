package com.example.calculatorapp


import android.icu.text.DecimalFormat
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.calculatorapp.databinding.ActivityMainBinding
import org.mariuszgromada.math.mxparser.Expression

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupClickListeners()
    }

    private fun addToInputText(value: String) {
        binding.input.text = "${binding.input.text}$value"
    }

    private fun setupClickListeners() {
        binding.buttonClear.setOnClickListener {
            binding.input.text = ""
            binding.output.text = ""
        }

        binding.buttonBracketLeft.setOnClickListener { addToInputText("(") }
        binding.buttonBracketRight.setOnClickListener { addToInputText(")") }
        binding.button0.setOnClickListener { addToInputText("0") }
        binding.button1.setOnClickListener { addToInputText("1") }
        binding.button2.setOnClickListener { addToInputText("2") }
        binding.button3.setOnClickListener { addToInputText("3") }
        binding.button4.setOnClickListener { addToInputText("4") }
        binding.button5.setOnClickListener { addToInputText("5") }
        binding.button6.setOnClickListener { addToInputText("6") }
        binding.button7.setOnClickListener { addToInputText("7") }
        binding.button8.setOnClickListener { addToInputText("8") }
        binding.button9.setOnClickListener { addToInputText("9") }
        binding.buttonDot.setOnClickListener { addToInputText(".") }
        binding.buttonDivison.setOnClickListener { addToInputText("/") }
        binding.buttonMultiply.setOnClickListener { addToInputText("*") }
        binding.buttonSubtraction.setOnClickListener { addToInputText("-") }
        binding.buttonAddition.setOnClickListener { addToInputText("+") }
        binding.buttonEqual.setOnClickListener { showResult() }
    }


    private fun showResult() {
        try {
            val expression = binding.input.text.toString()
            val result = Expression(expression).calculate()
            if (result.isNaN()) {
                // Show error message
                binding.output.text = "Error"
                binding.output.setTextColor(ContextCompat.getColor(this, R.color.red))
            } else {
                // Show result
                binding.output.text = DecimalFormat("0.######").format(result).toString()
                binding.output.setTextColor(ContextCompat.getColor(this, R.color.green))
            }
        } catch (e: Exception) {
            // Show Error message
            binding.output.text = "Error"
            binding.output.setTextColor(ContextCompat.getColor(this, R.color.red))
        }
    }
}