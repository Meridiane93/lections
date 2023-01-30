package com.meridiane.lection1_3

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {

    companion object{
        const val KEY = "send_message_HelloActivity"
    }

    private lateinit var mainEditText : EditText

    private lateinit var buttonStartHelloActivity : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mainEditText = findViewById(R.id.edTextMain)

        buttonStartHelloActivity = findViewById(R.id.btStartHelloActivity)

        buttonStartHelloActivity.setOnClickListener {
            sendMessageHelloActivity()
        }
    }

    private fun sendMessageHelloActivity(){
        val intent: Intent = Intent(this, HelloActivity::class.java)
            .putExtra(KEY, mainEditText.text.toString())

        startActivity(intent)
    }
}