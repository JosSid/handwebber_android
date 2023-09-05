package com.jossidfactory.handwebber.screen.components

import android.graphics.Bitmap
import android.net.Uri
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jossidfactory.handwebber.R
import com.jossidfactory.handwebber.utils.loadBitmap

@Composable
fun PickerImageField(
    onImageChange: (Bitmap) -> Unit
) {

    var selectedimage: Bitmap? by rememberSaveable { mutableStateOf(null) }

    val context = LocalContext.current

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) {uri: Uri? ->
        uri?.let {
            selectedimage = context.contentResolver.loadBitmap(it)
            onImageChange(selectedimage!!)
            Log.d("IMAGEVALUE", selectedimage.toString())
        }
    }
    Row(
        modifier = Modifier
            .padding(16.dp)
            .clickable { launcher.launch("image/*") },
        verticalAlignment = Alignment.CenterVertically
    ) {

        Text(
            text = "Upload Image",
            fontSize = 16.sp,
            modifier = Modifier.padding(end = 10.dp)
        )
        Image(
            painter = painterResource(id = R.drawable.baseline_photo_camera_24),
            contentDescription = null,
            modifier = Modifier
                .clip(CircleShape)
                .background(Color.Black)
                .size(50.dp)
                .padding(10.dp)
        )
    }
}