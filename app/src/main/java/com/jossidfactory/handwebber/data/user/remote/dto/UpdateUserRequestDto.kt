package com.jossidfactory.handwebber.data.user.remote.dto

import okhttp3.MultipartBody
import okhttp3.RequestBody

data class UpdateUserRequestDto(
    val username: RequestBody?,
    val email: RequestBody?,
    val password: RequestBody?,
    val subscriptions: RequestBody?,
    val image: MultipartBody.Part?
)
