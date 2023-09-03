package com.jossidfactory.handwebber.screen.user.login

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue

enum class ScreenState {
    LOGIN,
    SIGNUP
}

@Composable
fun LoginScreen(
    paddingValues: PaddingValues,
    onLoginNavigate: () -> Unit
) {

    var screenState by rememberSaveable { mutableStateOf(ScreenState.LOGIN) }

    when(screenState) {
        ScreenState.LOGIN -> {
            LoginForm(
                paddingValues = paddingValues,
                changeForm = { screenState = ScreenState.SIGNUP }
                ) {
                onLoginNavigate()
            }
        }

        ScreenState.SIGNUP -> {
            SignupForm(
                paddingValues = paddingValues,
                changeForm = { screenState = ScreenState.LOGIN }
            ) {
                onLoginNavigate()
            }
        }
    }
}


