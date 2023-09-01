package com.jossidfactory.handwebber.di

import android.content.Context
import androidx.room.Room
import com.jossidfactory.handwebber.BuildConfig
import com.jossidfactory.handwebber.data.HandwebberDataBase
import com.jossidfactory.handwebber.data.HeaderInterceptor
import com.jossidfactory.handwebber.data.advertisement.AdvertisementDataService
import com.jossidfactory.handwebber.data.advertisement.AdvertisementRepository
import com.jossidfactory.handwebber.data.advertisement.AdvertisementRepositoryImpl
import com.jossidfactory.handwebber.data.user.UserRepository
import com.jossidfactory.handwebber.data.user.UserRepositoryImpl
import com.jossidfactory.handwebber.data.user.local.AuthRepository
import com.jossidfactory.handwebber.data.user.local.AuthRepositoryImpl
import com.jossidfactory.handwebber.data.user.local.UserDao
import com.jossidfactory.handwebber.data.user.remote.UserDataService
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

val DataModule = module {

    single <AuthRepository> {
        AuthRepositoryImpl(get())
    }
    single {
        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor(HttpLoggingInterceptor.Logger.DEFAULT).apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
            .addInterceptor(HeaderInterceptor(get()))
            .build()
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

    single<UserDataService> {
        getUserData(get())
    }

    single<UserRepository> {
        UserRepositoryImpl(get(), get())
    }

    single {
        getDatabase(get())
    }

    single {
        providesUserDao(get())
    }

    single<AuthRepository> {
        AuthRepositoryImpl(get())
    }
}

private fun getAdvertisementData(retrofit: Retrofit) =
    retrofit.create(AdvertisementDataService::class.java)

private fun getUserData(retrofit: Retrofit) =
    retrofit.create(UserDataService::class.java)

private fun getDatabase(context: Context) : HandwebberDataBase =
    Room.databaseBuilder(
        context,
        HandwebberDataBase::class.java, "handwebber-db"
    ).fallbackToDestructiveMigration().build()

private fun providesUserDao(db: HandwebberDataBase) : UserDao =
    db.userDao()