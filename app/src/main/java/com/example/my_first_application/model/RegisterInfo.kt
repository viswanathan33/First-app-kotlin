package com.example.my_first_application.model

class RegisterInfo(name:String?,address:String?,gender:String?,mail:String?,age:String?) {
    private var name:String = name!!
    private var gender:String = gender!!
    private var mail:String = mail!!
    private var age:String = age!!
    private var address:String = address!!
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
    fun getAddress():String?{
        return address
    }

}