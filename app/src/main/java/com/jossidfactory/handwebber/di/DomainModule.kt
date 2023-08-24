package com.jossidfactory.handwebber.di

import com.jossidfactory.handwebber.domain.advertisement.usecase.GetAdvertisementsListUseCase
import org.koin.dsl.module

val DomainModule = module {
    single { GetAdvertisementsListUseCase(get()) }
}