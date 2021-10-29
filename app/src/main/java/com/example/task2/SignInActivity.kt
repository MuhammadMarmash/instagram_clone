package com.example.task2

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.view.MotionEvent
import android.view.View
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
    private var icon = 0

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)

        emailEditText = findViewById(R.id.emailEditText)
        passwordEditText = findViewById(R.id.passwordEditText)
        loginButton = findViewById(R.id.signUpButton)
        getHelpSigningText = findViewById(R.id.helpText)
        loginWithFacebookText = findViewById(R.id.logInWithFacebookText)
        signUpText = findViewById(R.id.signInText)

        passwordEditText.setOnTouchListener(View.OnTouchListener { _, event ->
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

        loginButton.setOnClickListener {
            if (emailEditText.text.toString().isEmpty() || passwordEditText.text.toString()
                    .isEmpty()
            ) {
                Toast.makeText(this, "please fill all the fields", Toast.LENGTH_SHORT).show()
            } else {
                val intent = Intent(this, FeedActivity::class.java)
                startActivity(intent)
                finish()
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