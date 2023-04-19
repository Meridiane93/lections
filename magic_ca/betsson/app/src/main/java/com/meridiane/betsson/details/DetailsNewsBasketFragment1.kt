package com.meridiane.betsson.details

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.meridiane.betsson.MainActivity
import com.meridiane.betsson.databinding.FragmentDetailsHockey3Binding
import com.meridiane.betsson.databinding.FragmentDetailsNewsBasket1Binding

class DetailsNewsBasketFragment1 : Fragment() {

    private var _binding: FragmentDetailsNewsBasket1Binding? = null
    private val bindings get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View = FragmentDetailsNewsBasket1Binding.inflate(layoutInflater).also{ _binding = it }.root


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