package com.jossidfactory.handwebber.screen.layout

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jossidfactory.handwebber.data.user.local.AuthRepository
import kotlinx.coroutines.launch
import timber.log.Timber

class AppViewModel(
    private val authRepository: AuthRepository
): ViewModel() {
    private val appState = AppState()

    private val _state = MutableLiveData<AppState>()
    val state: LiveData<AppState> = _state

    init {
        getAppState()
    }

    private fun getAppState() {
        _state.value = appState
        viewModelScope.launch {
            try {
                val token = authRepository.getToken()
                val isLogged = !token.isNullOrEmpty()
                _state.value = _state.value?.copy(
                    isLogged = isLogged
                )
            } catch (e: Throwable) {
                e.message?.let { Timber.d(it) }
            }
        }

    }

    fun logOutState() {
        viewModelScope.launch {
            try {
                authRepository.setToken("")
                _state.value = _state.value?.copy(
                    isLogged = false
                )
            }catch (e: Throwable) {
                e.message?.let { Log.d("ERROR MESSAGE", it) }
            }
        }
    }

    fun logInState(cb: () -> Unit) {
        _state.value = _state.value?.copy(
            isLogged = true
        )
        cb()
    }

}