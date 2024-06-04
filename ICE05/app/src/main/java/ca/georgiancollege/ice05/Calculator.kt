package ca.georgiancollege.ice05

import android.content.Context
import ca.georgiancollege.ice05.databinding.ActivityMainBinding

class Calculator (private val context:Context, dataBinding: ActivityMainBinding){

    private var binding: ActivityMainBinding = dataBinding
    private var result: String

    init {
        result = ""
        createButtons()
    }

    private fun createButtons() {
        val digitButtons = arrayOf(
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

        digitButtons.forEach { it.setOnClickListener { digitPressHandler(it.tag as String) } }
//        operationButtons.forEach { it.setOnClickListener { operationPressHandler(it.tag as String) } }

        binding.clearButton.setOnClickListener {
            clearScreen()
        }

        binding.deleteButton.setOnClickListener {
            deleteCharacter()
        }

        binding.decimalButton.setOnClickListener {
            addDecimal()
        }

        binding.plusMinusButton.setOnClickListener {
            togglePlusMinus()
        }

    }

    private fun digitPressHandler(tag: String) = when(tag)
    {
        "." -> {}
        "delete" -> {}
        "plus_minus" -> {}
        else -> {
            if(binding.resultTextView.text == "0")
            {
                binding.resultTextView.text = tag
            } else {
                result += tag
                binding.resultTextView.text = result
            }
        }


//        val currentValue = binding.resultTextView.text
//        if(binding.resultTextView.text == "0") {
//            binding.resultTextView.text = tag
//        } else {
//            binding.resultTextView.text = String.format("%s%s", currentValue, tag)
//        }
    }

    private fun operationPressHandler(tag: String)
    {
        binding.resultTextView.text = tag
    }

    private fun clearScreen()
    {
        binding.resultTextView.text = context.getString(R.string.zero_character)
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