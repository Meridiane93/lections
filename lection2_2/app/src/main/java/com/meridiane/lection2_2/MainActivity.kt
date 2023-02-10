package com.meridiane.lection2_2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.meridiane.lection2_2.fragment.Fragment
import com.meridiane.lection2_2.fragment.Fragment2
import com.meridiane.lection2_2.fragment.Fragment3

class MainActivity : AppCompatActivity() {

    private lateinit var buttonStartFragment: Button
    private lateinit var buttonStartFragment2: Button
    private lateinit var buttonStartFragment3: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        buttonStartFragment = findViewById(R.id.button)
        buttonStartFragment2 = findViewById(R.id.button2)
        buttonStartFragment3 = findViewById(R.id.button3)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .add(R.id.container, Fragment.newInstance())
                .commit()
        }

        buttonStartFragment.setOnClickListener {
            startFragment(1)
        }

        buttonStartFragment2.setOnClickListener {
            startFragment(2)
        }

        buttonStartFragment3.setOnClickListener {
            startFragment(3)
        }
    }

    private fun startFragment(int: Int) {
        
        val fragment = when (int) {
            1 -> Fragment.newInstance()
            2 -> Fragment2.newInstance()
            else -> Fragment3.newInstance()
        }

        supportFragmentManager.beginTransaction()
            .replace(R.id.container, fragment)
            .addToBackStack(null)
            .commit()
    }
}