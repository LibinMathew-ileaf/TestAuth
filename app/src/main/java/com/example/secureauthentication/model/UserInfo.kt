package com.example.secureauthentication.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_info")
data class UserInfo(

    @PrimaryKey(autoGenerate = true)
    var id:Int?=null,
    val userDetail: String,
    val userDetailIv:String
)
