package com.jossidfactory.handwebber.screen.user.login

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable

@Composable
fun LoginScreen(
    paddingValues: PaddingValues,
    onLoginNavigate: () -> Unit
) {
    LoginForm(paddingValues = paddingValues) {
        onLoginNavigate()
    }

}


