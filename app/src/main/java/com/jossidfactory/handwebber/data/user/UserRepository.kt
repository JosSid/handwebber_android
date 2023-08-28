package com.jossidfactory.handwebber.data.user

import com.jossidfactory.handwebber.data.user.dto.LoginUserDto

interface UserRepository {

    suspend fun postLoginUser(body: LoginUserDto): String

}