package com.example.my_first_application.data

import android.content.Context
import android.content.SharedPreferences
import androidx.lifecycle.LiveData
import com.example.my_first_application.activity.HomeActivity
import com.example.my_first_application.constant.Constants
import com.example.my_first_application.data.UserDao
import com.example.my_first_application.data.UserDetails
import com.example.my_first_application.model.UserInfo
import com.google.gson.Gson

class UserRepository(private val userdao: UserDao) {

     fun addUser(userDetails: UserDetails){
        userdao.addUser(userDetails)
    }
     fun addLogin(userLogIn: UserLogIn){
        userdao.addLogin(userLogIn)
    }
    fun logInCheck(mMail:String,mPassword:String):UserLogIn{
        val userLogIn:UserLogIn=userdao.logInCheck(mMail,mPassword)
        return userLogIn
    }
    fun readData(usermail:String): List<UserDetails>{
        val userDetails:List<UserDetails> =userdao.readData(usermail)
        return userDetails
    }
}