package com.template

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.activity.result.contract.ActivityResultContract
import androidx.appcompat.app.AppCompatActivity
import androidx.browser.customtabs.CustomTabsIntent
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.template.databinding.ActivityLoadingBinding
import java.util.TimeZone
import java.util.UUID


class LoadingActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoadingBinding
    private val db = Firebase.firestore

    private val fbAnalytics = FirebaseAnalytics.getInstance(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoadingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonStart.setOnClickListener {
            db.collection("database").document("check")
                .get()
                .addOnSuccessListener { document ->

                    if (document != null) {
                        val link =
                            setLink(document.data.toString().dropWhile { it != 'h' }.dropLast(1))
                        val cct = startCCT(link)
                        Log.d("MyTag", "$cct")
                    } else errorValueInFb()

                }

                .addOnFailureListener {
                    //Log.d("MyTag", "$exception")
                    errorValueInFb()
                }
        }
    }

    private fun errorValueInFb() {
        val intent = Intent(this@LoadingActivity, MainActivity::class.java)
        startActivity(intent)
    }


    private fun startCCT(link: String) {

        val builder = CustomTabsIntent.Builder()
            .setInitialActivityHeightPx(500)
            .setCloseButtonPosition(CustomTabsIntent.CLOSE_BUTTON_POSITION_END)
        val customTabsIntent = builder.build().intent
        customTabsIntent.data = Uri.parse(link)

        val mCustomTabLauncher =
            registerForActivityResult<Intent, Int>(object : ActivityResultContract<Intent?, Int?>() {

                override fun createIntent(context: Context, input: Intent?): Intent {

                    return customTabsIntent
                }

                override fun parseResult(statusCode: Int, intent: Intent?): Int {
                    return statusCode
                }

            }) {
                // ...
            }
        mCustomTabLauncher.launch(customTabsIntent)

    }


    private fun setLink(link: String): String {

        val pack = this.packageName
        val uuid = UUID.randomUUID().toString()
        val timeZone = TimeZone.getDefault().toString().substringAfter('"')

        return "$link/?packageid=$pack&usserid=$uuid&getz=${timeZone.substringBeforeLast('"')}&getr=utm_source=google-play&utm_medium=organic"
    }

}

