package com.jossidfactory.handwebber.screen.layout

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.jossidfactory.handwebber.R
import com.jossidfactory.handwebber.navigation.NavigationItemsList
import com.jossidfactory.handwebber.screen.user.profile.ProfileDrawer
import kotlinx.coroutines.Job


@Composable
fun NavigationDrawerApp(
    isLogged: Boolean,
    isChangedProfile: Boolean,
    navController: NavController,
    onClickCloseMenu: () -> Job,
) {
    ModalDrawerSheet(
        modifier = Modifier.fillMaxHeight()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),
            horizontalArrangement = Arrangement.End,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = "CLOSE")
            IconButton(onClick = { onClickCloseMenu() }) {
                Icon(
                    modifier = Modifier.size(80.dp),
                    imageVector = Icons.Default.Close, contentDescription = null)
            }
        }
        Box(
            modifier = Modifier
                .padding(10.dp)
                .clip(RoundedCornerShape(10.dp)),
            contentAlignment = Alignment.Center

        ) {
            Image(painter = painterResource(
                id = R.drawable.handwebber_portada),
                contentDescription = null)
        }
        Divider()
        if(isLogged){
            ProfileDrawer(isLogged = isLogged, isChangedProfile = isChangedProfile)
        }
        Divider()

        NavigationItemsList(
            isLogged = isLogged,
            navController = navController,
            onClickCloseMenu = onClickCloseMenu
        )

    }
}

@Preview()
@Composable
fun ShowNavigationDrawerApp() {
    NavigationDrawerApp(isLogged = true, isChangedProfile = true, navController =
    NavController
        (LocalContext.current)){
        Job()
    }
}