package com.premium.noteappwithmyapi.repos

import com.example.model.notes.NoteRequest
import com.premium.noteappwithmyapi.network.ApiService

class NotesRepositoryImpl(private val apiService: ApiService) : NotesRepository {
    override suspend fun addNoteByUserId(noteRequest: NoteRequest) {
        TODO("Not yet implemented")
    }

    override suspend fun getNotesByUserId(userId: Int) {
        TODO("Not yet implemented")
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