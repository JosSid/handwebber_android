package com.jossidfactory.handwebber.domain.user.usecase

import com.jossidfactory.handwebber.data.user.UserRepository
import com.jossidfactory.handwebber.domain.user.model.SignupUserRequestModel

class SignupUserUseCase(
    private val userRepository: UserRepository
) {
    suspend fun invoke(body: SignupUserRequestModel) = userRepository.postSignupUser(body)
}