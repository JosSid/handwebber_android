package com.jossidfactory.handwebber.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.jossidfactory.handwebber.screen.advertisement.detail.AdvertisementDetailScreen
import com.jossidfactory.handwebber.screen.advertisement.list.AdvertisementsListScreen
import com.jossidfactory.handwebber.screen.user.login.LoginScreen
import com.jossidfactory.handwebber.screen.user.profile.ProfileScreen

fun NavGraphBuilder.addAdvertisementsListScreen(navController: NavController, paddingValues:
PaddingValues){
    composable(Screen.AdvertisementsListScreen.route){
        AdvertisementsListScreen(paddingValues = paddingValues){ id ->
            navController.navigate("advertisement_detail_screen/$id")
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
    composable(Screen.AdvertisementDetailScreen.route) { backStackEntry ->
        val arguments = backStackEntry.arguments
        val id = arguments?.getString("id")

        if (id != null) {
            AdvertisementDetailScreen(paddingValues = paddingValues, id = id)

        }

    }
}

fun NavGraphBuilder.addLoginScreen(
    navController: NavController,
    paddingValues: PaddingValues,
    cb: () -> Unit
) {
    composable(Screen.LoginScreen.route) {
        LoginScreen(paddingValues = paddingValues) {
            cb()

        }
    }
}

fun NavGraphBuilder.addProfileScreen(
    navController: NavController,
    paddingValues: PaddingValues,
    onLogOut: () -> Unit
) {
    composable(Screen.ProfileScreen.route) {
        ProfileScreen(navController = navController, paddingValues = paddingValues, onLogOut =
        onLogOut)
    }
}