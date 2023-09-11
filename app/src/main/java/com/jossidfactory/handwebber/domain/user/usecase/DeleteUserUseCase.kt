package com.jossidfactory.handwebber.domain.user.usecase

import com.jossidfactory.handwebber.data.user.UserRepository

class DeleteUserUseCase(
    private val userRepository: UserRepository
) {
    suspend fun invoke(id: String) = userRepository.deleteUser(id)
}