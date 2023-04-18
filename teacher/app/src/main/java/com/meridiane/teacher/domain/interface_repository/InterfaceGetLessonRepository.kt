package com.meridiane.teacher.domain.interface_repository

import androidx.paging.PagingData
import com.meridiane.teacher.domain.models.Lesson
import kotlinx.coroutines.flow.Flow

interface InterfaceGetLessonRepository {
    suspend fun getLesson(boolean: Boolean?): List<Lesson>

    fun getListLesson(boolean: Boolean?): Flow<PagingData<Lesson>>

    suspend fun getSizeLesson():List<Int>
}