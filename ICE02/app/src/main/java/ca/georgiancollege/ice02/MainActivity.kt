package ca.georgiancollege.ice02

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import ca.georgiancollege.ice02.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity()
{
    // Initializes binding with the shape of ActivityMainBinding
    private lateinit var binding: ActivityMainBinding

    // Initializes helloWorldString with the shape of TextView
    private lateinit var helloWorldString: TextView

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

        helloWorldString = binding.helloWorldTextView
        helloWorldString.text = getString(R.string.greeting_string)

        val clickMeButton = binding.clickMeButton
        clickMeButton.setOnClickListener{
            sharedButtonHandler(it)
        }

        val anotherButton = binding.anotherButton
        anotherButton.setOnClickListener{
            sharedButtonHandler(it)
        }
    }

    //create new function to share button handling which takes in a view
    // We are going to call sharedButtonHandler and pass in the view? or button?
    private fun sharedButtonHandler(view: View)
    {
        when(view.id)
        {
            R.id.clickMeButton -> {
                helloWorldString.text = getString(R.string.good_bye_string)
                Log.i("onCreate", "Click me button pressed!")
            }
            R.id.anotherButton -> {
                helloWorldString.text = getString(R.string.welcome_back_string)
                Log.i("onCreate", "Another button pressed!")
            }
        }
    }
}