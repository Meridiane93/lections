package com.meridiane.teacher.presentation.ui.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.meridiane.teacher.domain.models.Student
import com.meridiane.teacher.domain.use_case.GetStudents
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class StudentsViewModel @Inject constructor(private val getStudents: GetStudents) :
    ViewModel() {

    private var _studentState = MutableStateFlow<PagingData<Student>>(PagingData.empty())
    val studentState: StateFlow<PagingData<Student>> = _studentState

    fun getStudents() {

        viewModelScope.launch {

            getStudents.getStudents().cachedIn(viewModelScope)
                .collectLatest {
                    _studentState.value = it
                }
        }

    }

    override fun onCleared() {
        viewModelScope.cancel()
    }
}


