package com.example.model.notes


data class Note (
    val id: Int,
    val note: String,
    val userId: Int? = null
        )