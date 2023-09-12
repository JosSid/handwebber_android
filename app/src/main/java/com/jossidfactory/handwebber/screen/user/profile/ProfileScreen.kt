package com.jossidfactory.handwebber.screen.user.profile

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.jossidfactory.handwebber.R
import com.jossidfactory.handwebber.common.ui.components.SuccesView
import com.jossidfactory.handwebber.common.ui.components.confirm.ConfirmerView
import com.jossidfactory.handwebber.common.ui.components.error.ErrorView
import org.koin.androidx.compose.koinViewModel

@Composable
fun ProfileScreen(
    profileViewModel: ProfileViewModel = koinViewModel(),
    navController: NavController,
    paddingValues: PaddingValues,
    onLogOut: () -> Unit
) {

    val state: ProfileState by profileViewModel.state.observeAsState(ProfileState())

    when (state.view) {
        ProfileViewState.IsProfile -> ProfileView(state = state, navController = navController,
                paddingValues = paddingValues) {
            profileViewModel.handleView(ProfileViewState.IsConfirm)
        }


        ProfileViewState.IsConfirm -> ConfirmerView(text = stringResource(id = R.string.confirm_delete_text),
            onConfirm = {
                profileViewModel.deleteUser(state.id, onLogOut)
            }) {
            profileViewModel.handleView(ProfileViewState.IsProfile)
        }

        ProfileViewState.IsError -> state.isError?.let { ErrorView(it){profileViewModel.onResetError()} }

        ProfileViewState.IsDeleted -> SuccesView(
            title = stringResource(id = R.string.deleted_user_title),
            text = stringResource(id = R.string.deleted_user_text) + " ${state.username}",
            painter = painterResource(id = R.drawable.baseline_heart_broken_24))

    }

}