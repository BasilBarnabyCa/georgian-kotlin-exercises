package ca.georgiancollege.ice05

import android.util.Log
import ca.georgiancollege.ice05.databinding.ActivityMainBinding

class Calculator(dataBinding: ActivityMainBinding) {

    private var binding: ActivityMainBinding = dataBinding
    private var result: String
    private var currentOperand: String
    private var currentOperator: String

    init {
        result = "0"
        currentOperand = ""
        currentOperator = ""
        createButtons()
    }

    private fun createButtons() {
        val operandButtons = arrayOf(
            binding.oneButton,
            binding.twoButton,
            binding.threeButton,
            binding.fourButton,
            binding.fiveButton,
            binding.sixButton,
            binding.sevenButton,
            binding.eightButton,
            binding.nineButton,
            binding.zeroButton,
            binding.decimalButton,
            binding.clearButton,
            binding.deleteButton,
            binding.plusMinusButton
        )

        val operatorButtons = arrayOf(
            binding.plusButton,
            binding.minusButton,
            binding.multiplicationButton,
            binding.divideButton,
            binding.equalsButton
        )

        operandButtons.forEach { it.setOnClickListener { operandPressHandler(it.tag as String) } }
        operatorButtons.forEach { it.setOnClickListener { operatorPressHandler(it.tag as String) } }

        binding.clearButton.setOnClickListener {
            clearScreen()
        }
    }

    private fun operandPressHandler(tag: String) {
        when (tag) {
            "." -> {
                if (!binding.resultTextView.text.contains(".")) {
                    result += if (result.isEmpty()) "0." else "."
                    binding.resultTextView.text = result
                }
            }

            "delete" -> {
                result = result.dropLast(1)
                binding.resultTextView.text = if (result.isEmpty() || result == "-") "0" else result
                result = if (result.isEmpty()) "0" else result
            }

            "plus_minus" -> {
                if (result.startsWith("-")) {
                    result = result.substring(1)
                } else {
                    if (result.isNotEmpty() && result != "0") {
                        result = "-".plus(result)
                    }
                }
                binding.resultTextView.text = result
            }

            "clear" -> {
                clearScreen()
            }

            else -> {
                if (binding.resultTextView.text == "0") {
                    result = tag
                    binding.resultTextView.text = result
                } else {
                    result += tag
                    binding.resultTextView.text = result
                }
            }
        }
    }

    private fun operatorPressHandler(tag: String) {
        if (currentOperator.isNotEmpty()) {
            when (currentOperator) {
                "addition" -> {
                    add()
                }
                "subtraction" -> {
                    subtract()
                }
                "multiplication" -> {
                   multiply()
                }
                "division" -> {
                    divide()
                }
                else -> {
                    showAnswer()
                }

            }
        } else {
            currentOperand = binding.resultTextView.text.toString()
            result = "0"
            binding.resultTextView.text = "0"
        }
        currentOperator = tag;
    }

    private fun clearScreen() {
        result = "0"
        binding.resultTextView.text = "0"
        currentOperator = ""
        currentOperand = ""
    }

    private fun add() {
        if (currentOperand.contains(".") || result.contains(".")) {
            result = (currentOperand.toDouble() + result.toDouble()).toString()
        } else {
            result = (currentOperand.toInt() + result.toInt()).toString()
        }
        currentOperand = result
    }

    private fun subtract() {
        if (currentOperand.contains(".") || result.contains(".")) {
            result = (currentOperand.toDouble() - result.toDouble()).toString()
        } else {
            result = (currentOperand.toInt() - result.toInt()).toString()
        }
        currentOperand = result
    }

    private fun multiply()
    {
        if (currentOperand.contains(".") || result.contains(".")) {
            result = (currentOperand.toDouble() * result.toDouble()).toString()
        } else {
            result = (currentOperand.toInt() * result.toInt()).toString()
        }
        currentOperand = result
    }

    private fun divide()
    {
        if (currentOperand.contains(".") || result.contains(".")) {
            result = (currentOperand.toDouble() / result.toDouble()).toString()
        } else {
            result = (currentOperand.toInt() / result.toInt()).toString()
        }
        currentOperand = result
    }

    private fun showAnswer()
    {
        binding.resultTextView.text = currentOperand
        currentOperand = ""
        currentOperator = ""
    }
}