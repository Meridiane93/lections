package com.meridiane.lection4.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.meridiane.lection4.R
import com.meridiane.lection4.presentation.fragment.BookFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction()
            .add(R.id.container, BookFragment.newInstance())
            .commit()
    }

}