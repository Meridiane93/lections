package com.meridiane.magiccasino.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.meridiane.magiccasino.MAIN
import com.meridiane.magiccasino.R
import com.meridiane.magiccasino.databinding.FragmentOptionsBinding

class OptionsFragment : Fragment() {

    private lateinit var binding : FragmentOptionsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentOptionsBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.imOptionsActFinish.setOnClickListener {
            MAIN.supportFragmentManager.beginTransaction()
                .replace(R.id.nav_host_fragment,StartFragment())
                .addToBackStack(null)
                .commit()
        }
    }
}