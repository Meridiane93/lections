package com.meridiane.magiccasino.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.meridiane.magiccasino.databinding.FragmentBananasBinding

class BananasFragment : Fragment() {

    private lateinit var binding : FragmentBananasBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentBananasBinding.inflate(layoutInflater,container,false)
        return binding.root
    }
}