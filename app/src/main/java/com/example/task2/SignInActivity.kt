package com.example.task2

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class SignInActivity : AppCompatActivity() {
    private lateinit var emailEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var loginButton: Button
    private lateinit var getHelpSigningText: TextView
    private lateinit var loginWithFacebookText: TextView
    private lateinit var signUpText: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)

        emailEditText = findViewById(R.id.emailEditText)
        passwordEditText = findViewById(R.id.passwordEditText)
        loginButton = findViewById(R.id.signUpButton)
        getHelpSigningText = findViewById(R.id.helpText)
        loginWithFacebookText = findViewById(R.id.logInWithFacebookText)
        signUpText = findViewById(R.id.signInText)

        loginButton.setOnClickListener {
            if (emailEditText.text.toString().isEmpty() || passwordEditText.text.toString()
                    .isEmpty()
            ) {
                Toast.makeText(this, "please fill all the fields", Toast.LENGTH_SHORT).show()
            } else {
                val intent = Intent(this, FeedActivity::class.java)
                startActivity(intent)
            }
        }

        getHelpSigningText.setOnClickListener {
            val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse("https://help.instagram.com/"))
            startActivity(browserIntent)
        }

        loginWithFacebookText.setOnClickListener {
            val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/"))
            startActivity(browserIntent)
        }

        signUpText.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
        }
    }
}