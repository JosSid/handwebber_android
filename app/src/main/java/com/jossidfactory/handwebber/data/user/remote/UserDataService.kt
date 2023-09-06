package com.jossidfactory.handwebber.data.user.remote

import com.jossidfactory.handwebber.data.user.remote.dto.LoginUserDto
import com.jossidfactory.handwebber.data.user.remote.dto.SignupUserResponseDto
import com.jossidfactory.handwebber.data.user.remote.dto.UserByIdResponseDto
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Path

interface UserDataService {
    @GET("users/{id}")
    suspend fun getUserById(@Path("id") id: String): UserByIdResponseDto

    @POST("users/login")
    suspend fun postLoginUser(@Body body: LoginUserDto): String

    @POST("users/tokenTest")
    suspend fun postTokenTest(): String

    @Multipart
    @POST("users/signup")
    suspend fun postSignupUser(
        @Part("username") username: RequestBody,
        @Part("mail") email: RequestBody,
        @Part("password") password: RequestBody,
        @Part image: MultipartBody.Part?
    ): SignupUserResponseDto

}
