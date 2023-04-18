package com.meridiane.teacher.domain.use_case

import com.meridiane.teacher.domain.interface_repository.InterfaceGetProfileRepository
import com.meridiane.teacher.domain.models.Teacher

interface GetProfile {
    suspend fun authorization(): Result<Teacher>
}

class GetProfileUseCaseImpl(private val interfaceGetProfileRepository: InterfaceGetProfileRepository) :
    GetProfile {

    override suspend fun authorization(): Result<Teacher> =
        interfaceGetProfileRepository.getProfile()

}