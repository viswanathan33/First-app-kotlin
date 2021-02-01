package com.example.my_first_application.activity

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.my_first_application.BR
import com.example.my_first_application.R
import com.example.my_first_application.constant.Constants
import com.example.my_first_application.databinding.ActivityEditUserBinding
import com.example.my_first_application.model.UserInfo
import com.google.gson.Gson

class EditUserActivity : AppCompatActivity() {
    private val gson= Gson()
    private lateinit var binding: ActivityEditUserBinding
    private var userInfo: UserInfo = UserInfo()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=DataBindingUtil.setContentView(this,
            R.layout.activity_edit_user
        )
        binding.editUserInfo = userInfo
        val extras: Bundle? =intent.extras
        if(extras!=null){
            val json:String?=extras.getString(Constants.EDITUSER)
            userInfo =gson.fromJson(json, UserInfo::class.java)
            binding.setVariable(BR.editUserInfo,userInfo)
            binding.executePendingBindings()
            /*binding.personNameEdit.setText(userInfo.getName())
            binding.addressEdit.setText(userInfo.getAdress())
            binding.genderEdit.setText(userInfo.getGender())
            binding.ageEdit.setText(userInfo.getAge())*/
        }
        binding.btnEditUser.setOnClickListener {
            val userInfo=UserInfo()
            userInfo.setName(binding.personNameEdit.text.toString())
            userInfo.setAddress(binding.addressEdit.text.toString())
            userInfo.setGender(binding.genderEdit.text.toString())
            userInfo.setAge(binding.ageEdit.text.toString())
            val json:String=gson.toJson(userInfo)
            val intent= Intent()
            intent.putExtra(Constants.EDITUSER,json)
            setResult(Activity.RESULT_OK,intent)
            finish()
        }

    }
}