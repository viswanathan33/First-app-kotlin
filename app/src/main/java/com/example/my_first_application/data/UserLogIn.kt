package com.example.my_first_application.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "userLoginTable")
class UserLogIn(
    @ColumnInfo(name = "email") @PrimaryKey val email:String,
    @ColumnInfo(name = "password") val password:String
) {
}


