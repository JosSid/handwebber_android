package com.jossidfactory.handwebber.screen.user.profile

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.jossidfactory.handwebber.R
import com.jossidfactory.handwebber.common.ui.components.button.ButtonBase
import com.jossidfactory.handwebber.navigation.Screen

@Composable
fun ProfileView(
    state: ProfileState,
    navController: NavController,
    paddingValues: PaddingValues,
    onConfirm: () -> Unit
) {
    ElevatedCard(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
            .padding(16.dp),
        elevation = CardDefaults.cardElevation(10.dp),
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            Text(
                text = state.username,
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.titleLarge,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.padding(24.dp)
            )
            Box(
                modifier = Modifier
                    .size(250.dp)
                    .padding(5.dp)
            ) {
                AsyncImage(
                    model = state.image,
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxSize()
                        .clip(CircleShape),
                    contentScale = ContentScale.FillBounds,

                    )
            }
            Text(
                text = state.email,
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.titleLarge,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.padding(24.dp)
            )

            ButtonBase(text = stringResource(R.string.delete_account)) {
                onConfirm()
            }

            ButtonBase(
                text = stringResource(R.string.update_account),
                modifier = Modifier.padding(top = 12.dp)
                ) {
                navController.navigate(Screen.UpdateUserScreen.route)
            }
        }

    }
}