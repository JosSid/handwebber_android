package com.jossidfactory.handwebber.data.user.remote

import com.jossidfactory.handwebber.data.user.remote.dto.LoginUserDto
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

interface UserDataService {

    @POST("users/login")
    suspend fun postLoginUser(@Body body: LoginUserDto): String

    @POST("users/tokenTest")
    suspend fun postTokenTest(@Header("Authorization") token: String): String

}