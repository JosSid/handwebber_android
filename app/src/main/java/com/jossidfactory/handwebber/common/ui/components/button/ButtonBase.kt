package com.jossidfactory.handwebber.common.ui.components.button


import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.sp


@Composable
fun ButtonBase(text: String, isEnabled: Boolean, onClick: () -> Unit) {
    Button(onClick = { onClick() }, enabled =  isEnabled ) {
        Text(
            text = text,
            fontSize = 20.sp,
        )
    }
}