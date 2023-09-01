package com.jossidfactory.handwebber.data.user.local

interface AuthRepository {
    suspend fun getToken(): String?
}