package com.meridiane.magiccasino.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.meridiane.magiccasino.Common
import com.meridiane.magiccasino.MAIN
import com.meridiane.magiccasino.R
import com.meridiane.magiccasino.databinding.FragmentGamesBinding

class GamesFragment : Fragment() {

    private lateinit var binding : FragmentGamesBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentGamesBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.textBalance.text = Common.SCORE.toString()

        binding.imGamesActFinish.setOnClickListener {
            MAIN.supportFragmentManager.beginTransaction()
                .replace(R.id.nav_host_fragment,StartFragment())
                .addToBackStack(null)
                .commit()
        }

        binding.imMammoth.setOnClickListener {
            MAIN.supportFragmentManager.beginTransaction()
                .replace(R.id.nav_host_fragment,MammonthFragment())
                .addToBackStack(null)
                .commit()
        }

        binding.imBananas.setOnClickListener {
            MAIN.supportFragmentManager.beginTransaction()
                .replace(R.id.nav_host_fragment,BananasFragment())
                .addToBackStack(null)
                .commit()
        }
    }
}