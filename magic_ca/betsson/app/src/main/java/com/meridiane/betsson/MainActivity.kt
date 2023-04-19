package com.meridiane.betsson

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.core.view.GravityCompat
import com.google.android.material.navigation.NavigationView
import com.meridiane.betsson.databinding.ActivityMainBinding
import com.meridiane.betsson.interfaces.FragmentClose
import com.meridiane.betsson.model.MainViewModel
import com.meridiane.betsson.model.MatchesType

class MainActivity : AppCompatActivity(),NavigationView.OnNavigationItemSelectedListener,
    FragmentClose {

    private lateinit var binding : ActivityMainBinding

    private val mainViewModel: MainViewModel by lazy {
        MainViewModel(MainApp.INSTANCE.database)
    }
    private val lists = ArrayList<MatchesType>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        openDraver()
        binding.navView.setNavigationItemSelectedListener(this)

        mainViewModel.allType.observe(this) { list ->
            for (item: MatchesType in list) {
                lists.add(item)
            }
            MatchesFragment(this,lists)
        }

    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.ad_matches -> {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.frameLayout,MatchesFragment(this,lists))
                    .commit()
            }
            R.id.ad_news -> {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.frameLayout,NewsFragment(this,1))
                    .commit()
            }
            R.id.ad_favorites -> {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.frameLayout,FavoritesFragment(this,1))
                    .commit()
            }
            R.id.ad_settings -> {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.frameLayout,SettingsFragment(this))
                    .commit()
            }
        }
        binding.drawerLayout.closeDrawer(GravityCompat.START)    // закрываем выдвигающееся меню
        return true
    }

    private fun openDraver(){
        binding.drawerLayout.open()
    }

    override fun fragmentClose() {
        openDraver()
    }
}