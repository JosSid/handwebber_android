package com.jossidfactory.handwebber.navigation

enum class Screen(val route: String) {
        AdvertisementsListScreen("advertisements_list_screen"),
        AdvertisementDetailScreen("advertisement_detail_screen/{id}"),
        LoginScreen("login_screen"),
        ProfileScreen("profile_screen"),
        UpdateUserScreen("update_user_screen"),
        FavoritsListScreen("favorites_list_screen")
}

data class NavigationItemModel(
        val name: String,
        val route: Screen
)



