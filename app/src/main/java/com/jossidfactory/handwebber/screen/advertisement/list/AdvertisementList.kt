package com.jossidfactory.handwebber.screen.advertisement.list

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.jossidfactory.handwebber.domain.advertisement.model.AdvertisementModel

@Composable
fun AdvertisementList(advertisements: List<AdvertisementModel>, modifier: Modifier = Modifier) {
    LazyVerticalGrid(
        contentPadding = PaddingValues(2.dp),
        columns = GridCells.Adaptive(150.dp),
        modifier = modifier
       ) {
        items(advertisements) {item ->
                AdvertisementItem(item, Modifier.padding(8.dp))
        }
    }
}