package com.jossidfactory.handwebber.di

import com.jossidfactory.handwebber.domain.advertisement.usecase.GetAdvertisementByIdUseCase
import com.jossidfactory.handwebber.domain.advertisement.usecase.GetAdvertisementsListUseCase
import com.jossidfactory.handwebber.domain.user.usecase.DeleteUserUseCase
import com.jossidfactory.handwebber.domain.user.usecase.GetLoggedUserUseCase
import com.jossidfactory.handwebber.domain.user.usecase.LoginUserUseCase
import com.jossidfactory.handwebber.domain.user.usecase.SignupUserUseCase
import org.koin.dsl.module

val DomainModule = module {
    single { GetAdvertisementsListUseCase(get()) }

    single { GetAdvertisementByIdUseCase(get()) }

    single { LoginUserUseCase(get()) }

    single { GetLoggedUserUseCase(get())}

    single { SignupUserUseCase(get()) }

    single { DeleteUserUseCase(get()) }
}