package com.premium.noteappwithmyapi.repos

import com.example.model.notes.NoteRequest

interface NotesRepository {
    suspend fun addNoteByUserId(noteRequest: NoteRequest)
    suspend fun getNotesByUserId(userId: Int)
    suspend fun getNoteById(id: Int)
    suspend fun deleteNoteById(id: Int)
    suspend fun updateNoteById(id: Int, noteRequest: NoteRequest)
}