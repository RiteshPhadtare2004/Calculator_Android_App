package com.example.calculatorapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    private lateinit var resultTextView: TextView
    private var operand1 = 0.0
    private var operand2 = 0.0
    private var operator = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        resultTextView = findViewById(R.id.resultTextView)

        val numberButtons = listOf<Button>(
            findViewById(R.id.btn0), findViewById(R.id.btn1), findViewById(R.id.btn2),
            findViewById(R.id.btn3), findViewById(R.id.btn4), findViewById(R.id.btn5),
            findViewById(R.id.btn6), findViewById(R.id.btn7), findViewById(R.id.btn8),
            findViewById(R.id.btn9)
        )

        val operatorButtons = listOf<Button>(
            findViewById(R.id.btnAdd), findViewById(R.id.btnSubtract),
            findViewById(R.id.btnMultiply), findViewById(R.id.btnDivide)
        )

        val clearButton = findViewById<Button>(R.id.btnClear)
        val equalsButton = findViewById<Button>(R.id.btnEquals)

        numberButtons.forEach { button ->
            button.setOnClickListener {
                onNumberButtonClick(button)
            }
        }

        operatorButtons.forEach { button ->
            button.setOnClickListener {
                onOperatorButtonClick(button)
            }
        }

        clearButton.setOnClickListener {
            clearCalculator()
        }

        equalsButton.setOnClickListener {
            calculateResult()
        }
    }

    private fun onNumberButtonClick(button: Button) {
        val currentInput = resultTextView.text.toString()
        resultTextView.text = "$currentInput${button.text}"
    }

    private fun onOperatorButtonClick(button: Button) {
        operand1 = resultTextView.text.toString().toDouble()
        operator = when (button.id) {
            R.id.btnAdd -> "+"
            R.id.btnSubtract -> "-"
            R.id.btnMultiply -> "*"
            R.id.btnDivide -> "/"
            else -> ""
        }
        resultTextView.text = ""
    }

    private fun calculateResult() {
        operand2 = resultTextView.text.toString().toDouble()

        when (operator) {
            "+" -> resultTextView.text = (operand1 + operand2).toString()
            "-" -> resultTextView.text = (operand1 - operand2).toString()
            "*" -> resultTextView.text = (operand1 * operand2).toString()
            "/" -> {
                if (operand2 != 0.0) {
                    resultTextView.text = (operand1 / operand2).toString()
                } else {
                    resultTextView.text = "Error"
                }
            }

            else -> resultTextView.text = "Error"
        }
    }

    private fun clearCalculator() {
        resultTextView.text = ""
        operand1 = 0.0
        operand2 = 0.0
        operator = ""
    }
}