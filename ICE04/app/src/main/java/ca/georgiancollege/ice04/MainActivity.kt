package ca.georgiancollege.ice04

import android.os.Bundle
import android.widget.Button
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
            binding.decimalButton
        )

        val operationButtons = arrayOf(
            binding.equalsButton,
            binding.plusButton,
            binding.minusButton,
            binding.multiplicationButton,
            binding.divideButton,
            binding.percentButton,
            binding.deleteButton,
            binding.clearButton,
            binding.plusMinusButton
        )

        digitButtons.forEach { button ->
            button.setOnClickListener {
                digitPressHandler(it as Button)
            }
        }

        operationButtons.forEach { button ->
            button.setOnClickListener {
                operationPressHandler(it as Button)
            }
        }

    }

    private fun digitPressHandler(button: Button)
    {
        binding.resultTextView.text = button.tag.toString()
    }

    private fun operationPressHandler(button: Button)
    {
        binding.resultTextView.text = button.tag.toString()
    }
}