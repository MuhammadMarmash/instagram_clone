package com.example.task2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebViewClient
import com.example.task2.databinding.ActivityWebViewBinding

class WebViewActivity : AppCompatActivity() {
    lateinit var binding: ActivityWebViewBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web_view)

        binding = ActivityWebViewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val url = intent.getStringExtra("url").toString()

        binding.webView.loadUrl(url)
        binding.webView.webViewClient = WebViewClient()
//        binding.webView.settings.javaScriptEnabled = true //to make a very simple interactive things
        binding.webView.settings.builtInZoomControls = true // to zoom in and zoom out

    }
    override fun onBackPressed() {// when the back button pressed in the webView
        super.onBackPressed()
        if(binding.webView.canGoBack()){
            binding.webView.goBack()
        }else{
            super.onBackPressed()
        }
    }
}