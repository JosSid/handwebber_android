package com.jossidfactory.handwebber.data.user.mappers

import com.jossidfactory.handwebber.BuildConfig
import com.jossidfactory.handwebber.data.user.local.model.UserLoggedEntity
import com.jossidfactory.handwebber.domain.user.model.UserLoggedModel

fun UserLoggedEntity.toUserLoggedModel(): UserLoggedModel {

    return UserLoggedModel(
        id = id,
        username = username,
        email = email,
        image = if(image.isNotEmpty()) (BuildConfig.SERVER_URL + image?.removePrefix("/"))
        else "https://place-hold.it/800x800?text=Empty Image&fontsize=50",
        subscriptions = subscriptions
    )
}
