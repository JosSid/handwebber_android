package com.jossidfactory.handwebber.data.user.remote.dto

import com.squareup.moshi.Json

data class LoginUserDto(
    @Json(name = "mail") val email: String,
    @Json(name = "password") val password: String
)
