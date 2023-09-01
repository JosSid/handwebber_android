package com.jossidfactory.handwebber.data.user.local

interface AuthRepository {
    suspend fun getToken(): String?

    suspend fun setToken(token: String)
}