package com.meridiane.teacher.domain.use_case

import androidx.paging.PagingData
import com.meridiane.teacher.domain.interface_repository.InterfaceGetLessonRepository
import com.meridiane.teacher.domain.models.Lesson
import kotlinx.coroutines.flow.Flow

interface GetLessons {
    suspend fun getLessons(data:Boolean?): Flow<PagingData<Lesson>>

    suspend fun getSizeLesson():List<Int>
}

class GetLessonsUseCaseImpl(private val interfaceGetLessonRepository: InterfaceGetLessonRepository) :
    GetLessons {

    override suspend fun getLessons(data:Boolean?): Flow<PagingData<Lesson>> =
        interfaceGetLessonRepository.getListLesson(data)

    override suspend fun getSizeLesson(): List<Int> =
        interfaceGetLessonRepository.getSizeLesson()

}