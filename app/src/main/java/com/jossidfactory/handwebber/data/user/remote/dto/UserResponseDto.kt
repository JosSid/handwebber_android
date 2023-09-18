package com.jossidfactory.handwebber.data.user.remote.dto

import com.squareup.moshi.Json

data class UserDto(
    @Json(name = "_id") val id: String,
    @Json(name = "username") val username: String,
    @Json(name = "mail") val email: String?,
    @Json(name = "image") val image: String?,
    @Json(name = "subscriptions") val subscriptions: List<String?>?
)

data class UserResponseDto(
    @Json(name = "result") val result: UserDto
)

