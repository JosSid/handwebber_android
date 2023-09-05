package com.jossidfactory.handwebber.screen.user.login

import android.graphics.Bitmap

data class SignupState(
    val username: String = "",
    val email: String = "",
    val password: String = "",
    val confirmPassword: String = "",
    val image: Bitmap? = null,
    val checked: Boolean = false
)
