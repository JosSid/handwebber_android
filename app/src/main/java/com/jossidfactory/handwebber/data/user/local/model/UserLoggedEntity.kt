package com.jossidfactory.handwebber.data.user.local.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_logged")
data class UserLoggedEntity(
    @PrimaryKey val id: String = "",
    @ColumnInfo(name = "username") val username: String,
    @ColumnInfo(name = "email") val email: String,
    @ColumnInfo(name = "image") val image: String,
    @ColumnInfo(name = "subscriptions") val subscriptions: String,
)
