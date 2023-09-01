package com.jossidfactory.handwebber.data.user

import android.util.Log
import com.jossidfactory.handwebber.data.user.local.UserDao
import com.jossidfactory.handwebber.data.user.local.model.UserLoggedEntity
import com.jossidfactory.handwebber.data.user.remote.UserDataService
import com.jossidfactory.handwebber.data.user.remote.dto.LoginUserDto

class UserRepositoryImpl(
    private val userDataService: UserDataService,
    private val userDao: UserDao
): UserRepository {
    override fun getUserLogged(): UserLoggedEntity? {
        return userDao.getUserLogged()
    }

    override suspend fun postLoginUser(body: LoginUserDto): String {
        val token = userDataService.postLoginUser(body)
        Log.d("RECEIVEDTOKEN", token)
        val id = postTokenTest()
        Log.d("RECEIVEDID", id)
        val email = body.email


        val user = UserLoggedEntity(id,email,token)

        userDao.insertUserLogged(user)
        return token
    }

    override suspend fun postTokenTest(): String {
        return userDataService.postTokenTest()
    }


}