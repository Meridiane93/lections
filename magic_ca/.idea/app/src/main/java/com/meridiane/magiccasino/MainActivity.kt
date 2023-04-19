package com.meridiane.magiccasino

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.meridiane.magiccasino.databinding.ActivityMainBinding
import com.meridiane.magiccasino.fragment.GamesFragment
import com.meridiane.magiccasino.fragment.StartFragment
import com.meridiane.magiccasino.imageViewScrolling.IEventEnd
import kotlinx.android.synthetic.main.fragment_mammonth.*

class MainActivity : AppCompatActivity(){

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