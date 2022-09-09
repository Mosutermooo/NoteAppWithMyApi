package com.premium.noteappwithmyapi.repos

import com.premium.noteappwithmyapi.models.AuthRequest
import com.premium.noteappwithmyapi.models.AuthResponse
import retrofit2.Response

interface AuthRepository {
    suspend fun login(loginRequest: AuthRequest): Response<AuthResponse>
    suspend fun register(registerRequest: AuthRequest): Response<AuthResponse>
}