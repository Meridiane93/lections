package com.meridiane.teacher.domain.use_case

import androidx.paging.PagingData
import com.meridiane.teacher.domain.interface_repository.InterfaceGetStudentsRepository
import com.meridiane.teacher.domain.models.Student
import kotlinx.coroutines.flow.Flow

interface GetStudents {
    suspend fun getStudents(): Flow<PagingData<Student>>
}

class GetStudentsUseCaseImpl(private val interfaceGetStudentsRepository: InterfaceGetStudentsRepository) :
    GetStudents {

    override suspend fun getStudents(): Flow<PagingData<Student>> =
        interfaceGetStudentsRepository.getListStudents()

}