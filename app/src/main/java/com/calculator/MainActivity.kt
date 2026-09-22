package com.calculator

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    
    private lateinit var display: TextView
    private var currentInput = ""
    private var operator = ""
    private var previousValue = ""
    private var isNewOperation = true
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        
        display = findViewById(R.id.display)
        setupButtons()
    }
    
    private fun setupButtons() {
        val buttonIds = listOf(
            R.id.btn_0, R.id.btn_1, R.id.btn_2, R.id.btn_3, R.id.btn_4,
            R.id.btn_5, R.id.btn_6, R.id.btn_7, R.id.btn_8, R.id.btn_9,
            R.id.btn_add, R.id.btn_subtract, R.id.btn_multiply, R.id.btn_divide,
            R.id.btn_decimal, R.id.btn_clear, R.id.btn_equal, R.id.btn_delete,
            R.id.btn_percent, R.id.btn_negate
        )
        
        buttonIds.forEach { id ->
            val button = findViewById<Button>(id)
            button.setOnClickListener {
                handleButtonClick(button.text.toString())
            }
        }
    }
    
    private fun handleButtonClick(text: String) {
        when (text) {
            "C" -> clearAll()
            "⌫" -> deleteLast()
            "=" -> calculate()
            "+" -> setOperator("+")
            "-" -> setOperator("-")
            "×" -> setOperator("*")
            "÷" -> setOperator("/")
            "." -> addDecimal()
            "%" -> calculatePercent()
            "±" -> negate()
            else -> appendNumber(text)
        }
    }
    
    private fun appendNumber(num: String) {
        if (isNewOperation) {
            currentInput = num
            isNewOperation = false
        } else {
            if (currentInput == "0" && num != ".") {
                currentInput = num
            } else {
                currentInput += num
            }
        }
        updateDisplay()
    }
    
    private fun addDecimal() {
        if (isNewOperation) {
            currentInput = "0."
            isNewOperation = false
        } else if (!currentInput.contains(".")) {
            currentInput += "."
        }
        updateDisplay()
    }
    
    private fun setOperator(op: String) {
        if (operator.isNotEmpty() && !isNewOperation) {
            calculate()
        }
        operator = op
        previousValue = currentInput
        isNewOperation = true
    }
    
    private fun calculate() {
        if (operator.isEmpty() || isNewOperation) return
        
        val current = currentInput.toDoubleOrNull() ?: return
        val prev = previousValue.toDoubleOrNull() ?: return
        var result = 0.0
        
        when (operator) {
            "+" -> result = prev + current
            "-" -> result = prev - current
            "*" -> result = prev * current
            "/" -> result = if (current != 0.0) prev / current else 0.0
        }
        
        currentInput = if (result == result.toInt().toDouble()) {
            result.toInt().toString()
        } else {
            result.toString()
        }
        
        operator = ""
        previousValue = ""
        isNewOperation = true
        updateDisplay()
    }
    
    private fun clearAll() {
        currentInput = "0"
        operator = ""
        previousValue = ""
        isNewOperation = true
        updateDisplay()
    }
    
    private fun deleteLast() {
        if (currentInput.length > 1) {
            currentInput = currentInput.dropLast(1)
        } else {
            currentInput = "0"
            isNewOperation = true
        }
        updateDisplay()
    }
    
    private fun calculatePercent() {
        val value = currentInput.toDoubleOrNull() ?: return
        currentInput = (value / 100).toString()
        updateDisplay()
    }
    
    private fun negate() {
        if (currentInput == "0") return
        if (currentInput.startsWith("-")) {
            currentInput = currentInput.substring(1)
        } else {
            currentInput = "-" + currentInput
        }
        updateDisplay()
    }
    
    private fun updateDisplay() {
        display.text = currentInput
        // Limit display length
        if (currentInput.length > 15) {
            display.text = currentInput.takeLast(15)
        }
    }
}
