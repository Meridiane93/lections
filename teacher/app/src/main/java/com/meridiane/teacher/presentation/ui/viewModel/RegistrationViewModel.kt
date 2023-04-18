package com.meridiane.teacher.presentation.ui.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.meridiane.teacher.domain.use_case.Registration
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegistrationViewModel @Inject constructor(
    private val registration: Registration) :
    ViewModel() {

    val liveData = MutableLiveData<String>()

    fun getLogin() {
        viewModelScope.launch {
            try {
                val checkLogin = registration.registrationUser()
                liveData.value = checkLogin.toString()

            } catch (e: Exception) {
                liveData.value = e.message.toString()
            }
        }
    }

    override fun onCleared() {
        viewModelScope.cancel()
    }
}