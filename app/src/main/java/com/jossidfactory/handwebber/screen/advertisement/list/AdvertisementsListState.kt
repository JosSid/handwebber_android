package com.jossidfactory.handwebber.screen.advertisement.list

import com.jossidfactory.handwebber.common.domain.entity.Error
import com.jossidfactory.handwebber.domain.advertisement.model.AdvertisementModel

data class AdvertisementsListState(
    val advertisements: List<AdvertisementModel> = listOf(),
    val query: String = "",
    val isError: Error? = null
)
