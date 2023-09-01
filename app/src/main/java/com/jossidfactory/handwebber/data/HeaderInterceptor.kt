package com.jossidfactory.handwebber.data

import android.util.Log
import com.jossidfactory.handwebber.data.user.local.AuthRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Response

class HeaderInterceptor(
    private val authRepository: AuthRepository
): Interceptor {

    private var token: String? = null
        get() {
            field = if(field == null) {
                runBlocking(Dispatchers.IO) {
                    authRepository.getToken()
                }

            }else {
                field
            }
            Log.d("FIELDTOKEN", field!!)
            return field
        }


    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
            .newBuilder()
        token?.let {
            request.addHeader("Authorization", "Bearer $it")
        }

        return chain.proceed(request.build())
    }
}