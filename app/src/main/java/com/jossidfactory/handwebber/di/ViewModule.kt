package com.jossidfactory.handwebber.di

import com.jossidfactory.handwebber.screen.advertisement.list.AdvertisementsListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val ViewModule = module {
    viewModel { AdvertisementsListViewModel(get()) }
}