package com.jossidfactory.handwebber.domain.user.usecase

import com.jossidfactory.handwebber.data.user.UserRepository
import com.jossidfactory.handwebber.domain.user.model.UpdateUserRequestModel

class UpdateUserUseCase(
    private val  userRepository: UserRepository
) {
    suspend fun invoke(id: String, body: UpdateUserRequestModel) = userRepository.updateUser(id, body)
}