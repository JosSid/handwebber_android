package com.jossidfactory.handwebber.screen.user.login

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.jossidfactory.handwebber.R
import com.jossidfactory.handwebber.screen.components.ButtonBase
import com.jossidfactory.handwebber.screen.components.CheckboxBase
import com.jossidfactory.handwebber.screen.components.PickerImageField
import com.jossidfactory.handwebber.screen.components.TextFieldBase
import com.jossidfactory.handwebber.screen.components.TextFieldPassword
import org.koin.androidx.compose.koinViewModel

@Composable
fun SignupForm(
    signupViewModel: SignupViewModel = koinViewModel(),
    paddingValues: PaddingValues,
    changeForm: () -> Unit,
    onLoginNavigate: () -> Unit
) {

    val state: SignupState by signupViewModel.state.observeAsState(
        SignupState()
    )

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        item {

            Text(text = "Back to login",
                modifier = Modifier
                    .padding(5.dp)
                    .clickable { changeForm() },
                color = MaterialTheme.colorScheme.primary
            )

            TextFieldBase(text = stringResource(id = R.string.username),
                textValue = state.username ,
                onValueChange = { signupViewModel.onFieldChange(it, "Username") }
            )

            TextFieldBase(text = stringResource(id = R.string.email),
                textValue = state.email ,
                keyboardType = KeyboardType.Email,
                onValueChange = { signupViewModel.onFieldChange(it, "Email") }
            )

            TextFieldPassword(text = stringResource(id = R.string.password) + " (min 8 characters)",
                textValue = state.password,
                onValueChange = { signupViewModel.onFieldChange(it, "Password") }
            )

            TextFieldPassword(text = "Confirm " + stringResource(id = R.string.password),
                textValue = state.confirmPassword,
                onValueChange = { signupViewModel.onFieldChange(it, "Confirm password") }
            )

            PickerImageField { signupViewModel.onImageChange(it) }

            Spacer(modifier = Modifier.padding(3.dp))

            CheckboxBase(
                text = "Accept conditions",
                checked = state.checked
            ) { signupViewModel.onCheckedChange() }

            ButtonBase(
                text = "Signup",
                isEnabled = signupViewModel.isEnabledButton(state)
            ) {
                signupViewModel.onLoginClick(state) {
                    onLoginNavigate()
                }
            }
        }
    }


    
}