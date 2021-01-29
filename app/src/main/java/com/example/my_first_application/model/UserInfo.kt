package com.example.my_first_application.model

class UserInfo {
    private var name: String? = null
    private var email: String? = null
    private var password: String? = null
    private var confirmPassword: String? = null
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
}