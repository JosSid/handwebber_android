package com.jossidfactory.handwebber.screen.components

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.input.KeyboardType

@Composable
fun TextFieldBase(
    text: String,
    textValue: String,
    keyboardType: KeyboardType = KeyboardType.Text,
    onValueChange: (String) -> Unit
) {
    OutlinedTextField(
        value = textValue, onValueChange = {
            onValueChange(it)
        },
        label = {
            Text(text = text, color = MaterialTheme.colorScheme.onBackground)
        }, singleLine = true,
        keyboardOptions = KeyboardOptions(
            keyboardType = keyboardType
        )
    )
}