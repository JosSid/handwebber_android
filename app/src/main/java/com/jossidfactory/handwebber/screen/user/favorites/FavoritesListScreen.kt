package com.jossidfactory.handwebber.screen.user.favorites

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.jossidfactory.handwebber.R
import com.jossidfactory.handwebber.common.ui.components.SuccesView
import com.jossidfactory.handwebber.common.ui.components.error.ErrorView
import com.jossidfactory.handwebber.common.ui.components.searchBar.SearchBarApp
import com.jossidfactory.handwebber.screen.advertisement.list.AdvertisementList
import org.koin.androidx.compose.koinViewModel

@Composable
fun FavoritesListScreen(
    favoritesListViewModel: FavoritesListViewModel = koinViewModel(),
    paddingValues: PaddingValues,
    onItemClick: (String) -> Unit
) {
    
    val state: FavoritesListState by favoritesListViewModel.state.observeAsState(
        FavoritesListState()
    )

    if(state.isError == null && state.advertisements.isNotEmpty()) {

        SearchBarApp(
            paddingValues = paddingValues,
            query = state.query,
            onQueryChange = {favoritesListViewModel.onQueryChange(it)}
        ) {
            AdvertisementList(
                state.advertisements,
                onItemClick
            )
        }
    } else if(state.isError == null) {
        SuccesView(
            title = stringResource(id = R.string.favorites_title),
            text = stringResource(id = R.string.favorites_text),
            painter = painterResource(id = R.drawable
            .baseline_search_off_24))
    }
    else {
        ErrorView(state.isError!!) {
            favoritesListViewModel.onResetError()
        }
    }

}