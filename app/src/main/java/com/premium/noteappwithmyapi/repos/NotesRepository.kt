package com.premium.noteappwithmyapi.repos

import androidx.lifecycle.LiveData
import com.example.model.notes.Note
import com.example.model.notes.NoteRequest
import com.example.model.notes.NoteResponse
import retrofit2.Response

interface NotesRepository {
    suspend fun addNoteByUserId(noteRequest: NoteRequest?): Response<NoteResponse>
    suspend fun getNotesByUserId(userId: Int?) : Response<NoteResponse>
    suspend fun getNoteById(id: Int)
    suspend fun deleteNoteById(id: Int)
    suspend fun updateNoteById(id: Int, noteRequest: NoteRequest)
}