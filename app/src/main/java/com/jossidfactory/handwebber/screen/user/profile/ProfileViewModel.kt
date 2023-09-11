package com.jossidfactory.handwebber.screen.user.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jossidfactory.handwebber.common.domain.mappers.logError
import com.jossidfactory.handwebber.common.domain.mappers.toError
import com.jossidfactory.handwebber.domain.user.usecase.DeleteUserUseCase
import com.jossidfactory.handwebber.domain.user.usecase.GetLoggedUserUseCase
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class ProfileViewModel(
    private val getLoggedUserUseCase: GetLoggedUserUseCase,
    private val deleteUserUseCase: DeleteUserUseCase
): ViewModel() {
    private val profileState = ProfileState()

    private val _state = MutableLiveData<ProfileState>()
    val state: LiveData<ProfileState> = _state

    init {
        getLoggedUser()
    }

    fun getLoggedUser() {
        _state.value = profileState
        viewModelScope.launch {
            try {
                val userLogged = getLoggedUserUseCase.invoke()
                if (userLogged != null) {
                    _state.value = _state.value?.copy(
                        id = userLogged.id,
                        username = userLogged.username,
                        email = userLogged.email,
                        image = userLogged.image,
                        subscriptions = userLogged.subscriptions
                    )
                }
            } catch (e: Throwable) {
                _state.value = _state.value?.copy(
                    isError = e.toError(),
                    view = ProfileViewState.IsError
                )
                e.logError()
            }
        }
    }

    fun deleteUser(id: String, onLogOut: () -> Unit) {
        viewModelScope.launch {
            try {
                deleteUserUseCase.invoke(id)
                _state.value = _state.value?.copy(
                    view = ProfileViewState.IsDeleted
                )
                delay(3000)
                onLogOut()
                _state.value = _state.value?.copy(
                    id = "",
                    username = "",
                    email = "",
                    image = "",
                    subscriptions = emptyList()
                )
            } catch (e: Throwable) {
                _state.value = _state.value?.copy(
                    isError = e.toError(),
                    view = ProfileViewState.IsError
                )
                e.logError()
            }
        }
    }

    fun handleView(value: ProfileViewState) {
        _state.value = _state.value?.copy(
            view = value
        )
    }

    fun onResetError() {
        _state.value = _state.value?.copy(
            isError = null,
            view = ProfileViewState.IsProfile
        )
    }
}