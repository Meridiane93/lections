package com.meridiane.lection1_2

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.core.net.toUri

class MainActivity : AppCompatActivity() {

    private lateinit var mainEditText : EditText

    private lateinit var buttonOpenBrowser : Button
    private lateinit var buttonOpenMail : Button
    private lateinit var buttonOpenCall : Button
    private lateinit var buttonOpenMap : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mainEditText = findViewById(R.id.edTextMain)

        buttonOpenBrowser = findViewById(R.id.bt_open_browser)
        buttonOpenMail = findViewById(R.id.bt_open_mail)
        buttonOpenCall = findViewById(R.id.bt_open_call)
        buttonOpenMap = findViewById(R.id.bt_open_map)

        buttonOpenBrowser.setOnClickListener { browser() }
        buttonOpenMail.setOnClickListener { sendEmail() }
        buttonOpenCall.setOnClickListener { call() }
        buttonOpenMap.setOnClickListener { map() }
    }

    private fun browser(){

        val launchBrowser = Intent(Intent.ACTION_VIEW, Uri.parse("https://${mainEditText.text}"))
        startActivity (launchBrowser)
    }

    private fun sendEmail(){

        val iSendEmail = Intent(Intent.ACTION_SEND)
        iSendEmail.type = "message/rfc822"
        iSendEmail.apply {
            putExtra(Intent.EXTRA_EMAIL, arrayOf("${mainEditText.text}@mail.ru"))
            putExtra(Intent.EXTRA_SUBJECT, "Заголовок")
            putExtra(Intent.EXTRA_TEXT, "Описание")
        }

        try { startActivity(Intent.createChooser(iSendEmail,"Открыть с :"))
        } catch (e: ActivityNotFoundException){ }
    }

    private fun call(){

        val callUri = "tel: ${mainEditText.text}"
        val iCall = Intent(Intent.ACTION_DIAL)
        iCall.data = callUri.toUri()
        startActivity(iCall)
    }

    private fun map(){

        val geoUriString = "geo:${mainEditText.text}"+"?z=2" // geo:0,10?z=2
        val geoUri: Uri = Uri.parse(geoUriString)
        val mapIntent = Intent(Intent.ACTION_VIEW, geoUri)

        try { startActivity(mapIntent)
        } catch (ex: ActivityNotFoundException) { }
    }
}