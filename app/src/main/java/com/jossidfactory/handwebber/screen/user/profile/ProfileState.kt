package com.jossidfactory.handwebber.screen.user.profile

import com.jossidfactory.handwebber.common.domain.entity.Error

data class ProfileState(
    val id: String = "",
    val username: String = "",
    val email: String = "",
    val image: String = "",
    val subscriptions: List<String?>? = emptyList(),
    val isError: Error? = null,
    val view: ProfileViewState = ProfileViewState.IsProfile,
)

enum class ProfileViewState {
    IsProfile,
    IsError,
    IsConfirm,
    IsDeleted
}
