package com.jossidfactory.handwebber.data.user

import com.jossidfactory.handwebber.data.user.remote.dto.LoginUserDto
import com.jossidfactory.handwebber.data.user.remote.dto.UserDto
import com.jossidfactory.handwebber.domain.user.model.SignupUserRequestModel
import com.jossidfactory.handwebber.domain.user.model.UpdateUserRequestModel
import com.jossidfactory.handwebber.domain.user.model.UserLoggedModel

interface UserRepository {

    suspend fun getUserLogged(): UserLoggedModel?

    suspend fun postLoginUser(body: LoginUserDto)

    suspend fun postTokenTest(): String

    suspend fun getUserById(id: String): UserDto

    suspend fun postSignupUser(body: SignupUserRequestModel)

    suspend fun updateUser(id: String, body: UpdateUserRequestModel)

    suspend fun deleteUser(id: String): UserDto

}