package com.meridiane.teacher.presentation.ui.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.meridiane.teacher.domain.use_case.Authorization
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthorizationViewModel @Inject constructor(
    private val authorization: Authorization) :
    ViewModel() {

    val liveData = MutableLiveData<String>()

    fun getLogin() {
        viewModelScope.launch {
            try {
                val checkLogin = authorization.authorization()
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