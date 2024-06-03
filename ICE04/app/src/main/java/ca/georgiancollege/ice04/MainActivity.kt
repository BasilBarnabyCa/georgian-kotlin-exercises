package ca.georgiancollege.ice04

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import ca.georgiancollege.ice04.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity()
{
    // Initializes binding with the shape of ActivityMainBinding
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //Creates a reference to the ActivityMainBinding Class Object
        binding = ActivityMainBinding.inflate(layoutInflater)

        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

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
            binding.zeroButton
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

    private fun digitPressHandler(tag: String)
    {
        val currentValue = binding.resultTextView.text
        if(binding.resultTextView.text == "0") {
            binding.resultTextView.text = tag
        } else {
            binding.resultTextView.text = String.format("%s%s", currentValue, tag)
        }
    }

    private fun operationPressHandler(tag: String)
    {
        binding.resultTextView.text = tag
    }

    private fun clearScreen()
    {
        binding.resultTextView.text = getString(R.string.zero_character)
    }

    private fun deleteCharacter()
    {
        val currentValue = binding.resultTextView.text
        if (currentValue != "0" && currentValue.length > 1) {
            val newValue = currentValue.substring(0, currentValue.length - 1)
            if (newValue == "-" || newValue == "-0") {
                binding.resultTextView.text = getString(R.string.zero_character)
            } else {
                binding.resultTextView.text= newValue
            }
        } else {
            binding.resultTextView.text = getString(R.string.zero_character)
        }
    }

    private fun addDecimal()
    {
        val currentValue = binding.resultTextView.text
        if(!currentValue.contains("."))
            binding.resultTextView.text = String.format("%s%s", currentValue, getString(R.string.decimal_icon))
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