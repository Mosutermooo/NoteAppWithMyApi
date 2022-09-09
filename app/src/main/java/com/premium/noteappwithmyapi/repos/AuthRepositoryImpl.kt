package com.premium.noteappwithmyapi.repos

import com.premium.noteappwithmyapi.models.AuthRequest
import com.premium.noteappwithmyapi.network.ApiService

class AuthRepositoryImpl(private val apiService: ApiService) : AuthRepository  {
    override suspend fun login(loginRequest: AuthRequest) = apiService.login(loginRequest = loginRequest)
    override suspend fun register(registerRequest: AuthRequest) = apiService.register(registerRequest)
}