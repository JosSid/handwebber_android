package com.jossidfactory.handwebber.domain.user.usecase

import com.jossidfactory.handwebber.data.user.UserRepository
import com.jossidfactory.handwebber.data.user.remote.dto.SignupUserRequestDto

class SignupUserUseCase(
    private val userRepository: UserRepository
) {
    suspend fun invoke(body: SignupUserRequestDto) = userRepository.postSignupUser(body)
}