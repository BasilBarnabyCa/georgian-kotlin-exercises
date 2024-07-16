package ca.georgiancollege.ice09

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import ca.georgiancollege.ice09.databinding.ActivityRegisterBinding
import com.google.firebase.auth.FirebaseAuth

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding
    private lateinit var auth : FirebaseAuth
    private lateinit var viewModel: TVShowViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Initialize Firebase Auth
        auth = FirebaseAuth.getInstance()

        // Initialize ViewModel
        viewModel = ViewModelProvider(this).get(TVShowViewModel::class.java)

        binding.RegisterButton.setOnClickListener{
            var firstName = binding.FirstNameEditText.text.toString()
            var lastName = binding.LastNameEditText.text.toString()
            var email = binding.EmailEditText.text.toString()
            var password = binding.PasswordText.text.toString()
            var confirmPassword = binding.ConfirmPassword.text.toString()

            if(firstName.isNotEmpty() && lastName.isNotEmpty() && email.isNotEmpty() && password.isNotEmpty() && confirmPassword.isNotEmpty()) {
                if(password == confirmPassword) {
                   auth.createUserWithEmailAndPassword(email, password)
                       .addOnCompleteListener(this) { task ->
                           if (task.isSuccessful) {
                               val user = auth.currentUser

                               user?.let {
                                   val newUser = User(id = it.uid, firstName, lastName, email)
                                   viewModel.insertUser(newUser)
                               }

                               // Navigate to sign in page
                               startActivity(Intent(this, LoginActivity::class.java))
                               finish()
                           } else {
                               Toast.makeText(this, "Registration failed: ${task.exception?.message}", Toast.LENGTH_LONG).show()
                               Log.e("RegisterActivity", "Registration failed", task.exception)
                           }
                       }
                } else {
                    Toast.makeText(this, "Passwords do not match", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "Please enter all fields", Toast.LENGTH_SHORT).show()
            }
        }

        binding.CancelButton.setOnClickListener {
            finish()
        }
    }
}