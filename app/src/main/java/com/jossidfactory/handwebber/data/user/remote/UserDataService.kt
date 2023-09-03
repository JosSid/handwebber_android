package com.jossidfactory.handwebber.data.user.remote

import com.jossidfactory.handwebber.data.user.remote.dto.LoginUserDto
import com.jossidfactory.handwebber.data.user.remote.dto.UserByIdResponseDto
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface UserDataService {
    @GET("users/{id}")
    suspend fun getUserById(@Path("id") id: String): UserByIdResponseDto

    @POST("users/login")
    suspend fun postLoginUser(@Body body: LoginUserDto): String

    @POST("users/tokenTest")
    suspend fun postTokenTest(): String

}