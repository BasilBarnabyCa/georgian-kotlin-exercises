package ca.georgiancollege.ice02

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
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

    // Initializes both buttons with the shape of Button
    private lateinit var loginButton: Button
    private lateinit var logoutButton: Button

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

        // Put initialization of buttons as late initialization bindings
        // then just assign binding value here

//        var loginButton = binding.loginButton
        loginButton = binding.loginButton
        loginButton.setOnClickListener{
            buttonPressHandler(it)
        }

//        var logoutButton = binding.logoutButton
        logoutButton = binding.logoutButton
        logoutButton.setOnClickListener{
            buttonPressHandler(it)
        }
    }

    // Old function to handle both buttons and their actions

    /** You were asked to use ViewBinding for all UI references.
     * Your initial button references did use ViewBinding.
     * However, you used synthetic binding for your button references
     * in your shared event handler. **/
//    private fun buttonPressHandler(view: View)
//    {
//        when(view.id)
//        {
//            R.id.loginButton -> {
//                helloWorldString.text = getString(R.string.welcome_back_string)
//                Log.i("onCreate", "Login button pressed!")
//            }
//            R.id.logoutButton -> {
//                helloWorldString.text = getString(R.string.good_bye_string)
//                Log.i("onCreate", "Logout button pressed!")
//            }
//        }
//    }

    // Updated function to handle both buttons and their actions with proper use of view binding
    private fun buttonPressHandler(view: View)
    {
        when(view)
        {
            // Buttons are now accessed using view binding
            loginButton -> {
                helloWorldString.text = getString(R.string.welcome_back_string)
                Log.i("onCreate", "Login button pressed!")
            }
            logoutButton -> {
                helloWorldString.text = getString(R.string.good_bye_string)
                Log.i("onCreate", "Logout button pressed!")
            }
        }
    }
}