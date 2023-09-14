package com.jossidfactory.handwebber.screen.advertisement.detail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.jossidfactory.handwebber.domain.advertisement.model.AdvertisementModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AdvertisementDetailItem(
    advertisement: AdvertisementModel,
    paddingValues: PaddingValues,
    onHandleFavorite: () -> Unit
) {
    ElevatedCard(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
            .padding(16.dp),
        elevation = CardDefaults.cardElevation(10.dp),
    ) {
        Column {
            Text(
                text = advertisement.name,
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.titleLarge,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.padding(10.dp)
            )
            Box(
                modifier = Modifier
                    .height(350.dp)
                    .fillMaxWidth()
                    .padding(5.dp)
            ) {
                AsyncImage(
                    model = advertisement.image,
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxSize()
                        .clip(RoundedCornerShape(10.dp)),
                    contentScale = ContentScale.FillBounds,

                )
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .defaultMinSize(100.dp, 200.dp)
                    .padding(10.dp),
                verticalArrangement = Arrangement.SpaceEvenly
            ) {

                Text(text = advertisement.description)
                Text(text = "Price: ${advertisement.price} €")
            }
            Box(modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
                contentAlignment = Alignment.TopEnd
            ) {
                IconButton(onClick = {onHandleFavorite()}) {
                    Icon(
                        imageVector = Icons.Default.FavoriteBorder,
                        contentDescription = null,
                        tint = Color.Black
                    )
                }
            }

        }
    }
}