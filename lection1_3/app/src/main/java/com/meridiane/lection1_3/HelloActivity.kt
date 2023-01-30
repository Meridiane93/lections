package com.meridiane.lection1_3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class HelloActivity : AppCompatActivity() {

    private lateinit var helloText : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hello)

        helloText = findViewById(R.id.textViewHelloActivity)

        startActivityChangeText()
    }

    private fun startActivityChangeText(){
        val getMessageMainActivity = intent.getStringExtra(MainActivity.KEY)

        if (!getMessageMainActivity.isNullOrBlank())
            helloText.text = getString(R.string.textview_get_helloActivity,getMessageMainActivity)
    }

}