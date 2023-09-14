package com.jossidfactory.handwebber.data.user.local.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.jossidfactory.handwebber.utils.StringListConverter

@Entity(tableName = "user_logged")
@TypeConverters(StringListConverter::class)
data class UserLoggedEntity(
    @PrimaryKey val id: String = "",
    @ColumnInfo(name = "username") val username: String,
    @ColumnInfo(name = "email") val email: String,
    @ColumnInfo(name = "image") val image: String,
    @ColumnInfo(name = "subscriptions") val subscriptions: List<String?>?
)
