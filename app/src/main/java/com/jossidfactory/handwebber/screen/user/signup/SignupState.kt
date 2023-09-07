package com.jossidfactory.handwebber.screen.user.signup

import android.graphics.Bitmap
import com.jossidfactory.handwebber.common.domain.entity.Error

data class SignupState(
    val username: String = "",
    val email: String = "",
    val password: String = "",
    val confirmPassword: String = "",
    val image: Bitmap? = null,
    val checked: Boolean = false,
    val isError: Error? = null
)
