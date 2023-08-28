package com.jossidfactory.handwebber.screen.user.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LoginViewModel(): ViewModel() {
    val loginState = LoginState()

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
}