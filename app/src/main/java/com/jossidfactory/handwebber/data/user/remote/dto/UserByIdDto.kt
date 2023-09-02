package com.jossidfactory.handwebber.data.user.remote.dto

import com.squareup.moshi.Json

data class UserByIdDto(
    @Json(name = "_id") val id: String,
    @Json(name = "username") val username: String,
    @Json(name = "image") val image: String?,
    @Json(name = "subscriptions") val subscriptions: List<String?>
)

data class UserByIdResponseDto(
    @Json(name = "result") val result: UserByIdDto
)

//Example response

//{
//    "result": {
//    "_id": "string",
//    "username": "string",
//    "image": "string",
//    "subscriptions": [
//    "string"
//    ]
//  }
//}
