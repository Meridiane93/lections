package com.meridiane.lection2_3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.meridiane.lection2_3.fragments.*

class MainActivity : AppCompatActivity() {

    private lateinit var buttonStartFragment: Button
    private lateinit var buttonStartFragment2: Button
    private lateinit var buttonStartFragment3: Button

    private lateinit var buttonStartFragmentA: Button
    private lateinit var buttonStartFragmentB: Button
    private lateinit var buttonStartFragmentC: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        buttonStartFragment = findViewById(R.id.button)
        buttonStartFragment2 = findViewById(R.id.button2)
        buttonStartFragment3 = findViewById(R.id.button3)

        buttonStartFragmentA = findViewById(R.id.button_a)
        buttonStartFragmentB = findViewById(R.id.button_b)
        buttonStartFragmentC = findViewById(R.id.button_c)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .add(R.id.container, Fragment1.newInstance())
                .add(R.id.container2, FragmentA.newInstance())
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

        buttonStartFragmentA.setOnClickListener {
            startFragmentTwo('a')
        }

        buttonStartFragmentB.setOnClickListener {
            startFragmentTwo('b')
        }

        buttonStartFragmentC.setOnClickListener {
            startFragmentTwo('c')
        }
    }

    private fun startFragment(int: Int) {

        val fragment = when (int) {
            1 -> Fragment1.newInstance()
            2 -> Fragment2.newInstance()
            else -> Fragment3.newInstance()
        }

        supportFragmentManager.beginTransaction()
            .replace(R.id.container, fragment)
            .addToBackStack(null)
            .commit()
    }

    private fun startFragmentTwo(str: Char) {

        val fragment = when (str) {
            'a' -> FragmentA.newInstance()
            'b' -> FragmentB.newInstance()
            else -> FragmentC.newInstance()
        }

        supportFragmentManager.beginTransaction()
            .replace(R.id.container2, fragment)
            .addToBackStack(null)
            .commit()
    }

    // Можно избежать повторов кода путём объединения функий в одну или
    // создать ещё одну функцию для supportFragmentManager
}