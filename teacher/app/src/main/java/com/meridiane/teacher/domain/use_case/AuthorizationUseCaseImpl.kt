package com.meridiane.teacher.domain.use_case

import com.meridiane.teacher.domain.interface_repository.InterfaceAuthorizationRepository

interface Authorization {
    suspend fun authorization(): Result<String>
}

class AuthorizationUseCaseImpl(private val interfaceAuthorizationRepository: InterfaceAuthorizationRepository) :
    Authorization {

    override suspend fun authorization(): Result<String> =
        interfaceAuthorizationRepository.authorization()

}