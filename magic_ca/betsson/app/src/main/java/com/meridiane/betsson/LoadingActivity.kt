package com.meridiane.betsson

import android.animation.ObjectAnimator
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.ProgressBar

class LoadingActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_loading)

        val progress = findViewById<ProgressBar>(R.id.progressBar)
        progress.max = 1000
        val currentProgress = 1000

        ObjectAnimator.ofInt(progress,"progress",currentProgress)
            .setDuration(2000)
            .start()

        Handler().postDelayed({
            val intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
            this.finish()
        },2100)

    }
}