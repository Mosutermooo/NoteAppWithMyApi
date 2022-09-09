package com.example.model.notes


data class NoteResponse (
    val success: Boolean,
    val message: String,
    val notes: List<Note>? = null
        )