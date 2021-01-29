package com.example.my_first_application.activity

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.preference.PreferenceManager
import com.example.my_first_application.R
import com.example.my_first_application.constant.Constants
import com.example.my_first_application.data.UserDetails
import com.example.my_first_application.data.UserViewModel
import com.example.my_first_application.databinding.ActivityAdduserBinding
import com.example.my_first_application.model.UserInfo
import com.example.my_first_application.util.ToCallActivity
import com.google.gson.Gson
class AddUserActivity : AppCompatActivity() {
    lateinit var preferences: SharedPreferences
    private lateinit var binding: ActivityAdduserBinding
    private val gson=Gson()
    private lateinit var mUserViewModel: UserViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=DataBindingUtil.setContentView(this,R.layout.activity_adduser)

        preferences= PreferenceManager.getDefaultSharedPreferences(this)
        val json: String? = preferences.getString(Constants.USER_INFO, "")
        val userInfo:UserInfo=gson.fromJson(json,UserInfo::class.java)
        val usermail=userInfo.getEmail().toString()
        //val sharedPreferences:SharedPreferences=this.getSharedPreferences(Constants.share_pref, Context.MODE_PRIVATE)
        mUserViewModel=ViewModelProvider(this).get(UserViewModel::class.java)
        val actionBar = supportActionBar
        actionBar!!.hide()
        binding.buttonRegister.setOnClickListener {
            val username=binding.personName.text.toString()
            val usergender=binding.gender.text.toString()
            val userage=binding.age.text.toString()
            val useraddress=binding.address.text.toString()
            val userDetails=UserDetails(0,username,usergender,useraddress,usermail,userage)
            mUserViewModel.addUser(userDetails)
            Toast.makeText(applicationContext, getString(R.string.userAddedSuccesfully), Toast.LENGTH_SHORT).show()
            ToCallActivity.callActivity(this, HomeActivity::class.java)
            //Log.d("TAG","data===>"+json)
            finish()
        }
    }
}