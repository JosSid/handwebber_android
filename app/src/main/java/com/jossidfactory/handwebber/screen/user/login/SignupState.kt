package com.jossidfactory.handwebber.screen.user.login

data class SignupState(
    val username: String = "",
    val email: String = "",
    val password: String = "",
    val confirmPassword: String = "",
    val image: String = "",
    val checked: Boolean = false
)
