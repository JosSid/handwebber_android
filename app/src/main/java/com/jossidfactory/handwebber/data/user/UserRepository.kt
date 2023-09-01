package com.jossidfactory.handwebber.data.user

import com.jossidfactory.handwebber.data.user.local.model.UserLoggedEntity
import com.jossidfactory.handwebber.data.user.remote.dto.LoginUserDto

interface UserRepository {

    fun getUserLogged(): UserLoggedEntity?

    suspend fun postLoginUser(body: LoginUserDto): String

    suspend fun postTokenTest(): String

}