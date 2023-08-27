package com.jossidfactory.handwebber.data.advertisement.mappers

import com.jossidfactory.handwebber.data.advertisement.dto.AdvertisementResponseDto
import com.jossidfactory.handwebber.domain.advertisement.model.AdvertisementResponseModel

fun AdvertisementResponseDto.toAdvertisementResponseModel() = AdvertisementResponseModel(
    result = result.toAdvertisementModel()
)