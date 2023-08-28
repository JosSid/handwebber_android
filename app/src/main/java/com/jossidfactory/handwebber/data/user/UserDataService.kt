package com.jossidfactory.handwebber.data.user

import com.jossidfactory.handwebber.data.user.dto.LoginUserDto
import retrofit2.http.Body
import retrofit2.http.POST

interface UserDataService {

    @POST("users/login")
    suspend fun postLoginUser(@Body body: LoginUserDto): String

}