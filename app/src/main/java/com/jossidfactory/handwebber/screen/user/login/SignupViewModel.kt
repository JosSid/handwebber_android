package com.jossidfactory.handwebber.screen.user.login

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class SignupViewModel() : ViewModel() {
    private val signupState = SignupState()

    private val _state = MutableLiveData<SignupState>()
    val state: LiveData<SignupState> = _state

    init {
        _state.value = signupState
    }

    fun onFieldChange(value: String, type: String) {
        when (type) {
            "Username" -> {
                _state.value = _state.value?.copy(
                    username = value
                )
            }

            "Email" -> {
                _state.value = _state.value?.copy(
                    email = value
                )
            }

            "Password" -> {
                _state.value = _state.value?.copy(
                    password = value
                )
            }

            "Confirm password" -> {
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
        //val body = LoginUserDto(email, password)
        viewModelScope.launch {
            try {
                Log.d("SIGNUP", state.toString())
                //loginUserUseCase.invoke(body)
                //cb()
            } catch (e: Throwable) {
                e.message?.let { Log.d("DATA", it) }
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
}