package com.jossidfactory.handwebber.utils

import android.content.ContentResolver
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.ByteArrayOutputStream

fun ContentResolver.loadBitmap(uri: Uri): Bitmap {
    val inputStream = openInputStream(uri)
    val image = BitmapFactory.decodeStream(inputStream)
    inputStream?.close()
    return image
}

fun Bitmap.toMultipart(): MultipartBody.Part {
    val bos = ByteArrayOutputStream()
    compress(Bitmap.CompressFormat.JPEG, 80 /*ignored for PNG*/, bos)
    val bitmapData = bos.toByteArray()


    val name: RequestBody =
        bitmapData.toRequestBody("image/*".toMediaTypeOrNull(), 0, bitmapData.size)
    return MultipartBody.Part.createFormData("image", "user.jpeg", name)
}



