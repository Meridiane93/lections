package com.meridiane.teacher.domain.interface_repository

import androidx.paging.PagingData
import com.meridiane.teacher.domain.models.Question
import com.meridiane.teacher.domain.models.Student
import kotlinx.coroutines.flow.Flow

interface InterfaceGetQuestionsRepository {
    suspend fun getQuestions(): List<Question>

    fun getListQuestions(): Flow<PagingData<Question>>
}