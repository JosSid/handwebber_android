package com.jossidfactory.handwebber.screen.advertisement.list

import com.jossidfactory.handwebber.data.dto.AdvertisementDto

data class AdvertisementsListState(
    val advertisements: List<AdvertisementDto> = listOf()
)
