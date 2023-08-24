package com.jossidfactory.handwebber.data.advertisement

import com.jossidfactory.handwebber.data.advertisement.mappers.toAdvertisementsResponseModel
import com.jossidfactory.handwebber.domain.advertisement.model.AdvertisementsResponseModel

class AdvertisementRepositoryImpl(
    private val advertisementDataService: AdvertisementDataService
): AdvertisementRepository {
    override suspend fun getAdvertisements(): AdvertisementsResponseModel {
        return advertisementDataService.getAdvertisements().toAdvertisementsResponseModel()
    }
}