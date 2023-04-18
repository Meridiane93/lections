package com.meridiane.teacher.presentation.ui.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.meridiane.teacher.domain.models.Question
import com.meridiane.teacher.domain.use_case.GetQuestions
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class QuestionsViewModel @Inject constructor(private val getQuestions: GetQuestions) :
    ViewModel() {

    private var _studentState = MutableStateFlow<PagingData<Question>>(PagingData.empty())
    val studentState: StateFlow<PagingData<Question>> = _studentState

    fun getQuestions() {

        viewModelScope.launch {

            getQuestions.getQuestions().cachedIn(viewModelScope)
                .collectLatest {
                    _studentState.value = it
                }
        }

    }


    override fun onCleared() {
        viewModelScope.cancel()
    }
}


