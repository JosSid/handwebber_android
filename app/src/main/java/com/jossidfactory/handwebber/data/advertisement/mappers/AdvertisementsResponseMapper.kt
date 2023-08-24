package com.jossidfactory.handwebber.data.advertisement.mappers

import com.jossidfactory.handwebber.data.advertisement.dto.AdvertisementsResponseDto
import com.jossidfactory.handwebber.domain.advertisement.model.AdvertisementsResponseModel

fun AdvertisementsResponseDto.toAdvertisementsResponseModel() = AdvertisementsResponseModel(
    result = result.map { it.toAdvertisementModel() },
    meta = meta
)