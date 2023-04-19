package com.meridiane.magiccasino.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import com.meridiane.magiccasino.MAIN
import com.meridiane.magiccasino.R
import com.meridiane.magiccasino.databinding.FragmentStartBinding
import kotlin.system.exitProcess

class StartFragment : Fragment() {

    private lateinit var binding : FragmentStartBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentStartBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btOptionsFalse.setOnClickListener {
            binding.btOptionsFalse.background = ContextCompat.getDrawable(MAIN,R.drawable.main_act_bt_true)
            MAIN.supportFragmentManager.beginTransaction()
                .replace(R.id.nav_host_fragment,OptionsFragment())
                .addToBackStack(null)
                .commit()
        }

        binding.btPlayGamesFalse.setOnClickListener {
            binding.btPlayGamesFalse.background = ContextCompat.getDrawable(MAIN,R.drawable.main_act_bt_true)
            MAIN.supportFragmentManager.beginTransaction()
                .replace(R.id.nav_host_fragment,GamesFragment())
                .addToBackStack(null)
                .commit()
        }

        binding.btExitFalse.setOnClickListener {
            MAIN.finishAffinity()
            exitProcess(0)
        }
    }
}