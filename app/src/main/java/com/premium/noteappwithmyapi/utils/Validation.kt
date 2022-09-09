package com.premium.noteappwithmyapi.utils

data class Validation (
    val emailError: String? = null,
    val emailSuccess: Boolean? = null,
    val usernameError: String? = null,
    val usernameSuccess: Boolean? = null,
    val passwordError: String? = null,
    val passwordSuccess: Boolean? = null,
        )