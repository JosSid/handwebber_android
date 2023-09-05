package com.jossidfactory.handwebber.domain.user.model

import android.graphics.Bitmap

data class SignupUserRequestModel(
    val username: String,
    val email: String,
    val password: String,
    val image: Bitmap?
)
