package com.example.my_first_application.data

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.my_first_application.data.UserDataBase
import com.example.my_first_application.data.UserDetails
import com.example.my_first_application.data.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UserViewModel(application: Application):AndroidViewModel(application) {
    private val repository: UserRepository
    init {
        val userDao = UserDataBase.getDataBase(application).userDao()
        repository= UserRepository(userDao)
    }
    fun addUser(userDetails: UserDetails){
        viewModelScope.launch (Dispatchers.IO){
            repository.addUser(userDetails)
        }
    }
    fun addLogin(userLogIn: UserLogIn){
        viewModelScope.launch (Dispatchers.IO){
            repository.addLogin(userLogIn)
        }
    }
    fun logInCheck(mMail:String,mPassword:String):UserLogIn{
        val userLogIn:UserLogIn=repository.logInCheck(mMail,mPassword)
        return userLogIn

    }
    fun readData(usermail:String): List<UserDetails> {
        val userDetails: List<UserDetails> =repository.readData(usermail)
        return userDetails

    }

}