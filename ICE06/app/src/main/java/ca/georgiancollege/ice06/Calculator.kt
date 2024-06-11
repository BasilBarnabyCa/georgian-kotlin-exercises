package ca.georgiancollege.ice06

import ca.georgiancollege.ice06.databinding.ActivityMainBinding

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

        operandButtons.forEach { it.setOnClickListener { operandPressHandler(it.tag as String) } }

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

        if (tag != "clear") {
            if(currentOperand.isNotEmpty()) {
                // perform last operation
                when(currentOperator) {
                    "plus" -> {
                        add()
                    }
                }

            } else {
                currentOperand = binding.resultTextView.text.toString()
                result = ""
                binding.resultTextView.text = ""
            }
            currentOperator = tag
        } else {
            clearScreen()
        }
    }

    private fun add() {
        //Detect if float or int
        if(currentOperand.contains(".") || result.contains(".")) {
//            result = currentOperand.toFloat().plus(result.toFloat()).toString()
            result = (currentOperand.toFloat() + result.toFloat()).toString()
        } else  {
            result = (currentOperand.toFloat() + result.toFloat()).toString()
        }
        // TODO: Remove .0 if the result should be an int
        binding.resultTextView.text = result
    }

    private fun clearScreen() {
        result = "0"
        currentOperand = ""
        currentOperator = ""
        binding.resultTextView.text = "0"
    }
}