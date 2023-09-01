package com.jossidfactory.handwebber.screen.layout

import androidx.compose.foundation.background
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.jossidfactory.handwebber.navigation.NavigationGraph

@Composable
fun LayoutApp() {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    Scaffold(
        modifier = Modifier.background(MaterialTheme.colorScheme.background),
        topBar = { TopBarApp(currentRoute, navController) }
    ) { paddingValues ->
        NavigationGraph(navController = navController, paddingValues = paddingValues)
    }
}