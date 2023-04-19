package com.meridiane.magiccasino

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.meridiane.magiccasino.databinding.ActivityMainBinding
import com.meridiane.magiccasino.fragment.StartFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .add(R.id.nav_host_fragment, StartFragment())
                .commit()
        }
        MAIN = this
    }
}