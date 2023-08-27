package com.jossidfactory.handwebber.data.advertisement

import com.jossidfactory.handwebber.domain.advertisement.model.AdvertisementResponseModel
import com.jossidfactory.handwebber.domain.advertisement.model.AdvertisementsResponseModel

interface AdvertisementRepository {
    suspend fun getAdvertisements(): AdvertisementsResponseModel

    suspend fun getAdvertisementById(id: String): AdvertisementResponseModel
}