package com.jossidfactory.handwebber.data.user

import com.jossidfactory.handwebber.data.user.local.AuthRepository
import com.jossidfactory.handwebber.data.user.local.UserDao
import com.jossidfactory.handwebber.data.user.local.model.UserLoggedEntity
import com.jossidfactory.handwebber.data.user.mappers.toSignupUserRequestDto
import com.jossidfactory.handwebber.data.user.mappers.toUserLoggedModel
import com.jossidfactory.handwebber.data.user.remote.UserDataService
import com.jossidfactory.handwebber.data.user.remote.dto.LoginUserDto
import com.jossidfactory.handwebber.data.user.remote.dto.UserDto
import com.jossidfactory.handwebber.domain.user.model.SignupUserRequestModel
import com.jossidfactory.handwebber.domain.user.model.UserLoggedModel
import timber.log.Timber

class UserRepositoryImpl(
    private val userDataService: UserDataService,
    private val userDao: UserDao,
    private val authRepository: AuthRepository
): UserRepository {
    override suspend fun getUserLogged(): UserLoggedModel? {
        return userDao.getUserLogged()?.toUserLoggedModel()
    }

    override suspend fun postLoginUser(body: LoginUserDto) {
        val token = userDataService.postLoginUser(body)

        deleteLocalUserLogged(token)

        val id = postTokenTest()

        val email = body.email

        val user = getUserById(id)

        val userEntity = UserLoggedEntity(id,user.username,email,user.image ?: "",
            user.subscriptions.toString())

        userDao.insertUserLogged(userEntity)
    }

    override suspend fun postTokenTest(): String {
        return userDataService.postTokenTest()
    }

    override suspend fun getUserById(id: String): UserDto {
        val user = userDataService.getUserById(id)
        return user.result
    }

    override suspend fun postSignupUser(body: SignupUserRequestModel) {

        val bodyRequest = body.toSignupUserRequestDto()

        val result = userDataService.postSignupUser(
            bodyRequest.username,
            bodyRequest.email,
            bodyRequest.password,
            bodyRequest.image)
        Timber.d(result.toString())
    }

    override suspend fun deleteUser(id: String): UserDto {
        val user = userDataService.deleteUser(id)

        deleteLocalUserLogged("")

        return user.result
    }

    private suspend fun deleteLocalUserLogged(token: String) {
        authRepository.setToken(token)

        val userInDB = userDao.getUserLogged()

        if(userInDB != null) {
            userDao.deleteUserLogged(userInDB)
        }
    }


}