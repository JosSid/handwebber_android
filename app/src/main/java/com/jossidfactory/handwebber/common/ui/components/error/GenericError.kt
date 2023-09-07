package com.jossidfactory.handwebber.common.ui.components.error

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jossidfactory.handwebber.R
import com.jossidfactory.handwebber.common.domain.entity.Error
import timber.log.Timber

@Composable
fun ErrorView(
    error: Error = Error.Unknown("Unknown"),
    onRetry: (() -> Unit)? = null
) {
    when (error) {
        is Error.EmptyView,
        is Error.EmptySearch,
        is Error.Connectivity,
        is Error.Unauthorized,
        is Error.Conflict,
        is Error.Unprocesable,
        is Error.Unknown -> {
            GenericErrorView(
                title = stringResource(
                    when (error) {
                        is Error.EmptyView -> {
                            R.string.error_empty_title
                        }
                        is Error.EmptySearch -> {
                            R.string.error_search_title
                        }
                        is Error.Connectivity -> {
                            R.string.error_empty_title
                        }
                        is Error.Unauthorized -> {
                            R.string.error_unauthorized_title
                        }
                        is Error.Conflict -> {
                            R.string.error_conflict_title
                        }
                        is Error.Unprocesable -> {
                            R.string.error_unprocesable_title
                        }
                        else -> {
                            R.string.error_unknown_title
                        }
                    }
                ),
                text = stringResource(
                    when (error) {
                        is Error.EmptyView -> {
                            R.string.error_empty_text
                        }
                        is Error.EmptySearch -> {
                            R.string.error_search_text
                        }
                        is Error.Connectivity -> {
                            R.string.error_empty_text
                        }
                        is Error.Unauthorized -> {
                            R.string.error_unauthorized_text
                        }
                        is Error.Conflict -> {
                            R.string.error_conflict_text
                        }
                        is Error.Unprocesable -> {
                            R.string.error_unprocesable_text
                        }
                        else -> {
                            R.string.error_unknown_text
                        }
                    }
                ),
                painter = painterResource(
                    when (error) {
                        is Error.EmptyView -> {
                            R.drawable.ic_offline
                        }
                        is Error.EmptySearch -> {
                            R.drawable.baseline_search_off_24
                        }
                        is Error.Connectivity -> {
                            R.drawable.ic_offline
                        }
                        else -> {
                            R.drawable.ic_unknown_error
                        }
                    }
                ),
                onRetry = onRetry
            )
        }
    }
}


@Composable
private fun GenericErrorView(
    title: String,
    text: String,
    painter: Painter,
    onRetry: (() -> Unit)?
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
                colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.error),
                painter = painter,
                contentDescription = stringResource(R.string.error_image_description)
            )
            Text(
                title,
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.error,
                modifier = Modifier.padding(top = 11.dp, bottom = 4.dp)
            )
            Text(
                text,
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.error,
                textAlign = TextAlign.Center
            )
        }
        onRetry?.let {
            Button(
                shape = RoundedCornerShape(10.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.primary
                ),
                onClick = it,
                modifier = Modifier.padding(top = 24.dp),
                elevation = ButtonDefaults.buttonElevation()
            ) {
                Text(
                    stringResource(R.string.error_retry),
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.background,
                    modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp)
                )
            }
        }
    }
}

@Preview
@Composable
private fun TestGenericErrorView() {
    GenericErrorView(
        title = "Test title",
        text = "Test text error",
        painter = painterResource(id = R.drawable.ic_offline)
    )
    {
        Timber.d("test")
    }
}