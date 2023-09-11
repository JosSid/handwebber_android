package com.jossidfactory.handwebber.screen.layout

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.jossidfactory.handwebber.navigation.NavigationGraph
import com.jossidfactory.handwebber.navigation.Screen
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel

@SuppressLint("CoroutineCreationDuringComposition")
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

    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
                        NavigationDrawerApp(
                            state.isLogged,
                            navController = navController,
                            onClickCloseMenu = {
                                scope.launch {
                                    drawerState.close()
                                }
                            }
                        )
        },
        scrimColor = MaterialTheme.colorScheme.primary
    ) {
        Scaffold(
            modifier = Modifier.background(MaterialTheme.colorScheme.background),
            topBar = {
                TopBarApp(
                    state.isLogged,
                    currentRoute,
                    navController,
                    onClickMenu = {scope.launch {
                    drawerState.open()
                }}) {
                    appViewModel.logOutState()
                }
            },

            ) { paddingValues ->

            NavigationGraph(
                navController = navController,
                paddingValues = paddingValues,
                onLogOut = {
                    appViewModel.logOutState()
                    navController.navigate(Screen.AdvertisementsListScreen.route)
                }
            ) {
                appViewModel.logInState {
                    navController
                        .popBackStack()
                }
            }
        }
    }


}