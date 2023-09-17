package com.jossidfactory.handwebber.common.ui.components.form

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType

@Composable
fun TextFieldBase(
    text: String,
    textValue: String,
    modifier: Modifier = Modifier,
    singleLine: Boolean = true,
    keyboardType: KeyboardType = KeyboardType.Text,
    onValueChange: (String) -> Unit
) {
    OutlinedTextField(
        value = textValue,
        modifier = modifier,
        onValueChange = {
            onValueChange(it)
        },
        label = {
            Text(text = text, color = MaterialTheme.colorScheme.onBackground)
        }, singleLine = singleLine,
        keyboardOptions = KeyboardOptions(
            keyboardType = keyboardType
        )
    )
}