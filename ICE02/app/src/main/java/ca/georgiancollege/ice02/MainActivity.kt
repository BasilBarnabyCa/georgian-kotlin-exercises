package ca.georgiancollege.ice02

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import ca.georgiancollege.ice02.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity()
{
    // Initializes binding with the shape of ActivityMainBinding
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //creates reference to the ActivityMainBinding Class Object
        binding = ActivityMainBinding.inflate(layoutInflater)

        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val helloWorldString = binding.helloWorldTextView
        helloWorldString.text = getString(R.string.greeting_string)

        val clickMeButton = binding.clickMeButton
        clickMeButton.setOnClickListener{
            Log.i("onCreate", "Click me button pressed")

            binding.helloWorldTextView.text = getString(R.string.good_bye_string)
        }

        val anotherButton = binding.anotherButton
        anotherButton.setOnClickListener{
            Log.i("onCreate", "Another button pressed")

            binding.helloWorldTextView.text = getString(R.string.greeting_string)
        }
    }

    //create new function to share button handling which takes in a view
    // We are going to call sharedButtonHandler and pass in the view? or button?
    fun sharedButtonHandler(view: View)
    {

    }
}