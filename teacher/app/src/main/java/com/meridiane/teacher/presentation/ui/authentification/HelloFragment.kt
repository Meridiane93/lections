package com.meridiane.teacher.presentation.ui.authentification

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.meridiane.teacher.R
import com.meridiane.teacher.databinding.FragmentHelloBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HelloFragment : Fragment() {

    private var _binding: FragmentHelloBinding ?= null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHelloBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonRegistration.setOnClickListener{
            findNavController().navigate(R.id.registrationFragment)
        }
        binding.buttonAuthorisation.setOnClickListener{
            findNavController().navigate(R.id.authorizationFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}