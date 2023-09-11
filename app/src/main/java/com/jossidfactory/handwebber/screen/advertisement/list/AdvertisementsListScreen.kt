package com.jossidfactory.handwebber.screen.advertisement.list

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.jossidfactory.handwebber.common.ui.components.button.ButtonBase
import com.jossidfactory.handwebber.common.ui.components.error.ErrorView
import org.koin.androidx.compose.koinViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "SuspiciousIndentation")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AdvertisementsListScreen(
    advertisementsListViewModel: AdvertisementsListViewModel = koinViewModel(),
    paddingValues: PaddingValues,
    onItemClick: (String) -> Unit
) {

    val state: AdvertisementsListState by advertisementsListViewModel.state.observeAsState(
        AdvertisementsListState()
    )

    val ctx = LocalContext.current

        if(state.isError == null) {

            SearchBar(
                modifier = Modifier.padding(paddingValues),
                query = state.query,
                onQueryChange = { advertisementsListViewModel.onQueryChange(it) },
                onSearch = {
                    Toast.makeText(ctx, state.query, Toast.LENGTH_SHORT).show()
                },
                active = true,
                onActiveChange = {},
                placeholder = {
                    Text(text = "Search")
                },
                leadingIcon = {
                    Icon(imageVector = Icons.Default.Search, contentDescription = null)
                },
                trailingIcon = {
                    ButtonBase(
                        text = "Clear",
                        modifier = Modifier.padding(end = 10.dp),
                        ) { advertisementsListViewModel.onQueryChange("")
                    }
                }
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







