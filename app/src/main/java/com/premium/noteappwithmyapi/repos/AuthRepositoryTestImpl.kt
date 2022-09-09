package com.premium.noteappwithmyapi.repos

import com.premium.noteappwithmyapi.models.AuthRequest
import com.premium.noteappwithmyapi.models.AuthResponse
import okhttp3.ResponseBody
import retrofit2.Response

class AuthRepositoryTestImpl : AuthRepository {
    override suspend fun login(loginRequest: AuthRequest): Response<AuthResponse> {
        if(loginRequest.username == "1" && loginRequest.password == "2"){
            return Response.success(AuthResponse("123", true, "correct", null))
        }
        return Response.error(404, ResponseBody.create(null, "Something went wrong"))
    }

    override suspend fun register(registerRequest: AuthRequest): Response<AuthResponse> {
        if(registerRequest.password.length < 4){
            return Response.error(400, ResponseBody.create(null, "Something went wrong"))
        }
        return Response.success(AuthResponse("Login to get token", true, "correct", null))

    }
}