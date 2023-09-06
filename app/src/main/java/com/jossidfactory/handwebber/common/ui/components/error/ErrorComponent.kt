package com.jossidfactory.handwebber.common.ui.components.error

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.jossidfactory.handwebber.common.ui.components.button.ButtonBase

@Composable
fun ErrorComponent(
    text: String,
    onClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(50.dp),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally

    ) {
        Text(text)
        ButtonBase(text = "Reload", isEnabled = true) {
            onClick()
        }
    }
}