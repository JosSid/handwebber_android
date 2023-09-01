package com.jossidfactory.handwebber.domain.user.usecase

import com.jossidfactory.handwebber.data.user.UserRepository
import com.jossidfactory.handwebber.data.user.remote.dto.LoginUserDto

class LoginUserUseCase(
    private val  userRepository: UserRepository
) {
    suspend fun invoke(body: LoginUserDto) = userRepository.postLoginUser(body)

}