package com.meridiane.teacher.domain.interface_repository

interface InterfaceAuthorizationRepository {
    suspend fun authorization(): Result<String>
}