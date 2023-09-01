package com.jossidfactory.handwebber.data.user

import com.jossidfactory.handwebber.data.user.local.model.UserLoggedEntity
import com.jossidfactory.handwebber.data.user.remote.dto.LoginUserDto

interface UserRepository {

    suspend fun getUserLogged(): List<UserLoggedEntity>?

    suspend fun postLoginUser(body: LoginUserDto): String

    suspend fun postTokenTest(token: String): String

}