package com.jossidfactory.handwebber.data.advertisement

import com.jossidfactory.handwebber.data.advertisement.dto.AdvertisementResponseDto
import com.jossidfactory.handwebber.data.advertisement.dto.AdvertisementsResponseDto
import retrofit2.http.GET
import retrofit2.http.Path

interface AdvertisementDataService {

    @GET("advertisement")
    suspend fun getAdvertisements(): AdvertisementsResponseDto

    @GET("advertisement/{id}")
    suspend fun getAdvertisementById(@Path("id") id: String): AdvertisementResponseDto
}