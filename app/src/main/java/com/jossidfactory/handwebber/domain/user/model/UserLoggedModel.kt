package com.jossidfactory.handwebber.domain.user.model

data class UserLoggedModel(
    val id: String,
    val username: String,
    val email: String,
    val image: String,
    val subscriptions: List<String>?
)

