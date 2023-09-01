package com.jossidfactory.handwebber.data.user.mappers

import com.jossidfactory.handwebber.data.user.remote.dto.LoginUserResponseDto
import com.jossidfactory.handwebber.domain.user.model.LoginUserResponseModel

fun LoginUserResponseDto.toLoginUserResponseModel() = LoginUserResponseModel(
    token = token
)