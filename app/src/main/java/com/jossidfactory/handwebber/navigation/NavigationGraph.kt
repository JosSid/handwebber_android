package com.jossidfactory.handwebber.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost

@Composable
fun NavigationGraph(
    navController: NavController, paddingValues: PaddingValues, cb: () -> Unit
) {
    NavHost(navController = navController as NavHostController,
        startDestination = Screen.AdvertisementsListScreen.route,
        ) {
        addAdvertisementsListScreen(navController, paddingValues)
        addAdvertisementDetailScreen(navController, paddingValues)
        addLoginScreen(navController, paddingValues, cb)
    }
}