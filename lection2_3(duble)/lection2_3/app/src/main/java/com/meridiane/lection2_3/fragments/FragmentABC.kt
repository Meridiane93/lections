package com.meridiane.lection2_3.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.meridiane.lection2_3.R

class FragmentABC : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        if (savedInstanceState == null) {
            childFragmentManager.beginTransaction()
                .add(R.id.container_in_fragment, FragmentA.newInstance())
                .commit()
        }

        return inflater.inflate(R.layout.fragment_a_b_c, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val buttonStartFragmentA = view.findViewById<Button>(R.id.button_start_fr_a)
        val buttonStartFragmentB = view.findViewById<Button>(R.id.button_start_fr_b)
        val buttonStartFragmentC = view.findViewById<Button>(R.id.button_start_fr_c)

        buttonStartFragmentA.setOnClickListener {
            startFragment('a')
        }

        buttonStartFragmentB.setOnClickListener {
            startFragment('b')
        }

        buttonStartFragmentC.setOnClickListener {
            startFragment('c')
        }
    }

    private fun startFragment(str: Char) {

        val fragment = when (str) {
            'a' -> FragmentA.newInstance()
            'b' -> FragmentB.newInstance()
            else -> FragmentC.newInstance()
        }

        childFragmentManager.beginTransaction()
            .replace(R.id.container_in_fragment, fragment)
            .addToBackStack(null)
            .commit()
    }

    companion object {
        @JvmStatic
        fun newInstance() = FragmentABC()
    }
}