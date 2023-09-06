package com.jossidfactory.handwebber.common.ui.components.form

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CheckboxBase(
    text: String,
    checked: Boolean,
    onCheckedChange: () -> Unit
) {
    Row(
        modifier = Modifier.padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Checkbox(
            checked = checked,
            onCheckedChange = { onCheckedChange() },
            modifier = Modifier.padding(end = 8.dp)
        )
        Text(
            text = text,
            fontSize = 16.sp

        )
    }
}