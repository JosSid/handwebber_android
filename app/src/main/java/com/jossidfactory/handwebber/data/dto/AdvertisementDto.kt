package com.jossidfactory.handwebber.data.dto

import com.squareup.moshi.Json

data class AdvertisementDto(
    @Json(name = "_id") val id: String,
    @Json(name = "idUser") val userId: IdUser,
    @Json(name = "name") val name: String,
    @Json(name = "image") val image: String?,
    @Json(name = "description") val description: String,
    @Json(name = "custom") val custom: Boolean,
    @Json(name = "price") val price: Double,
    @Json(name = "stock") val stock: Int,
    @Json(name = "tags") val tags: List<String>,
    @Json(name = "subscriptions") val subscriptions: List<String>,
    @Json(name = "update") val update: String,
    @Json(name = "creation") val creation: String,
)

data class IdUser(
    @Json(name = "_id") val userId: String,
    val username: String
)