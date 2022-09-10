package com.premium.noteappwithmyapi.repos

import androidx.lifecycle.LiveData
import com.example.model.notes.Note
import com.example.model.notes.NoteRequest
import com.example.model.notes.NoteResponse
import com.premium.noteappwithmyapi.network.ApiService
import okhttp3.ResponseBody
import retrofit2.Response

class NotesRepositoryImpl(
    private val apiService: ApiService
) : NotesRepository {
    override suspend fun addNoteByUserId(noteRequest: NoteRequest?) : Response<NoteResponse>{
        TODO("Not yet implemented")
    }

    override suspend fun getNotesByUserId(userId: Int?): Response<NoteResponse> {
        userId?.let {
            return apiService.getNotesByUserId(userId)
        }
        return Response.error(400, ResponseBody.create(null, "Please enter a valid userId"))
    }



    override suspend fun getNoteById(id: Int) {
        TODO("Not yet implemented")
    }

    override suspend fun deleteNoteById(id: Int) {
        TODO("Not yet implemented")
    }

    override suspend fun updateNoteById(id: Int, noteRequest: NoteRequest) {
        TODO("Not yet implemented")
    }
}