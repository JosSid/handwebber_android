package com.jossidfactory.handwebber.domain.advertisement.model

import com.jossidfactory.handwebber.data.advertisement.dto.IdUser

data class AdvertisementModel(
    val id: String,
    val userId: IdUser,
    val name: String,
    val image: String,
    val description: String,
    val custom: Boolean,
    val price: Double,
    val stock: Int,
    val tags: List<String>,
    val subscriptions: List<String>,
    val update: String,
    val creation: String,
)

data class IdUser(
    val userId: String,
    val username: String
)

