package com.jossidfactory.handwebber.domain.advertisement.usecase

import com.jossidfactory.handwebber.data.advertisement.AdvertisementRepository
import com.jossidfactory.handwebber.domain.advertisement.model.AdvertisementResponseModel

class GetAdvertisementByIdUseCase(
    private val advertisementRepository: AdvertisementRepository
) {
    suspend operator fun invoke(id: String): AdvertisementResponseModel = advertisementRepository
        .getAdvertisementById(id)
}
