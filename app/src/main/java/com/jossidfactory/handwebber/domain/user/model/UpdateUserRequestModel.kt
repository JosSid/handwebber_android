package com.jossidfactory.handwebber.domain.user.model

import android.graphics.Bitmap

data class UpdateUserRequestModel(
    val username: String?,
    val email: String?,
    val password: String?,
    val subscriptions: String?,
    val image: Bitmap?
)
