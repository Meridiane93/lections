package com.meridiane.lection3.presentation.ui.signin

import android.os.Bundle
import android.view.KeyEvent
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import androidx.fragment.app.commit
import com.meridiane.lection3.R
import androidx.fragment.app.add
import androidx.fragment.app.replace
import com.meridiane.lection3.databinding.FragmentSignInBinding
import com.meridiane.lection3.presentation.ui.catalog.CatalogFragment

class SignInFragment : Fragment() {

    private var _binding: FragmentSignInBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSignInBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {

            buttonSignIn.setOnClickListener {
                checkLoginAndPassword()
            }

            binding.textPassword.setOnEditorActionListener { _, actionId, event ->
                if (actionId == EditorInfo.IME_ACTION_DONE ||
                    event.action == KeyEvent.ACTION_DOWN &&
                    event.keyCode == KeyEvent.KEYCODE_ENTER
                ) {
                    checkLoginAndPassword()
                    true
                }
                else false
            }

        }

    }

    private fun checkLoginAndPassword(){

        when {
            binding.textPassword.text?.isNotEmpty()!!
                    && binding.textLogin.text!!.isNotBlank() -> navigateToCatalog()
            binding.textLogin.text!!.isEmpty() -> binding.textLogin.error = "Поле не заполнено"
            binding.textPassword.text!!.isEmpty() -> binding.textPassword.error = "Поле не заполнено"
            binding.textLogin.text!!.isNotEmpty() -> binding.textLogin.error = null
            binding.textPassword.text!!.isEmpty() -> binding.textPassword.error = "Поле не заполнено"
            binding.textPassword.text!!.isNotEmpty() -> binding.textPassword.error = null
        }

    }

    private fun navigateToCatalog() {
        parentFragmentManager.commit {
            replace<CatalogFragment>(R.id.container)
            addToBackStack(null)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}