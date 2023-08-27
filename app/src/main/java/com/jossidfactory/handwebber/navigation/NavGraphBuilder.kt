package com.jossidfactory.handwebber.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.jossidfactory.handwebber.screen.advertisement.detail.AdvertisementDetailScreen
import com.jossidfactory.handwebber.screen.advertisement.list.AdvertisementsListScreen

fun NavGraphBuilder.addAdvertisementsListScreen(navController: NavController, paddingValues:
PaddingValues){
    composable(Screen.AdvertisementsListScreen.route){
        AdvertisementsListScreen(paddingValues = paddingValues){ id ->
            navController.navigate(Screen.AdvertisementDetailScreen.route)
            {
                popUpTo(Screen.AdvertisementsListScreen.route) {
                    inclusive = true
                }
            }
        }
    }
}

fun NavGraphBuilder.addAdvertisementDetailScreen(navController: NavController,paddingValues:
PaddingValues) {
    composable(Screen.AdvertisementDetailScreen.route) {
        AdvertisementDetailScreen(paddingValues = paddingValues)
    }
}