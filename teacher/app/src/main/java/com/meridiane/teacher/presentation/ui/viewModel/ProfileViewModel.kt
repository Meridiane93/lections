package com.meridiane.teacher.presentation.ui.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.meridiane.teacher.domain.models.Teacher
import com.meridiane.teacher.domain.use_case.GetProfile
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(private val getProfile: GetProfile) : ViewModel() {

    private var _profileState = MutableStateFlow(Teacher())
    val profileState: StateFlow<Teacher> = _profileState

    fun getProfile() {

        fun getProfile() = viewModelScope.async {
            getProfile.authorization()
        }

        viewModelScope.launch {
            val getProfileValue = getProfile().await().getOrNull()

            if (getProfileValue != null)
                _profileState.value = getProfileValue
        }

    }

    override fun onCleared() {
        viewModelScope.cancel()
    }
}


