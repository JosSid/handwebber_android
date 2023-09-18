package com.jossidfactory.handwebber.screen.user.favorites

import com.jossidfactory.handwebber.common.domain.entity.Error
import com.jossidfactory.handwebber.domain.advertisement.model.AdvertisementModel
import com.jossidfactory.handwebber.domain.user.model.UserLoggedModel

data class FavoritesListState(
    val user: UserLoggedModel? = null,
    val advertisements: List<AdvertisementModel> = listOf(),
    val query: String = "",
    val isError: Error? = null
)
