package com.premium.noteappwithmyapi.network

import com.example.model.notes.Note
import com.example.model.notes.NoteResponse
import com.premium.noteappwithmyapi.models.AuthRequest
import com.premium.noteappwithmyapi.models.AuthResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiService {

    @POST("login")
    suspend fun login(
        @Body loginRequest: AuthRequest
    ) : Response<AuthResponse>

    @POST("register")
    suspend fun register(
        @Body registerRequest: AuthRequest
    ) : Response<AuthResponse>

    @GET("notes/getNotes/{id}")
    suspend fun getNotesByUserId(
        @Path("id") id: Int
    ) : Response<NoteResponse>


}