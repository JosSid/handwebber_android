package com.jossidfactory.handwebber.screen.user.login

import android.graphics.Bitmap
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jossidfactory.handwebber.data.user.remote.dto.LoginUserDto
import com.jossidfactory.handwebber.data.user.remote.dto.SignupUserRequestDto
import com.jossidfactory.handwebber.domain.user.usecase.LoginUserUseCase
import com.jossidfactory.handwebber.domain.user.usecase.SignupUserUseCase
import com.jossidfactory.handwebber.utils.toMultipart
import kotlinx.coroutines.launch
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.toRequestBody
import timber.log.Timber

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

        val requestUsername =  state.username.toRequestBody("multipart/form-data".toMediaTypeOrNull())
        val requestEmail =  state.email.toRequestBody("multipart/form-data".toMediaTypeOrNull())
        val requestPassword =  state.password.toRequestBody("multipart/form-data"
            .toMediaTypeOrNull())

        val requestBody = SignupUserRequestDto(
            requestUsername,
            requestEmail,
            requestPassword,
            state.image?.toMultipart()
        )

        viewModelScope.launch {
            try {
                Timber.d(state.toString())
                signupUserUseCase.invoke(requestBody)
                loginUserUseCase.invoke(LoginUserDto(state.email, state.password))
                cb()
            } catch (e: Throwable) {
                Timber.e(e)
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