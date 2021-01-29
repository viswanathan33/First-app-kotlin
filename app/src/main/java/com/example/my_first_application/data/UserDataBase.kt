package com.example.my_first_application.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities =[UserDetails::class,UserLogIn::class] ,version = 1,exportSchema = false)

abstract class UserDataBase:RoomDatabase() {
    abstract fun userDao(): UserDao
    companion object{
        @Volatile
        private var INSTANCE: UserDataBase?=null
        fun getDataBase(context: Context): UserDataBase
        {
            val tempInstance= INSTANCE
            if (tempInstance!=null){
                return tempInstance
            }
            synchronized(this){
                val instance= Room.databaseBuilder(context.applicationContext,
                    UserDataBase::class.java,"USER_DATABASE").allowMainThreadQueries().build()
                INSTANCE =instance
                return instance
            }
        }
    }
}