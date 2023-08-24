package com.jossidfactory.handwebber.di

import com.jossidfactory.handwebber.BuildConfig
import com.jossidfactory.handwebber.data.advertisement.AdvertisementDataService
import com.jossidfactory.handwebber.data.advertisement.AdvertisementRepository
import com.jossidfactory.handwebber.data.advertisement.AdvertisementRepositoryImpl
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

val DataModule = module {
    single {
        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor(HttpLoggingInterceptor.Logger.DEFAULT).apply {
                level = HttpLoggingInterceptor.Level.BODY
            }).build()
    }

    single<Retrofit> {
        val URL = BuildConfig.SERVER_URL
        Retrofit.Builder()
            .baseUrl("${URL}api/")
            .client(get())
            .addConverterFactory(MoshiConverterFactory.create(get()))
            .build()
    }

    single<Moshi> {
        Moshi.Builder()
            .addLast(KotlinJsonAdapterFactory())
            .build()
    }

    single<AdvertisementDataService> {
        getAdvertisementData(get())
    }

    single<AdvertisementRepository> {
        AdvertisementRepositoryImpl(get())
    }
}

private fun getAdvertisementData(retrofit: Retrofit) =
    retrofit.create(AdvertisementDataService::class.java)