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
        helloWorldString.text = getString(R.string.hello_string)

        val loginButton = binding.loginButton
        loginButton.setOnClickListener{
            buttonPressHandler(it)
        }

        val logoutButton = binding.logoutButton
        logoutButton.setOnClickListener{
            buttonPressHandler(it)
        }
    }

    // Function to handle both buttons and their actions
    private fun buttonPressHandler(view: View)
    {
        when(view.id)
        {
            R.id.loginButton -> {
                helloWorldString.text = getString(R.string.welcome_back_string)
                Log.i("onCreate", "Login button pressed!")
            }
            R.id.logoutButton -> {
                helloWorldString.text = getString(R.string.good_bye_string)
                Log.i("onCreate", "Logout button pressed!")
            }
        }
    }
}