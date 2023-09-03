package com.jossidfactory.handwebber.screen.user.login

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.jossidfactory.handwebber.screen.components.ButtonBase
import com.jossidfactory.handwebber.screen.components.TextFieldBase
import com.jossidfactory.handwebber.screen.components.TextFieldPassword
import org.koin.androidx.compose.koinViewModel

@Composable
fun LoginForm(
    loginViewModel: LoginViewModel = koinViewModel(),
    paddingValues: PaddingValues,
    onLoginNavigate: () -> Unit
) {
    val state: LoginState by loginViewModel.state.observeAsState(
        LoginState()
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        TextFieldBase(text = "Email",
            textValue = state.email ,
            keyboardType = KeyboardType.Email,
            onValueChange = { loginViewModel.onEmailChange(it) }
        )

        TextFieldPassword(text = "Password",
            textValue = state.password,
            onValueChange = { loginViewModel.onPasswordChange(it) }
        )

        Spacer(modifier = Modifier.padding(3.dp))

        ButtonBase(text = "Login") {
            loginViewModel.onLoginClick(state.email, state.password) {
                onLoginNavigate()
            }
        }
    }

}