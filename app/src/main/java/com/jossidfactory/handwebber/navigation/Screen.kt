package com.jossidfactory.handwebber.navigation

enum class Screen(val route: String) {
        AdvertisementsListScreen("advertisements_list_screen"),
        AdvertisementDetailScreen("advertisement_detail_screen/{id}")
}