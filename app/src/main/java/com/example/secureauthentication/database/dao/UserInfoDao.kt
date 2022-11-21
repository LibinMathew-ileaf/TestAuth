package com.example.secureauthentication.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.secureauthentication.model.UserInfo

@Dao
interface UserInfoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(characters: UserInfo)

    @Query("SELECT * FROM user_info")
    fun getUserData(): UserInfo?


}
