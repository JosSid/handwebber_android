package com.jossidfactory.handwebber.screen.advertisement.list

import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import com.jossidfactory.handwebber.domain.advertisement.model.AdvertisementModel

@Composable
fun AdvertisementList(advertisements: List<AdvertisementModel>, onItemClick: (String) -> Unit) {
    LazyVerticalGrid(
        columns = GridCells.Adaptive(150.dp),
       ) {
        items(advertisements) {item ->
                AdvertisementItem(item, onItemClick)
        }
    }
}