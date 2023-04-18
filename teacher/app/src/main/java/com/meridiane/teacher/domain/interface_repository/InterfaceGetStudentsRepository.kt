package com.meridiane.teacher.domain.interface_repository

import androidx.paging.PagingData
import com.meridiane.teacher.domain.models.Student
import kotlinx.coroutines.flow.Flow

interface InterfaceGetStudentsRepository {
    suspend fun getStudents(): List<Student>

    fun getListStudents(): Flow<PagingData<Student>>
}