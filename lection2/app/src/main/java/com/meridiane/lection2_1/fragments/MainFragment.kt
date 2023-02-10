package com.meridiane.lection2_1.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.navigation.fragment.findNavController
import com.meridiane.lection2_1.R

class MainFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val button: Button = view.findViewById(R.id.bt_start_next_fragment)
        val text: TextView = view.findViewById(R.id.tx_main_fragment)
        val bundle = Bundle()

        text.text = arguments?.getString(SEND) ?: getString(R.string.text_fr_main)

        button.setOnClickListener {
            bundle.putString(SEND, text.text.toString())

            findNavController().navigate(R.id.action_mainFragment_to_helloFragment, bundle)
        }
    }

    companion object {
        const val SEND = "send"
    }
}