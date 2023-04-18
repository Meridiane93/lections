package com.meridiane.teacher.domain.use_case

import com.meridiane.teacher.domain.interface_repository.InterfaceRegistrationRepository

interface Registration {
    suspend fun registrationUser(): Result<String>
}

class RegistrationUseCaseImpl(private val interfaceRegistrationRepository: InterfaceRegistrationRepository) :
    Registration {

    override suspend fun registrationUser(): Result<String> =
        interfaceRegistrationRepository.registration()

}