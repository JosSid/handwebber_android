package com.jossidfactory.handwebber.data.user.mappers

import com.jossidfactory.handwebber.BuildConfig
import com.jossidfactory.handwebber.data.user.local.model.UserLoggedEntity
import com.jossidfactory.handwebber.domain.user.model.UserLoggedModel
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import org.koin.core.Koin
import org.koin.core.context.GlobalContext

fun UserLoggedEntity.toUserLoggedModel(): UserLoggedModel {
    val koin: Koin = GlobalContext.get()
    val moshi: Moshi = koin.get()

    val listType = Types.newParameterizedType(List::class.java, String::class.java)
    val adapter: JsonAdapter<List<String>> = moshi.adapter(listType)

    return UserLoggedModel(
        id = id,
        username = username,
        email = email,
        image = if(image.isNotEmpty()) (BuildConfig.SERVER_URL + image?.removePrefix("/"))
        else "https://place-hold.it/800x800?text=Empty Image&fontsize=50",
        subscriptions = adapter.fromJson(subscriptions)
    )
}