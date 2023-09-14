package com.jossidfactory.handwebber.screen.advertisement.detail

import com.jossidfactory.handwebber.common.domain.entity.Error
import com.jossidfactory.handwebber.domain.advertisement.model.AdvertisementModel

data class AdvertisementDetailState(
    val advertisement: AdvertisementModel? = null,
    val isError: Error? = null,
    val isFavorite: Boolean = false
)
