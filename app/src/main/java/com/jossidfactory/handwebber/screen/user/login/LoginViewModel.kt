package com.jossidfactory.handwebber.screen.user.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jossidfactory.handwebber.common.domain.mappers.logError
import com.jossidfactory.handwebber.common.domain.mappers.toError
import com.jossidfactory.handwebber.data.user.remote.dto.LoginUserDto
import com.jossidfactory.handwebber.domain.user.usecase.LoginUserUseCase
import kotlinx.coroutines.launch

class LoginViewModel(
    private val loginUserUseCase: LoginUserUseCase
): ViewModel() {
    private val loginState = LoginState()

    private val _state = MutableLiveData<LoginState>()
    val state: LiveData<LoginState> = _state

    init {
        _state.value = loginState
    }

    fun onEmailChange(email: String) {
        _state.value = _state.value?.copy(
            email = email
        )
    }

    fun onPasswordChange(password: String) {
        _state.value = _state.value?.copy(
            password = password
        )
    }

    fun onLoginClick(email: String, password: String, cb: () -> Unit) {
        val body = LoginUserDto(email, password)
        viewModelScope.launch {
            try {
                loginUserUseCase.invoke(body)
                cb()
            }catch (e: Throwable) {
                _state.value = _state.value?.copy(
                    isError = e.toError()
                )
                e.logError()
            }
        }
    }

    fun isEnabledButton(email: String, password: String): Boolean {
        val emailPattern = Regex("^\\S+@\\S+\\.\\S+\$")

        return emailPattern.matches(email) && password.length >= 4
    }

    fun onResetError() {
        _state.value = _state.value?.copy(
            isError = null
        )
    }
}