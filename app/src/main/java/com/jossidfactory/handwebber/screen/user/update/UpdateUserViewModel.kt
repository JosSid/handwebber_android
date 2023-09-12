package com.jossidfactory.handwebber.screen.user.update

import android.graphics.Bitmap
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jossidfactory.handwebber.common.domain.mappers.logError
import com.jossidfactory.handwebber.common.domain.mappers.toError
import com.jossidfactory.handwebber.domain.user.model.UpdateUserRequestModel
import com.jossidfactory.handwebber.domain.user.usecase.GetLoggedUserUseCase
import com.jossidfactory.handwebber.domain.user.usecase.UpdateUserUseCase
import com.jossidfactory.handwebber.screen.user.signup.SignupFormFields
import kotlinx.coroutines.launch

class UpdateUserViewModel(
    private val updateUserUseCase: UpdateUserUseCase,
    private val getLoggedUserUseCase: GetLoggedUserUseCase
): ViewModel() {
    private val updateUserState = UpdateUserState()

    private val _state = MutableLiveData<UpdateUserState>()
    val state: LiveData<UpdateUserState> = _state

    init {
        _state.value = updateUserState
    }

    fun onImageChange(value: Bitmap?) {
        _state.value = _state.value?.copy(
            image = value
        )
    }

    fun onFieldChange(value: String, type: SignupFormFields) {
        when (type) {
            SignupFormFields.USERNAME-> {
                _state.value = _state.value?.copy(
                    username = value
                )
            }

            SignupFormFields.EMAIL -> {
                _state.value = _state.value?.copy(
                    email = value
                )
            }

            SignupFormFields.PASSWORD -> {
                _state.value = _state.value?.copy(
                    password = value
                )
            }

            SignupFormFields.CONFIRM_PASSWORD -> {
                _state.value = _state.value?.copy(
                    confirmPassword = value
                )
            }
        }
    }

    fun onUpdateClick(state: UpdateUserState,navigate: () -> Unit) {
        val requestBody = UpdateUserRequestModel(
            username = if(state.username != "") state.username else null,
            email = if(state.email != "") state.email else null,
            password = if(state.password != "" && state.password == state.confirmPassword) state
                .password else null,
            subscriptions = null,
            image = state.image
        )

        viewModelScope.launch {
            try {
                val user = getLoggedUserUseCase.invoke()
                if (user != null) {
                    updateUserUseCase.invoke(user.id, requestBody)
                }
                navigate()
            } catch (e: Throwable) {
                _state.value = _state.value?.copy(
                    isError = e.toError()
                )
                e.logError()
            }
        }
    }

    fun isEnabledButton(state: UpdateUserState): Boolean {

        val emailPattern = Regex("^\\S+@\\S+\\.\\S+\$")

        val email = state.email != "" && emailPattern.matches(state.email) || state.email == ""

        val password = (state.password.length == 0 && state.password == state.confirmPassword
                || state.password.length >= 8 && state.password == state.confirmPassword)

        return email && password
    }

    fun onResetError() {
        _state.value = _state.value?.copy(
            isError = null
        )
    }
}