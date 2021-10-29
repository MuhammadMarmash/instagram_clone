package com.example.task2

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import android.view.MotionEvent

import android.view.View.OnTouchListener


class SignUpActivity : AppCompatActivity() {
    private lateinit var emailEditText: EditText
    private lateinit var nameEditText: EditText
    private lateinit var usernameEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var signUpButton: Button
    private lateinit var getHelpSigningText: TextView
    private lateinit var signUpWithFacebookText: TextView
    private lateinit var signInText: TextView

    private var icon = 0

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        emailEditText = findViewById(R.id.emailEditText)
        passwordEditText = findViewById(R.id.passwordEditText)
        nameEditText = findViewById(R.id.nameEditText)
        usernameEditText = findViewById(R.id.usernameEditText)
        signUpButton = findViewById(R.id.signUpButton)
        getHelpSigningText = findViewById(R.id.helpText)
        signUpWithFacebookText = findViewById(R.id.logInWithFacebookText)
        signInText = findViewById(R.id.signInText)

        signUpButton.setOnClickListener {
            if (emailEditText.text.toString().isEmpty()
                || passwordEditText.text.toString().isEmpty()
                || nameEditText.text.toString().isEmpty()
                || usernameEditText.text.toString().isEmpty()
            ) {
                Toast.makeText(this, "please fill all the fields", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(
                    this,
                    "your account is ready please signIn into your account",
                    Toast.LENGTH_SHORT
                ).show()
                val intent = Intent(this, SignInActivity::class.java)
                startActivity(intent)
            }
        }

        getHelpSigningText.setOnClickListener {
            val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse("https://help.instagram.com/"))
            startActivity(browserIntent)
        }

        signUpWithFacebookText.setOnClickListener {
            val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/"))
            startActivity(browserIntent)
        }

        signInText.setOnClickListener {
            val intent = Intent(this, SignInActivity::class.java)
            startActivity(intent)
        }

        passwordEditText.setOnTouchListener(OnTouchListener { _, event ->
//            val DRAWABLE_LEFT = 0
//            val DRAWABLE_TOP = 1
//            val DRAWABLE_RIGHT = 2
//            val DRAWABLE_BOTTOM = 3
            if (event.action == MotionEvent.ACTION_UP) {
                if (event.rawX >= passwordEditText.right - passwordEditText.compoundDrawables[2].bounds.width()
                ) {
                    icon = if (icon == 0) {
                        passwordEditText.setCompoundDrawablesWithIntrinsicBounds(
                            R.drawable.password_icon,
                            0,
                            R.drawable.closed_eye_icon,
                            0
                        )
                        passwordEditText.transformationMethod =
                            HideReturnsTransformationMethod.getInstance()
                        1
                    } else {
                        passwordEditText.setCompoundDrawablesWithIntrinsicBounds(
                            R.drawable.password_icon,
                            0,
                            R.drawable.open_eye_icon,
                            0
                        )
                        passwordEditText.transformationMethod =
                            PasswordTransformationMethod.getInstance()

                        0
                    }
                    return@OnTouchListener true
                }
            }
            return@OnTouchListener false
        })
    }
}
