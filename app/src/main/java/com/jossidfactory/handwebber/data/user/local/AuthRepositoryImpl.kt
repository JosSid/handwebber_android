package com.jossidfactory.handwebber.data.user.local

import android.content.Context
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext

val Context.dataStore by preferencesDataStore(name = "auth")

class AuthRepositoryImpl(
    private val context: Context
): AuthRepository {

    override suspend fun getToken(): String? {

            return withContext(Dispatchers.IO) {
                context.dataStore.data.map { preferences ->
                    preferences[stringPreferencesKey("token")]
                }.firstOrNull() ?: ""
            }

    }
}