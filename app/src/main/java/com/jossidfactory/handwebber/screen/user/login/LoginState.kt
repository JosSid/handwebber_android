package com.jossidfactory.handwebber.screen.user.login

import com.jossidfactory.handwebber.common.domain.entity.Error

data class LoginState(
    val email: String = "",
    val password: String = "",
    val isError: Error? = null
)
