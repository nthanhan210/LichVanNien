package com.nthanhan.lichvannien

import android.R
import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(com.nthanhan.lichvannien.R.layout.activity_main)

        var url: String? = null
        try {
            // It's good practice to ensure the stream is closed in a finally block
            // or use Kotlin's use function for automatic resource management.
            assets.open("lichvannien.txt").use { inputStream ->
                BufferedReader(InputStreamReader(inputStream)).use { bufferedReader ->
                    url = bufferedReader.readLine() // Đọc dòng đầu tiên
                }
            }
        } catch (e: Exception) { // Catching generic Exception is okay for this example
            e.printStackTrace()
            // You might want to load a default URL or show an error message here
        }

        url?.let {
            // This will now correctly refer to com.nthanhan.lichvannien.R.id.webView
            val webView: WebView = findViewById(com.nthanhan.lichvannien.R.id.webView)
            webView.settings.javaScriptEnabled = true
            webView.webViewClient = WebViewClient() // Uses the imported android.webkit.WebViewClient
            webView.loadUrl(it)
        }

    }
//    @Deprecated("Deprecated in Java")
//    override fun onBackPressed() {
//        // This will now correctly refer to com.nthanhan.lichvannien.R.id.webView
//        val webView: WebView = findViewById(com.nthanhan.lichvannien.R.id.webView)
//        if (webView.canGoBack()) {
//            webView.goBack()
//        } else {
//            super.onBackPressed()
//        }
//    }
}