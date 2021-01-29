package com.example.my_first_application.model

class RegisterInfo(name:String?,gender:String?,mail:String?,age:String?) {
    private var name:String = name!!
    private var gender:String = gender!!
    private var mail:String = mail!!
    private var age:String = age!!
    fun getName():String?{
        return name
    }
    fun getEmail(): String? {
        return mail
    }
    fun getGender():String?{
        return gender
    }
    fun getAge():String?{
        return age
    }
}