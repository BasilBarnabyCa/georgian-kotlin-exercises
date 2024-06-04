package ca.georgiancollege.ice05

import android.content.Context
import android.util.Log
import ca.georgiancollege.ice05.databinding.ActivityMainBinding

class Calculator (private val context:Context, dataBinding: ActivityMainBinding){

    private var binding: ActivityMainBinding = dataBinding
    private var result: String

    init {
        result = "0"
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

        val operationButtons = arrayOf(
            binding.equalsButton,
            binding.plusButton,
            binding.minusButton,
            binding.multiplicationButton,
            binding.divideButton,
            binding.percentButton
        )

        operandButtons.forEach { it.setOnClickListener { operandPressHandler(it.tag as String) } }
//        operationButtons.forEach { it.setOnClickListener { operationPressHandler(it.tag as String) } }

        binding.clearButton.setOnClickListener {
            clearScreen()
        }
    }

    private fun operandPressHandler(tag: String) {
        when(tag)
        {
            "." -> {
                if(!binding.resultTextView.text.contains("."))
                {
                    result += if(result.isEmpty()) "0." else "."
                    binding.resultTextView.text = result
                }
            }
            "delete" -> {
                result = result.dropLast(1)
                binding.resultTextView.text = if(result.isEmpty() || result == "-") "0" else result
                result = if(result.isEmpty()) "0" else result
            }
            "plus_minus" -> {
                if(result.startsWith("-"))
                {
                    result = result.substring(1)
                }
                else
                {
                    if(result.isNotEmpty() && result != "0")
                    {
                        result = "-".plus(result)
                    }
                }
                binding.resultTextView.text = result
            }
             "clear" -> {
                 clearScreen()
             }
            else -> {
                if(binding.resultTextView.text == "0")
                {
                    result = tag
                    binding.resultTextView.text = result
                }
                else
                {
                    result += tag
                    binding.resultTextView.text = result
                }
            }
        }
    }

    private fun operationPressHandler(tag: String)
    {
        binding.resultTextView.text = tag
    }

    private fun clearScreen()
    {
        result = "0"
        binding.resultTextView.text = "0"
    }

    private fun deleteCharacter()
    {
        val currentValue = binding.resultTextView.text
        if (currentValue != "0" && currentValue.length > 1) {
            val newValue = currentValue.substring(0, currentValue.length - 1)
            if (newValue == "-" || newValue == "-0") {
                binding.resultTextView.text = context.getString(R.string.zero_character)
            } else {
                binding.resultTextView.text= newValue
            }
        } else {
            binding.resultTextView.text = context.getString(R.string.zero_character)
        }
    }

    private fun addDecimal()
    {
        val currentValue = binding.resultTextView.text
        if(!currentValue.contains("."))
            binding.resultTextView.text = String.format("%s%s", currentValue, context.getString(R.string.decimal_icon))
    }

    private fun togglePlusMinus()
    {
        val currentValue = binding.resultTextView.text
        if (currentValue != "0") {
            if (currentValue.contains("-")) {
                binding.resultTextView.text = currentValue.substring(1)
            } else {
                binding.resultTextView.text = String.format("-%s", currentValue)
            }
        }
    }
}