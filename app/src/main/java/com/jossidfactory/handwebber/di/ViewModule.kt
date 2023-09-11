package com.jossidfactory.handwebber.di

import com.jossidfactory.handwebber.screen.advertisement.detail.AdvertisementDetailViewModel
import com.jossidfactory.handwebber.screen.advertisement.list.AdvertisementsListViewModel
import com.jossidfactory.handwebber.screen.layout.AppViewModel
import com.jossidfactory.handwebber.screen.user.login.LoginViewModel
import com.jossidfactory.handwebber.screen.user.profile.ProfileViewModel
import com.jossidfactory.handwebber.screen.user.signup.SignupViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val ViewModule = module {
    viewModel { AppViewModel(get()) }
    viewModel { AdvertisementsListViewModel(get()) }
    viewModel { AdvertisementDetailViewModel(get()) }
    viewModel { LoginViewModel(get()) }
    viewModel { SignupViewModel(get(), get()) }
    viewModel { ProfileViewModel(get(), get()) }
}