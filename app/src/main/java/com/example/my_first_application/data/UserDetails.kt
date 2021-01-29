package com.example.my_first_application.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
@Entity(
    tableName = "userTable",
    foreignKeys = [ForeignKey(
        entity = UserLogIn::class,
        parentColumns = arrayOf("email"),
        childColumns = arrayOf("mail"),
        onDelete = ForeignKey.CASCADE
    )]
)
class UserDetails(
    @PrimaryKey(autoGenerate = true) val tempId:Int,
    @ColumnInfo(name = "name") val name:String,
    @ColumnInfo(name = "gender") val gender:String,
    @ColumnInfo(name = "adress") val address:String,
    @ColumnInfo(name = "mail") val mail:String,
    @ColumnInfo(name = "age") val age:String
)
{
    fun getUserName():String{
        return name
    }
    fun getUserGender():String{
        return gender
    }
    fun getUserAddress():String{
        return address
    }
    fun getUserMail():String{
        return mail
    }
    fun getUserAge():String{
        return age
    }
}