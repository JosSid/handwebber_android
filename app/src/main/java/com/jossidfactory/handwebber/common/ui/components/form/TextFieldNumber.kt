package com.jossidfactory.handwebber.common.ui.components.form

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun TextFieldNumber(
    text: String,
    value: Int = 1,
    step: Int = 1,
    maxChar: Int = 4,
    fieldWidth: Dp = 120.dp,
    isOnlyPositive: Boolean = false,
    onValueChange: (Int) -> Unit
) {
    var textValue by rememberSaveable { mutableStateOf(value.toString()) }
    var intValue by rememberSaveable { mutableIntStateOf(value) }

    Row {
        OutlinedTextField(
            value = textValue,
            onValueChange = {
                var newText = it.replace(Regex("[^0-9]"), "")
                if(newText.length > maxChar){
                    return@OutlinedTextField
                }
                if(newText.isEmpty()) {
                    intValue = 0
                    textValue = "0"
                    newText = "0"
                    onValueChange(intValue)
                }
                if(isOnlyPositive) {
                    if(newText.toInt() <= 0) {
                        intValue = 0
                        textValue = "0"
                        onValueChange(intValue)
                        return@OutlinedTextField
                    }
                }
                intValue = newText.toInt()
                textValue = newText
                onValueChange(intValue)
            },
            label = {
                Text(text = text, color = MaterialTheme.colorScheme.onBackground)
            },
            textStyle = TextStyle(fontSize = 20.sp, textAlign = TextAlign.Right),
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Number,
            ),
            modifier = Modifier
                .padding(16.dp)
                .width(fieldWidth)
                .background(MaterialTheme.colorScheme.background),
            singleLine = true,
            trailingIcon = {NumberInputButtons(
                onIncrementClick = {
                    onValueChange(intValue + step)
                    intValue += step
                    textValue = intValue.toString()
                                   },
                onDecrementClick = {
                    if(textValue.isEmpty()){
                        intValue = 0
                        textValue = "0"
                        onValueChange(intValue)
                        return@NumberInputButtons
                    }
                    if(isOnlyPositive) {
                        if(textValue.toInt() <= 0) {
                            intValue = 0
                            textValue = "0"
                            onValueChange(intValue)
                            return@NumberInputButtons
                        }
                    }
                    onValueChange(intValue - step)
                    intValue -= step
                    textValue = intValue.toString()
                }
            )}
        )

    }
}

@Composable
fun NumberInputButtons(
    onIncrementClick: () -> Unit,
    onDecrementClick: () -> Unit
) {
    Column {
        IconButton(
            onClick = { onIncrementClick() },
            modifier = Modifier
                .padding(4.dp)
                .size(18.dp)
        ) {
            Icon(
                imageVector = Icons.Default.KeyboardArrowUp,
                contentDescription = "Increment",
                tint = MaterialTheme.colorScheme.onBackground
            )
        }

        IconButton(
            onClick = { onDecrementClick() },
            modifier = Modifier
                .padding(4.dp)
                .size(18.dp)
        ) {
            Icon(
                imageVector = Icons.Default.KeyboardArrowDown,
                contentDescription = "Decrement",
                tint = MaterialTheme.colorScheme.onBackground
            )
        }
    }

}

@Preview(showBackground = true)
@Composable
fun NumberInputWithButtonsPreview() {
    var value by remember { mutableStateOf(0) }
    TextFieldNumber(
        text = "Number",
        value = value,
        isOnlyPositive = true
    ) { newValue -> value = newValue }
}
