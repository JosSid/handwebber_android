package com.jossidfactory.handwebber.domain.user.usecase

import com.jossidfactory.handwebber.data.user.UserRepository

class GetLoggedUserUseCase(
    private val  userRepository: UserRepository
) {
    suspend fun invoke() = userRepository.getUserLogged()
}