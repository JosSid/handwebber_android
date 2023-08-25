package com.jossidfactory.handwebber.screen.advertisement.list

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.jossidfactory.handwebber.BuildConfig
import com.jossidfactory.handwebber.domain.advertisement.model.AdvertisementModel

@Composable
fun AdvertisementItem(advertisement: AdvertisementModel) {
    val inputImageUrl = advertisement.image
    val outputImageUrl = inputImageUrl.removePrefix("/")
    Column {
        Box(
            modifier = Modifier
                .height(200.dp)
                .fillMaxWidth()
                .background(color = Color.Red)
        ) {
            AsyncImage(
                model = BuildConfig.SERVER_URL + outputImageUrl,
                contentDescription = null,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.FillBounds)
        }
        Column(
            modifier = Modifier
                .height(80.dp)
                .fillMaxWidth()
                .padding(10.dp),
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            Text(text = advertisement.name,
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.titleLarge)

            Text(text = "Price: ${advertisement.price} €")
        }
    }
}