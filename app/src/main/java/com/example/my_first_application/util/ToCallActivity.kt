package com.example.my_first_application.util

import android.content.Context
import android.content.Intent

class ToCallActivity {
    companion object callActivity {
        fun<T> callActivity(context: Context, classType: Class<T>)
        {
            val intent = Intent(context, classType)
            context.startActivity(intent)
        }
    }

}