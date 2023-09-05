package com.jossidfactory.handwebber.data.user

import com.jossidfactory.handwebber.data.user.local.model.UserLoggedEntity
import com.jossidfactory.handwebber.data.user.remote.dto.LoginUserDto
import com.jossidfactory.handwebber.data.user.remote.dto.UserByIdDto
import com.jossidfactory.handwebber.domain.user.model.SignupUserRequestModel

interface UserRepository {

    suspend fun getUserLogged(): UserLoggedEntity?

    suspend fun postLoginUser(body: LoginUserDto)

    suspend fun postTokenTest(): String

    suspend fun getUserById(id: String): UserByIdDto

    suspend fun postSignupUser(body: SignupUserRequestModel)

}