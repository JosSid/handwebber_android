package com.jossidfactory.handwebber.data.advertisement

import com.jossidfactory.handwebber.data.advertisement.dto.AdvertisementsResponseDto
import com.jossidfactory.handwebber.domain.advertisement.model.AdvertisementsResponseModel

interface AdvertisementRepository {
    suspend fun getAdvertisements(): AdvertisementsResponseModel
}