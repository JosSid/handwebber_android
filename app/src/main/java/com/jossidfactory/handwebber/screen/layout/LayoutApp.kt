package com.jossidfactory.handwebber.screen.layout

import androidx.compose.foundation.background
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.jossidfactory.handwebber.navigation.NavigationGraph
import org.koin.androidx.compose.koinViewModel

@Composable
fun LayoutApp(
    appViewModel: AppViewModel = koinViewModel()
) {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    val state: AppState by appViewModel.state.observeAsState(
        AppState()
    )


    Scaffold(
        modifier = Modifier.background(MaterialTheme.colorScheme.background),
        topBar = { TopBarApp(state.isLogged, currentRoute, navController) {
            appViewModel.logOutState()
        } }
    ) { paddingValues ->
        NavigationGraph(navController = navController,
            paddingValues = paddingValues
        ) {
            appViewModel.logInState {
                navController
                    .popBackStack()
            }
        }
    }
}