package com.meridiane.betsson.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.meridiane.betsson.MainActivity
import com.meridiane.betsson.databinding.FragmentDetailsNewsBinding

class DetailsNewsFragment: Fragment() {

    private var _binding: FragmentDetailsNewsBinding? = null
    private val bindings get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View = FragmentDetailsNewsBinding.inflate(layoutInflater).also{ _binding = it }.root


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bindings.imageBack.setOnClickListener {
            (context as MainActivity).supportFragmentManager.popBackStack()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}