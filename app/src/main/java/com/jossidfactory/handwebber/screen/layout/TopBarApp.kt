package com.jossidfactory.handwebber.screen.layout

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.jossidfactory.handwebber.R
import com.jossidfactory.handwebber.navigation.Screen
import kotlinx.coroutines.Job

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBarApp(
    isLogged: Boolean,
    currentRoute: String?,
    navController: NavController,
    onClickMenu: () -> Job,
    onLogOut: () -> Unit) {

    val titleScreen = currentRoute?.split("_")?.first()?.capitalize() ?: ""

    TopAppBar(
        title = {
            Column {
                Text(text = stringResource(R.string.app_name))

                Text(text = titleScreen)

            }
        },
        navigationIcon = {
            if(titleScreen != "Advertisements") {
                IconButton(onClick = {navController.navigate(Screen.AdvertisementsListScreen.route)}) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Back"
                    )
                }
            } else {
                IconButton(onClick = { onClickMenu() }) {
                    Icon(
                        imageVector = Icons.Default.Menu,
                        contentDescription = "Menu"
                    )
                }
            }
        },
        actions = { Text(text = if(isLogged)"Logout" else "Login",
            modifier = Modifier
            .clickable { if(isLogged) onLogOut() else navController.navigate(Screen.LoginScreen
                .route) }
            .padding(end = 15.dp))}
    )

}