package com.meridiane.lection2_3.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.meridiane.lection2_3.R

class FragmentC : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_c, container, false)
    }

    companion object {
        @JvmStatic
        fun newInstance() = FragmentC()
    }
}