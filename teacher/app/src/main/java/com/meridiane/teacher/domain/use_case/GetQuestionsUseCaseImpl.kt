package com.meridiane.teacher.domain.use_case

import androidx.paging.PagingData
import com.meridiane.teacher.domain.interface_repository.InterfaceGetQuestionsRepository
import com.meridiane.teacher.domain.interface_repository.InterfaceGetStudentsRepository
import com.meridiane.teacher.domain.models.Question
import com.meridiane.teacher.domain.models.Student
import kotlinx.coroutines.flow.Flow

interface GetQuestions {
    suspend fun getQuestions(): Flow<PagingData<Question>>
}

class GetQuestionsUseCaseImpl(private val interfaceGetQuestionsRepository: InterfaceGetQuestionsRepository) :
    GetQuestions {

    override suspend fun getQuestions(): Flow<PagingData<Question>> =
        interfaceGetQuestionsRepository.getListQuestions()

}