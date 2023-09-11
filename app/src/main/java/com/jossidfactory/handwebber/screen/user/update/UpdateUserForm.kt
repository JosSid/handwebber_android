package com.jossidfactory.handwebber.screen.user.update

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.navigation.NavController
import com.jossidfactory.handwebber.R
import com.jossidfactory.handwebber.common.ui.components.button.ButtonBase
import com.jossidfactory.handwebber.common.ui.components.error.ErrorView
import com.jossidfactory.handwebber.common.ui.components.form.PickerImageField
import com.jossidfactory.handwebber.common.ui.components.form.TextFieldBase
import com.jossidfactory.handwebber.common.ui.components.form.TextFieldPassword
import com.jossidfactory.handwebber.navigation.Screen
import com.jossidfactory.handwebber.screen.user.signup.SignupFormFields
import org.koin.androidx.compose.koinViewModel

@Composable
fun UpdateUserForm(
    updateUserViewModel: UpdateUserViewModel = koinViewModel(),
    navController: NavController,
    paddingValues: PaddingValues,
) {

    val state: UpdateUserState by updateUserViewModel.state.observeAsState(
        UpdateUserState()
    )

    if (state.isError == null) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            item {

                TextFieldBase(text = stringResource(id = R.string.username),
                    textValue = state.username,
                    onValueChange = {
                        updateUserViewModel.onFieldChange(
                            it, SignupFormFields
                                .USERNAME
                        )
                    }
                )

                TextFieldBase(text = stringResource(id = R.string.email),
                    textValue = state.email,
                    keyboardType = KeyboardType.Email,
                    onValueChange = {
                        updateUserViewModel.onFieldChange(
                            it,
                            SignupFormFields.EMAIL
                        )
                    }
                )

                TextFieldPassword(text = stringResource(id = R.string.password) + " (min 8 characters)",
                    textValue = state.password,
                    onValueChange = {
                        updateUserViewModel.onFieldChange(
                            it,
                            SignupFormFields.PASSWORD
                        )
                    }
                )

                TextFieldPassword(text = "Confirm " + stringResource(id = R.string.password),
                    textValue = state.confirmPassword,
                    onValueChange = {
                        updateUserViewModel.onFieldChange(
                            it,
                            SignupFormFields.CONFIRM_PASSWORD
                        )
                    }
                )

                PickerImageField { updateUserViewModel.onImageChange(it) }

                ButtonBase(
                    text = "Update"
                ) {
                        updateUserViewModel.onUpdateClick(state) {
                            navController.navigate(Screen.ProfileScreen.route)
                        }
                    }
                }
            }

    }else {
        ErrorView(state.isError!!) {
            updateUserViewModel.onResetError()
        }
    }
}