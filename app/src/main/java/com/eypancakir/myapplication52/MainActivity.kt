package com.eypancakir.myapplication52

import android.os.Bundle
import android.text.Editable
import com.eypancakir.myapplication52.databinding.ActivityMainBinding
import androidx.appcompat.app.AppCompatActivity
import net.objecthunter.exp4j.ExpressionBuilder


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Button click listeners
        binding.button0.setOnClickListener { appendOnExpression("0", true) }
        binding.button1.setOnClickListener { appendOnExpression("1", true) }
        binding.button2.setOnClickListener { appendOnExpression("2", true) }
        binding.button3.setOnClickListener { appendOnExpression("3", true) }
        binding.button4.setOnClickListener { appendOnExpression("4", true) }
        binding.button5.setOnClickListener { appendOnExpression("5", true) }
        binding.button6.setOnClickListener { appendOnExpression("6", true) }
        binding.button7.setOnClickListener { appendOnExpression("7", true) }
        binding.button8.setOnClickListener { appendOnExpression("8", true) }
        binding.button9.setOnClickListener { appendOnExpression("9", true) }

        binding.buttonAdd.setOnClickListener { appendOnExpression("+", false) }
        binding.buttonSubtract.setOnClickListener { appendOnExpression("-", false) }
        binding.buttonMultiply.setOnClickListener { appendOnExpression("*", false) }
        binding.buttonDivide.setOnClickListener { appendOnExpression("/", false) }

        binding.buttonClear.setOnClickListener {
            binding.inputTextView.text = Editable.Factory.getInstance().newEditable("")
            binding.resultTextView.text = ""
        }

        binding.buttonEqual.setOnClickListener {
            try {
                val expression = ExpressionBuilder(binding.inputTextView.text.toString()).build()
                val result = expression.evaluate()
                val longResult = result.toLong()
                if (result == longResult.toDouble()) {
                    binding.resultTextView.text = longResult.toString()
                } else {
                    binding.resultTextView.text = result.toString()
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    private fun appendOnExpression(string: String, canClear: Boolean) {
        if (binding.resultTextView.text.isNotEmpty()) {
            binding.inputTextView.text = Editable.Factory.getInstance().newEditable("")
        }

        if (canClear) {
            binding.resultTextView.text = Editable.Factory.getInstance().newEditable("")
            binding.inputTextView.append(Editable.Factory.getInstance().newEditable(string))
        } else {
            binding.inputTextView.append(Editable.Factory.getInstance().newEditable(binding.resultTextView.text))
            binding.inputTextView.append(Editable.Factory.getInstance().newEditable(string))
            binding.resultTextView.text = Editable.Factory.getInstance().newEditable("")
        }
    }

}