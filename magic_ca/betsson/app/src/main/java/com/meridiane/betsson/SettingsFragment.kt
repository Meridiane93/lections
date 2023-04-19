package com.meridiane.betsson

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.meridiane.betsson.databinding.FragmentDetailsHockey1Binding
import com.meridiane.betsson.databinding.FragmentSettingsBinding
import com.meridiane.betsson.interfaces.FragmentClose

class SettingsFragment(private val fClose: FragmentClose) : Fragment() {

    private var _binding: FragmentSettingsBinding? = null
    private val bindings get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View = FragmentSettingsBinding.inflate(layoutInflater).also{ _binding = it }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bindings.imageDrawer2.setOnClickListener {
            fClose.fragmentClose()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}