package com.example.my_first_application.util.extensions

import android.content.SharedPreferences
import androidx.core.content.edit

fun SharedPreferences.put(name:String,any: String){
        edit().putString(name,any).apply()
        edit().commit()
    }
    fun SharedPreferences.remove(name: String){
        edit().remove(name).apply()
        edit().commit()
    }
fun SharedPreferences.clear(){
    edit().clear().apply()
    edit().commit()
}

