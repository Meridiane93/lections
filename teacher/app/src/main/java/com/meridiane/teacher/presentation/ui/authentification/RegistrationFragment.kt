package com.meridiane.teacher.presentation.ui.authentification

import android.os.Bundle
import android.util.Patterns
import android.view.*
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.meridiane.teacher.R
import com.meridiane.teacher.databinding.FragmentRegistrationBinding
import com.meridiane.teacher.presentation.ui.viewModel.RegistrationViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegistrationFragment : Fragment() {

    private val viewModel: RegistrationViewModel by viewModels()

    private var _binding: FragmentRegistrationBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val actionbar = (activity as AppCompatActivity).supportActionBar
        actionbar!!.title = "Регистрация"
        actionbar.setDisplayHomeAsUpEnabled(true)
        actionbar.setDisplayHomeAsUpEnabled(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRegistrationBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonSignIn.setOnClickListener {
            checkLoginAndPassword(binding)
        }

        binding.textPassword.setOnEditorActionListener { _, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_DONE ||
                event.action == KeyEvent.ACTION_DOWN &&
                event.keyCode == KeyEvent.KEYCODE_ENTER
            ) {
                checkLoginAndPassword(binding)
                true
            } else false
        }


    }

    private fun checkLoginAndPassword(binding: FragmentRegistrationBinding) {
        with(binding) {

            when {
                isEmailValid(textLogin.text.toString()) &&
                        textPassword.text!!.isNotEmpty() &&
                        textPassword.text!!.toString() == textPasswordRe.text!!.toString() &&
                        textName.text!!.isNotEmpty() &&
                        textLastName.text!!.isNotEmpty() &&
                        textSurname.text!!.isNotEmpty() &&
                        textTelegram.text!!.isNotEmpty() -> {

                    viewModel.getLogin()
                    buttonSignIn.isLoading = true
                    textViewError.visibility = View.GONE
                    buttonSignIn.colorButton(R.color.purple_700)
                }
                else -> Toast.makeText(
                    requireContext(),
                    "Заполнены не все поля",
                    Toast.LENGTH_SHORT
                ).show()
            }


            viewModel.liveData.observe(viewLifecycleOwner) { data ->
                if (data == "Success(is success)") {
                    findNavController().navigate(R.id.fragmentContainerView)
                } else {
                    textViewError.visibility = View.VISIBLE
                    textViewError.text =
                        view?.context?.getString(R.string.error_text, data)

                    buttonSignIn.isLoading
                    buttonSignIn.setText("Повторить")
                    buttonSignIn.colorButton(R.color.error_text)
                }
            }
        }
    }

    private fun isEmailValid(email: String): Boolean =
        Patterns.EMAIL_ADDRESS.matcher(email).matches()

    override fun onDestroyView() {
        super.onDestroyView()
        val actionbar = (activity as AppCompatActivity).supportActionBar
        actionbar!!.title = "Teacher"
        actionbar.setDisplayHomeAsUpEnabled(false)
        actionbar.setDisplayHomeAsUpEnabled(false)
        _binding = null
    }
}