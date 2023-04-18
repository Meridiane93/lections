package com.meridiane.teacher.presentation.ui.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.meridiane.teacher.domain.models.Lesson
import com.meridiane.teacher.domain.use_case.GetLessons
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class FutureViewModel @Inject constructor(private val getLessons: GetLessons) :
    ViewModel() {

    private var _lessonState = MutableStateFlow<PagingData<Lesson>>(PagingData.empty())
    val lessonState: StateFlow<PagingData<Lesson>> = _lessonState

    fun getQuestions(boolean: Boolean) {

        viewModelScope.launch {

            getLessons.getLessons(boolean).cachedIn(viewModelScope)
                .collectLatest {
                    _lessonState.value = it

                }
        }

    }

    override fun onCleared() {
        viewModelScope.cancel()
    }
}


