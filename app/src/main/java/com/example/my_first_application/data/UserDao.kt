package com.example.my_first_application.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface UserDao {
    @Insert(onConflict=OnConflictStrategy.IGNORE)
    fun addUser(userDetails: UserDetails)

    @Insert(onConflict=OnConflictStrategy.IGNORE)
    fun addLogin(userLogIn: UserLogIn)

    @Query("SELECT*FROM userTable WHERE mail =:umail")
    fun readData(umail:String?):List<UserDetails>

    @Query("SELECT*FROM userLoginTable WHERE email =:mail AND password=:mPassword")
    fun logInCheck(mail:String,mPassword:String):UserLogIn
}