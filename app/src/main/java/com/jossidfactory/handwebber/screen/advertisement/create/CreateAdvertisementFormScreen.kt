package com.jossidfactory.handwebber.screen.advertisement.create

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import com.jossidfactory.handwebber.R
import com.jossidfactory.handwebber.common.ui.components.form.TextFieldBase
import com.jossidfactory.handwebber.common.ui.components.form.TextFieldNumber
import org.koin.androidx.compose.koinViewModel

@Composable
fun CreateAdvertisementFormScreen(
    createAdvertisementViewModel: CreateAdvertisementViewModel = koinViewModel(),
    paddingValues: PaddingValues
) {

    val state: CreateAdvertisementState by createAdvertisementViewModel.state.observeAsState(
        CreateAdvertisementState()
    )
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        item {
            TextFieldBase(text = stringResource(id = R.string.create_title),
                textValue = state.title,
                onValueChange = { }
            )
            TextFieldBase(text = stringResource(id = R.string.create_description),
                textValue = state.description,
                singleLine = false,
                onValueChange = { }
            )
            TextFieldNumber(
                text = "Stock",
                value = state.stock,
                isOnlyPositive = true,
                fieldWidth = dimensionResource(id = R.dimen.textfieldnumber_width)
            ) {
                createAdvertisementViewModel.getStock(it)
            }
        }

    }
}