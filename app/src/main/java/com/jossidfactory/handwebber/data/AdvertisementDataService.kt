package com.jossidfactory.handwebber.data

import com.jossidfactory.handwebber.data.dto.AdvertisementDto
import com.jossidfactory.handwebber.data.dto.AdvertisementsResponseDto
import retrofit2.http.GET

interface AdvertisementDataService {

    @GET("advertisement")
    suspend fun getAdvertisements(): AdvertisementsResponseDto
}