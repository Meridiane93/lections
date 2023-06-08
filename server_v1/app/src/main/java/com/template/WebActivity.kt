package com.template

import android.annotation.TargetApi
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.Window
import android.view.WindowInsets
import android.view.WindowInsetsController
import android.view.WindowManager
import android.webkit.CookieManager
import android.webkit.WebResourceRequest
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import android.window.OnBackInvokedDispatcher
import androidx.activity.OnBackPressedCallback
import androidx.activity.OnBackPressedDispatcher
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.BuildCompat
import java.net.HttpCookie
import java.net.URI

class WebActivity : AppCompatActivity() {

    private lateinit var webView: WebView

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.Theme_AppCompat_Light_NoActionBar_FullScreen);
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_web)
        setFullscreen()

        webView = findViewById(R.id.webView)
        webView.settings.javaScriptEnabled = true
        webView.webViewClient = MyWebViewClient()
        val settings: WebSettings = webView.settings
        settings.domStorageEnabled = true

        val extras = intent.extras
        if (extras != null)
            webView.loadUrl("${extras.getString(Constants.SEND_LINK_IN_WEB_VIEW)}")

        cookie(extras?.getString(Constants.SEND_LINK_IN_WEB_VIEW)!!)

        if (Build.VERSION.SDK_INT >= 33) {
            onBackInvokedDispatcher.registerOnBackInvokedCallback(
                OnBackInvokedDispatcher.PRIORITY_DEFAULT
            ) {
                if (webView.canGoBack()) {
                    webView.goBack()
                } else {
                    Log.d("MyTag", "onBackPressed")
                }
                /**
                 * onBackPressed logic goes here. For instance:
                 * Prevents closing the app to go home screen when in the
                 * middle of entering data to a form
                 * or from accidentally leaving a fragment with a WebView in it
                 *
                 * Unregistering the callback to stop intercepting the back gesture:
                 * When the user transitions to the topmost screen (activity, fragment)
                 * in the BackStack, unregister the callback by using
                 * OnBackInvokeDispatcher.unregisterOnBackInvokedCallback
                 * (https://developer.android.com/reference/kotlin/android/window/OnBackInvokedDispatcher#unregisteronbackinvokedcallback)
                 */
            }
        }else {
            onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                if (webView.canGoBack()) {
                    webView.goBack()
                } else {
                    Log.d("MyTag", "onBackPressed")
                }
            }
        })
        }
    }


//    override fun onBackPressed() {
//        if (webView.canGoBack()) {
//            webView.goBack()
//        } else {
//            Log.d("MyTag", "onBackPressed")
//        }
//    }

    private fun cookie(link: String) {
        val cookieManager = java.net.CookieManager()

        webView.webViewClient = object : WebViewClient() {
            override fun onPageFinished(view: WebView?, url: String?) {
                CookieManager.getInstance()
                    .getCookie(url)
                    ?.let {
                        val uri = URI(link)
                        HttpCookie.parse(it).forEach {
                            cookieManager.cookieStore.add(uri, it)
                        }
                    }
            }
        }

    }

    private fun setFullscreen() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            window.attributes.layoutInDisplayCutoutMode =
                WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_SHORT_EDGES
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window.setDecorFitsSystemWindows(false)
            window.insetsController?.apply {
                hide(WindowInsets.Type.statusBars() or WindowInsets.Type.navigationBars())
                systemBarsBehavior = WindowInsetsController.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
            }
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            @Suppress("DEPRECATION")
            window.decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_FULLSCREEN
                    or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                    or View.SYSTEM_UI_FLAG_IMMERSIVE
                    or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                    or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION)
        }
    }
}

private class MyWebViewClient : WebViewClient() {
    @TargetApi(Build.VERSION_CODES.N)
    override fun shouldOverrideUrlLoading(view: WebView, request: WebResourceRequest): Boolean {
        view.loadUrl(request.url.toString())
        return true
    }

    override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
        view.loadUrl(url)
        return true
    }


}