package com.jossidfactory.handwebber.common.ui.components.searchBar

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.jossidfactory.handwebber.common.ui.components.button.ButtonBase

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBarApp(
    paddingValues: PaddingValues,
    query: String,
    onQueryChange: (String) -> Unit,
    content: @Composable () -> Unit
) {
    SearchBar(
        modifier = Modifier.padding(paddingValues),
        query = query,
        onQueryChange = { onQueryChange(it) },
        onSearch = {},
        active = true,
        onActiveChange = {},
        placeholder = {
            Text(text = "Search")
        },
        leadingIcon = {
            Icon(imageVector = Icons.Default.Search, contentDescription = null)
        },
        trailingIcon = {
            ButtonBase(
                text = "Clear",
                modifier = Modifier.padding(end = 10.dp),
            ) { onQueryChange("")
            }
        }
    ){
        content()
    }
}