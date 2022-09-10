package com.premium.noteappwithmyapi.models

import com.google.gson.annotations.SerializedName

data class AlreadyLoggedInUser(
    val email_username: String,
    val password: String,
)
