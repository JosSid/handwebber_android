package com.jossidfactory.handwebber.data.user.remote.dto

import okhttp3.MultipartBody
import okhttp3.RequestBody

data class SignupUserRequestDto(
    val username: RequestBody,
    val email: RequestBody,
    val password: RequestBody,
    val image: MultipartBody.Part?
)
