package com.jossidfactory.handwebber.common.ui.components.confirm

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jossidfactory.handwebber.R
import com.jossidfactory.handwebber.common.ui.components.button.ButtonBase

@Composable
fun ConfirmerView(
    text: String,
    textConfirm: String = "Confirm",
    textCancel: String = "Cancel",
    onConfirm: () -> Unit,
    onCancel: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .fillMaxWidth()
            .height(LocalConfiguration.current.screenHeightDp.dp - 120.dp)
            .padding(horizontal = 32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Column(
            modifier = Modifier.padding(
                top = 16.dp,
                bottom = 16.dp
            ),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.primary),
                painter = painterResource(id = R.drawable.baseline_delete_forever_24),
                contentDescription = stringResource(R.string.error_image_description)
            )
            Text(
                stringResource(id = R.string.confirm_action),
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier.padding(top = 11.dp, bottom = 4.dp)
            )
            Text(
                text,
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.primary,
                textAlign = TextAlign.Center
            )
            ButtonBase(textConfirm,
                modifier = Modifier.padding(top = 48.dp),
                containerColor = MaterialTheme.colorScheme.error) {
                onConfirm()
            }
            ButtonBase(textCancel, modifier = Modifier.padding(top = 48.dp)) {
                onCancel()
            }
        }
    }
}

@Preview(name = "Confirmer", showBackground = true)
@Composable
fun ShowConfirmerView() {
    ConfirmerView(text = "Estas seguro de querer borrar tu cuenta?", onConfirm = { /*TODO*/ }) {
        
    }
}