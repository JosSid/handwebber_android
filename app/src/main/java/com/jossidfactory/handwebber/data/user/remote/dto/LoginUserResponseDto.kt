package com.jossidfactory.handwebber.data.user.remote.dto

import com.squareup.moshi.Json

data class LoginUserResponseDto(
    @Json val token: String
)
