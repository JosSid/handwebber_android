package com.jossidfactory.handwebber.domain.advertisement.usecase

import com.jossidfactory.handwebber.data.advertisement.AdvertisementRepository
import com.jossidfactory.handwebber.domain.advertisement.model.AdvertisementsResponseModel

class GetAdvertisementsListUseCase(
    private val advertisementRepository: AdvertisementRepository
) {
    suspend fun invoke(): AdvertisementsResponseModel = advertisementRepository.getAdvertisements()
}