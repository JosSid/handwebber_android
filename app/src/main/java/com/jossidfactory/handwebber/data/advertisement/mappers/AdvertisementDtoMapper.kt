package com.jossidfactory.handwebber.data.advertisement.mappers

import com.jossidfactory.handwebber.data.advertisement.dto.AdvertisementDto
import com.jossidfactory.handwebber.domain.advertisement.model.AdvertisementModel


fun AdvertisementDto.toAdvertisementModel() = AdvertisementModel(
    id = id ?: "",
    userId = userId,
    name = name ?: "",
    image = image ?: "",
    description = description ?: "",
    custom = custom ?: false,
    price = price ?: 0.0,
    stock = stock ?: 1,
    tags = tags,
    subscriptions = subscriptions,
    update = update,
    creation = creation
)
