package com.jossidfactory.handwebber.data.user.mappers

import com.jossidfactory.handwebber.data.user.remote.dto.UpdateUserRequestDto
import com.jossidfactory.handwebber.domain.user.model.UpdateUserRequestModel
import com.jossidfactory.handwebber.utils.toMultipart
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.toRequestBody

fun UpdateUserRequestModel.toUpdateUserRequestDto() = UpdateUserRequestDto(
    username = username?.toRequestBody("multipart/form-data".toMediaTypeOrNull()),
    email = email?.toRequestBody("multipart/form-data".toMediaTypeOrNull()),
    password = password?.toRequestBody("multipart/form-data"
        .toMediaTypeOrNull()),
    subscriptions = subscriptions?.toRequestBody("multipart/form-data".toMediaTypeOrNull()),
    image = image?.toMultipart()
)