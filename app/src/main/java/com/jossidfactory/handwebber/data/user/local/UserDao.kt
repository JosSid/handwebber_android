package com.jossidfactory.handwebber.data.user.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.jossidfactory.handwebber.data.user.local.model.UserLoggedEntity

@Dao
interface UserDao {
    @Query("SELECT * FROM user_logged")
    suspend fun getUserLogged(): List<UserLoggedEntity>?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUserLogged(user: UserLoggedEntity)
}