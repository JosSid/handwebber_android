package com.jossidfactory.handwebber.screen.layout

import androidx.compose.foundation.layout.Column
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.jossidfactory.handwebber.R
import com.jossidfactory.handwebber.navigation.Screen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBarApp(currentRoute: String?, navController: NavController) {
    TopAppBar(
        title = { Column {
            Text(text = stringResource(R.string.app_name))

            if (currentRoute != null) {
                Text(text = currentRoute)
            }

        }},
        navigationIcon = {
            IconButton(onClick = { /*TODO*/ }) {
                Icon(
                    imageVector = Icons.Default.Menu,
                    contentDescription = "Menu"
                )
            }
        })
}