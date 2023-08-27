package com.jossidfactory.handwebber.data.advertisement.dto

import com.squareup.moshi.Json

data class AdvertisementResponseDto(
    @Json(name = "result") val result: AdvertisementDto
)
