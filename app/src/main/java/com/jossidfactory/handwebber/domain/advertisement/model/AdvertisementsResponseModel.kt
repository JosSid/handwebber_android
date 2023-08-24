package com.jossidfactory.handwebber.domain.advertisement.model

import com.jossidfactory.handwebber.data.advertisement.dto.Meta

data class AdvertisementsResponseModel(
    val result: List<AdvertisementModel>,
    val meta: Meta
)
