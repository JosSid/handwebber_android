package com.jossidfactory.handwebber.screen.advertisement.list

import com.jossidfactory.handwebber.domain.advertisement.model.AdvertisementModel

data class AdvertisementsListState(
    val advertisements: List<AdvertisementModel> = listOf()
)
