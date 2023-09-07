package com.jossidfactory.handwebber.screen.user.signup

import android.graphics.Bitmap
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jossidfactory.handwebber.common.domain.mappers.logError
import com.jossidfactory.handwebber.common.domain.mappers.toError
import com.jossidfactory.handwebber.data.user.remote.dto.LoginUserDto
import com.jossidfactory.handwebber.domain.user.model.SignupUserRequestModel
import com.jossidfactory.handwebber.domain.user.usecase.LoginUserUseCase
import com.jossidfactory.handwebber.domain.user.usecase.SignupUserUseCase
import kotlinx.coroutines.launch

class SignupViewModel(
    private val signupUserUseCase: SignupUserUseCase,
    private val loginUserUseCase: LoginUserUseCase
) : ViewModel() {
    private val signupState = SignupState()

    private val _state = MutableLiveData<SignupState>()
    val state: LiveData<SignupState> = _state


    init {
        _state.value = signupState
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

    fun onCheckedChange() {
        _state.value = _state.value?.copy(
            checked = !_state.value!!.checked
        )
    }


    fun onLoginClick(state: SignupState, cb: () -> Unit) {

        val requestBody = SignupUserRequestModel(
            state.username,
            state.email,
            state.password,
             state.image
        )

        viewModelScope.launch {
            try {
                signupUserUseCase.invoke(requestBody)
                loginUserUseCase.invoke(LoginUserDto(state.email, state.password))
                cb()
            } catch (e: Throwable) {
                _state.value = _state.value?.copy(
                    isError = e.toError()
                )
                e.logError()
            }
        }
    }

    fun isEnabledButton(state: SignupState): Boolean {
        val emailPattern = Regex("^\\S+@\\S+\\.\\S+\$")

        val (
            username,
            email,
            password,
            confirmPassword,
            _,
            checked
        ) = state

        return emailPattern.matches(email) && password.length >= 8 && password == confirmPassword
                && username.isNotEmpty() && checked
    }

    fun onResetError() {
        _state.value = _state.value?.copy(
            isError = null
        )
    }
}