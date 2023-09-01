package com.jossidfactory.handwebber.screen.user.login

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jossidfactory.handwebber.data.user.local.AuthRepository
import com.jossidfactory.handwebber.data.user.remote.dto.LoginUserDto
import com.jossidfactory.handwebber.domain.user.usecase.GetLoggedUserUseCase
import com.jossidfactory.handwebber.domain.user.usecase.LoginUserUseCase
import kotlinx.coroutines.launch

class LoginViewModel(
    private val loginUserUseCase: LoginUserUseCase,
    private val getLoggedUserUseCase: GetLoggedUserUseCase,
    private val authRepository: AuthRepository
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

    fun onLoginClick(email: String, password: String) {
        val body = LoginUserDto(email, password)
        viewModelScope.launch {
            try {
                val token = loginUserUseCase.invoke(body)
                authRepository.setToken(token)

            }catch (e: Throwable) {
                e.message?.let { Log.d("DATA", it) }
            }

        }


    }
}