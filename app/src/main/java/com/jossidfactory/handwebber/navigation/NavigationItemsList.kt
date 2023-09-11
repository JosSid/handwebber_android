package com.jossidfactory.handwebber.navigation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import kotlinx.coroutines.Job

@Composable
fun NavigationItemsList(
    isLogged: Boolean,
    navController: NavController,
    onClickCloseMenu: () -> Job
) {
    val navigationItems = if(isLogged) {
        listOf(
            NavigationItemModel(
            "Profile",
            Screen.ProfileScreen
            ),
            NavigationItemModel(
                "Update profile",
                Screen.UpdateUserScreen
            )
        )
    }else{
        listOf(NavigationItemModel(
            "Login/Signup",
            Screen.LoginScreen
        ))
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .clickable { onClickCloseMenu() },
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {

        navigationItems.forEach {
            Text(
                text = it.name,
                modifier = Modifier.clickable {
                    navController.navigate(it.route.route)
                    onClickCloseMenu()
                },
                fontSize = 24.sp,
                color = MaterialTheme.colorScheme.primary
            )
        }
    }
}