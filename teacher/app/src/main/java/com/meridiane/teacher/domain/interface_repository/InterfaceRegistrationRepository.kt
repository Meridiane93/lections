package com.meridiane.teacher.domain.interface_repository

interface InterfaceRegistrationRepository {
    suspend fun registration(): Result<String>
}