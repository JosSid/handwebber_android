package com.jossidfactory.handwebber.screen.advertisement.detail

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import com.jossidfactory.handwebber.common.ui.components.error.ErrorView
import org.koin.androidx.compose.koinViewModel

@Composable
fun AdvertisementDetailScreen(
    advertisementDetailViewModel: AdvertisementDetailViewModel = koinViewModel(),
    paddingValues: PaddingValues,
    isLogged: Boolean,
    id: String
) {

    val state: AdvertisementDetailState by advertisementDetailViewModel.state.observeAsState(
        AdvertisementDetailState()
    )
    LaunchedEffect(Unit) {
        advertisementDetailViewModel.getAdvertisementById(id)
    }
    if (state.isError == null) {
        state.advertisement?.let {
            AdvertisementDetailItem(
                it,
                paddingValues,
                isLogged,
                state.isFavorite,
            ) { advertisementDetailViewModel.handleFavoriteAdvertisement(id) }
        }
    } else {
        ErrorView(state.isError!!) {
            advertisementDetailViewModel.onResetError(id)
        }
    }
}