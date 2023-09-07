package com.jossidfactory.handwebber.data.advertisement.mappers

import com.jossidfactory.handwebber.BuildConfig
import com.jossidfactory.handwebber.data.advertisement.dto.AdvertisementDto
import com.jossidfactory.handwebber.domain.advertisement.model.AdvertisementModel


fun AdvertisementDto.toAdvertisementModel() = AdvertisementModel(
    id = id ?: "",
    userId = userId,
    name = name ?: "",
    image = if(image?.isNotEmpty() == true) (BuildConfig.SERVER_URL + image?.removePrefix("/"))
    else "https://place-hold.it/800x800?text=Empty Image&fontsize=50",
    description = description ?: "",
    custom = custom ?: false,
    price = price ?: 0.0,
    stock = stock ?: 1,
    tags = tags,
    subscriptions = subscriptions,
    update = update,
    creation = creation
)

//fun AdvertisementDto.toAdvertisementEntity() = AdvertisementEntity(
//    id = id,
//    idUser = userId,
//    creation = creation,
//    name = name,
//    custom = custom,
//    description = description,
//    image = image,
//    price = price,
//    tags = tags,
//    stock = stock,
//    subscriptions = subscriptions,
//    update = update
//)
