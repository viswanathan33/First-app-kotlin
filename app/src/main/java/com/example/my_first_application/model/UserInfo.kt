package com.example.my_first_application.model

class UserInfo {
    private var name: String? = null
    private var email: String? = null
    private var password: String? = null
    private var confirmPassword: String? = null
    private var address: String? = null
    private var age: String? = null
    private var gender: String? = null
    fun setName(name: String?) {
        this.name = name
    }
    fun setEmail(email: String?) {
        this.email = email
    }
    fun setpassword(password: String?) {
        this.password = password
    }
    fun setConfirmPassword(confirm_password: String?) {
        this.confirmPassword = confirm_password
    }
    fun setAddress(address: String?) {
        this.address = address
    }
    fun setAge(age: String?) {
        this.age = age
    }
    fun setGender(gender: String?) {
        this.gender = gender
    }
    fun getName():String?{
        return name
    }
    fun getEmail(): String? {
        return email
    }
    fun getPassword():String?{
        return password
    }
    fun getConfirmPassword():String?{
        return confirmPassword
    }
    fun getAdress():String?{
        return address
    }
    fun getAge():String?{
        return age
    }
    fun getGender():String?{
        return gender
    }
}