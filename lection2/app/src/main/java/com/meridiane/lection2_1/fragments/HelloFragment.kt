package com.meridiane.lection2_1.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.navigation.fragment.findNavController
import com.meridiane.lection2_1.R


class HelloFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_hello, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val button: Button = view.findViewById(R.id.bt_save)
        val edit: EditText = view.findViewById(R.id.edTextHello)
        val bundle = Bundle()

        edit.setText(arguments?.getString(MainFragment.SEND))

        button.setOnClickListener {
            bundle.putString(MainFragment.SEND, edit.text.toString())

            findNavController().navigate(R.id.action_helloFragment_to_mainFragment,bundle)
        }
    }
}