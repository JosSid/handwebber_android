package com.jossidfactory.handwebber.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.jossidfactory.handwebber.data.user.local.UserDao
import com.jossidfactory.handwebber.data.user.local.model.UserLoggedEntity

@Database(entities = [UserLoggedEntity::class], version = 1)
abstract class HandwebberDataBase: RoomDatabase() {
    abstract fun userDao(): UserDao
}