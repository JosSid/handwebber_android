package com.jossidfactory.handwebber.data.user

import com.jossidfactory.handwebber.data.user.dto.LoginUserDto

class UserRepositoryImpl(
    private val userDataService: UserDataService
): UserRepository {

    override suspend fun postLoginUser(body: LoginUserDto): String {
        return userDataService.postLoginUser(body)
    }
}