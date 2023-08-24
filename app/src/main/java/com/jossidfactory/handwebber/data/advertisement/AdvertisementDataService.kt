package com.jossidfactory.handwebber.data.advertisement

import com.jossidfactory.handwebber.data.advertisement.dto.AdvertisementsResponseDto
import retrofit2.http.GET

interface AdvertisementDataService {

    @GET("advertisement")
    suspend fun getAdvertisements(): AdvertisementsResponseDto
}