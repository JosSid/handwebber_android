package com.jossidfactory.handwebber.screen.advertisement.list

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import com.jossidfactory.handwebber.screen.layout.TopBarApp
import org.koin.androidx.compose.koinViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AdvertisementsListScreen(
    advertisementsListViewModel: AdvertisementsListViewModel = koinViewModel()
) {

    val state: AdvertisementsListState by advertisementsListViewModel.state.observeAsState(
        AdvertisementsListState()
    )

    val ctx = LocalContext.current

    Scaffold(
        modifier = Modifier.background(MaterialTheme.colorScheme.background),
        topBar = { TopBarApp() }
    ) { paddingValues ->
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
                Button(onClick = { advertisementsListViewModel.onQueryChange("") }) {
                    Text(text = "Clear")
                }
            }
        ) {
            AdvertisementList(
                state.advertisements,
            )
        }
    }


}




