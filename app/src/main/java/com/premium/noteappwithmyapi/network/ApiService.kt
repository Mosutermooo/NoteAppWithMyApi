package com.premium.noteappwithmyapi.network

import com.premium.noteappwithmyapi.models.AuthRequest
import com.premium.noteappwithmyapi.models.AuthResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {

    @POST("login")
    suspend fun login(
        @Body loginRequest: AuthRequest
    ) : Response<AuthResponse>

    @POST("register")
    suspend fun register(
        @Body registerRequest: AuthRequest
    ) : Response<AuthResponse>

}