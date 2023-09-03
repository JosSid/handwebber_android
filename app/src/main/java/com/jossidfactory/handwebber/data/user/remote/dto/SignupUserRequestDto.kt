package com.jossidfactory.handwebber.data.user.remote.dto

data class SignupUserRequestDto(
    val username: String,
    val email: String,
    val password: String,
    val image: String?
)
