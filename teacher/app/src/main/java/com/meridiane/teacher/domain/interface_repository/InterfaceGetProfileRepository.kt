package com.meridiane.teacher.domain.interface_repository

import com.meridiane.teacher.domain.models.Teacher

interface InterfaceGetProfileRepository {
    suspend fun getProfile(): Result<Teacher>
}