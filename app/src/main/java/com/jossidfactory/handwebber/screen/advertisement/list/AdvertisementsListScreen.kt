package com.jossidfactory.handwebber.screen.advertisement.list

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import com.jossidfactory.handwebber.common.ui.components.error.ErrorView
import com.jossidfactory.handwebber.common.ui.components.searchBar.SearchBarApp
import org.koin.androidx.compose.koinViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "SuspiciousIndentation")
@Composable
fun AdvertisementsListScreen(
    advertisementsListViewModel: AdvertisementsListViewModel = koinViewModel(),
    paddingValues: PaddingValues,
    onItemClick: (String) -> Unit
) {

    val state: AdvertisementsListState by advertisementsListViewModel.state.observeAsState(
        AdvertisementsListState()
    )

        if(state.isError == null) {

            SearchBarApp(
                paddingValues = paddingValues,
                query = state.query,
                onQueryChange = {advertisementsListViewModel.onQueryChange(it)}
            ) {
                AdvertisementList(
                    state.advertisements,
                    onItemClick
                )
            }
        } else {
            ErrorView(state.isError!!) {
                advertisementsListViewModel.onResetError()
            }
        }
    }







