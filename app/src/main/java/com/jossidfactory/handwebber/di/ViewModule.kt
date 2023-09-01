package com.jossidfactory.handwebber.di

import com.jossidfactory.handwebber.screen.advertisement.detail.AdvertisementDetailViewModel
import com.jossidfactory.handwebber.screen.advertisement.list.AdvertisementsListViewModel
import com.jossidfactory.handwebber.screen.user.login.LoginViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val ViewModule = module {
    viewModel { AdvertisementsListViewModel(get()) }
    viewModel { AdvertisementDetailViewModel(get()) }
    viewModel { LoginViewModel(get(),get(), get()) }
}