package com.jossidfactory.handwebber.data.advertisement.dto

import com.squareup.moshi.Json

data class AdvertisementsResponseDto(
    @Json(name = "result") val result: List<AdvertisementDto>,
    @Json(name = "meta") val meta: Meta
)

data class Meta(
    val totalNumOfAds: Int,
    val maxPrice: Double
)
