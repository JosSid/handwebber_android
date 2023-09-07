package com.jossidfactory.handwebber.screen.user.login

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.jossidfactory.handwebber.common.ui.components.button.ButtonBase
import com.jossidfactory.handwebber.common.ui.components.error.ErrorView
import com.jossidfactory.handwebber.common.ui.components.form.TextFieldBase
import com.jossidfactory.handwebber.common.ui.components.form.TextFieldPassword
import org.koin.androidx.compose.koinViewModel

@Composable
fun LoginForm(
    loginViewModel: LoginViewModel = koinViewModel(),
    paddingValues: PaddingValues,
    changeForm: () -> Unit,
    onLoginNavigate: () -> Unit
) {
    val state: LoginState by loginViewModel.state.observeAsState(
        LoginState()
    )

    if(state.isError == null) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            Text(
                text = "Click here for Signup",
                modifier = Modifier
                    .padding(5.dp)
                    .clickable { changeForm() },
                color = MaterialTheme.colorScheme.primary
            )
            TextFieldBase(text = "Email",
                textValue = state.email,
                keyboardType = KeyboardType.Email,
                onValueChange = { loginViewModel.onEmailChange(it) }
            )

            TextFieldPassword(text = "Password",
                textValue = state.password,
                onValueChange = { loginViewModel.onPasswordChange(it) }
            )

            Spacer(modifier = Modifier.padding(3.dp))

            ButtonBase(
                text = "Login",
                isEnabled = loginViewModel.isEnabledButton(state.email, state.password)
            ) {
                loginViewModel.onLoginClick(state.email, state.password) {
                    onLoginNavigate()
                }
            }
        }
    }else {
        ErrorView(state.isError!!) {
            loginViewModel.onResetError()
        }
    }

}