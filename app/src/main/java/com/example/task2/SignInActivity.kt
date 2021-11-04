package com.example.task2

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.view.MotionEvent
import android.view.View
import android.widget.Toast
import com.example.task2.databinding.ActivitySignInBinding

class SignInActivity : AppCompatActivity() {
    lateinit var binding: ActivitySignInBinding
    private var icon = 0

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)

        binding = ActivitySignInBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.passwordEditText.setOnTouchListener(View.OnTouchListener { _, event ->
//            val DRAWABLE_LEFT = 0
//            val DRAWABLE_TOP = 1
//            val DRAWABLE_RIGHT = 2
//            val DRAWABLE_BOTTOM = 3
            if (event.action == MotionEvent.ACTION_UP) {
                if (event.rawX >= binding.passwordEditText.right - binding.passwordEditText.compoundDrawables[2].bounds.width()
                ) {
                    icon = if (icon == 0) {
                        binding.passwordEditText.setCompoundDrawablesWithIntrinsicBounds(
                            R.drawable.password_icon,
                            0,
                            R.drawable.closed_eye_icon,
                            0
                        )
                        binding.passwordEditText.transformationMethod =
                            HideReturnsTransformationMethod.getInstance()
                        1
                    } else {
                        binding.passwordEditText.setCompoundDrawablesWithIntrinsicBounds(
                            R.drawable.password_icon,
                            0,
                            R.drawable.open_eye_icon,
                            0
                        )
                        binding.passwordEditText.transformationMethod =
                            PasswordTransformationMethod.getInstance()

                        0
                    }
                    return@OnTouchListener true
                }
            }
            return@OnTouchListener false
        })

        binding.signUpButton.setOnClickListener {
            if ( binding.emailEditText.text.toString().isEmpty() ||  binding.passwordEditText.text.toString()
                    .isEmpty()
            ) {
                Toast.makeText(this, "please fill all the fields", Toast.LENGTH_SHORT).show()
            } else {
                val intent = Intent(this, FeedActivity::class.java)
                startActivity(intent)
                finish()
            }
        }

        binding.helpText.setOnClickListener {
//            val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse("https://help.instagram.com/"))
//            startActivity(browserIntent)

            val intent = Intent(this, WebViewActivity::class.java)
            intent.putExtra("url", "https://help.instagram.com/")
            startActivity(intent)
        }

        binding.logInWithFacebookText.setOnClickListener {
//            val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/"))
//            startActivity(browserIntent)

            val intent = Intent(this, WebViewActivity::class.java)
            intent.putExtra("url", "https://www.facebook.com/")
            startActivity(intent)
        }

        binding.signInText.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
        }
    }
}