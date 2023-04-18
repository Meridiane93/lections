package com.meridiane.teacher.presentation.ui.profile

import android.app.AlertDialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import coil.load
import coil.transform.CircleCropTransformation
import com.meridiane.teacher.R
import com.meridiane.teacher.databinding.AddTelegramDialogBinding
import com.meridiane.teacher.databinding.FragmentProfileBinding
import com.meridiane.teacher.presentation.ui.viewModel.ProfileViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ProfileFragment : Fragment() {

    private val viewModel: ProfileViewModel by viewModels()

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.shimmerViewContainer.startShimmerAnimation()
        viewModel.getProfile()

        binding.textTelegram.setOnClickListener {
            alertDialog()
        }

        getProfile()
    }

    private fun alertDialog() {
        val builder = AlertDialog.Builder(requireContext())
        val rootDialogElement = AddTelegramDialogBinding.inflate(activity?.layoutInflater!!)

        val dialog = builder.setView(rootDialogElement.root).create()
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        rootDialogElement.textSave.setOnClickListener {
            binding.textTelegram.text = "Telegram: " + rootDialogElement.textLogin.text.toString()
            dialog.dismiss()
        }
        dialog.show()
    }

    private fun getProfile() {
        lifecycleScope.launch {
            viewModel.profileState.collectLatest { profile ->
                with(binding) {
                    if (profile.id != null) {
                        shimmerViewContainer.stopShimmerAnimation()
                        shimmerViewContainer.visibility = View.GONE
                        constraintLayoutProfile.visibility = View.VISIBLE
                        buttonLogout.visibility = View.VISIBLE

                        textName.text = profile.full_name.toString()
                        textEmail.text = "Email: " + profile.email
                        textTelegram.text = if (profile.telegram == "") "Добавьте телеграм"
                        else "Telegram: " + profile.telegram
                        textStudents.text = "Учеников: " + profile.students


                        imagePhoto.load(profile.image) {
                            crossfade(true)
                            crossfade(100)
                            placeholder(R.drawable.ic_photo)
                            transformations(CircleCropTransformation())

                        }
                    }
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}