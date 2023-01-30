package com.meridiane.lection1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {

    private lateinit var buttonStartActivityRed : Button
    private lateinit var buttonStartActivityGreen : Button
    private lateinit var buttonStartActivityBlue : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        buttonStartActivityRed = findViewById(R.id.id_bt_redActivity)
        buttonStartActivityGreen = findViewById(R.id.id_bt_greenActivity)
        buttonStartActivityBlue = findViewById(R.id.id_bt_blueActivity)

        buttonStartActivityRed.setOnClickListener {
            startActivity(Intent(this, RedActivity::class.java))
        }

        buttonStartActivityGreen.setOnClickListener {
            startActivity(Intent(this, GreenActivity::class.java))
        }

        buttonStartActivityBlue.setOnClickListener {
            startActivity(Intent(this, BlueActivity::class.java))
        }
    }
}