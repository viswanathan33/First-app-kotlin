package com.example.my_first_application.constant

import android.content.Context
import android.content.Intent
class Constants {
    companion object BundleKey{

        const val EDITUSER = "EDITUSER"
        const val USER_INFO = "USER_INFO"
        const val mailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"
        const val passwordPatternDigit = "^(?=.*[0-9])" + ".{0,}$"
        const val passwordPatternLowerCase = "^(?=.*[a-z])" + ".{0,}$"
        const val passwordPatternUpperCase = "^(?=.*[A-Z])" + ".{0,}$"
        const val passwordPatternSplChar = "^(?=.*[@#$%^&+=])" + ".{0,}$"

    }
}