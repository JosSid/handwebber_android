package com.jossidfactory.handwebber.data.user

import com.jossidfactory.handwebber.data.user.local.AuthRepository
import com.jossidfactory.handwebber.data.user.local.UserDao
import com.jossidfactory.handwebber.data.user.local.model.UserLoggedEntity
import com.jossidfactory.handwebber.data.user.mappers.toSignupUserRequestDto
import com.jossidfactory.handwebber.data.user.remote.UserDataService
import com.jossidfactory.handwebber.data.user.remote.dto.LoginUserDto
import com.jossidfactory.handwebber.data.user.remote.dto.UserByIdDto
import com.jossidfactory.handwebber.domain.user.model.SignupUserRequestModel
import timber.log.Timber

class UserRepositoryImpl(
    private val userDataService: UserDataService,
    private val userDao: UserDao,
    private val authRepository: AuthRepository
): UserRepository {
    override suspend fun getUserLogged(): UserLoggedEntity? {
        return userDao.getUserLogged()
    }

    override suspend fun postLoginUser(body: LoginUserDto) {
        val token = userDataService.postLoginUser(body)

        authRepository.setToken(token)

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

    override suspend fun getUserById(id: String): UserByIdDto {
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


}