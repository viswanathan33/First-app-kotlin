package com.example.my_first_application.activity

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.preference.PreferenceManager
import com.example.my_first_application.R
import com.example.my_first_application.constant.Constants
import com.example.my_first_application.databinding.ActivityGreetingBinding
import com.example.my_first_application.util.ToCallActivity
import java.util.*

class GreetingsActivity : AppCompatActivity() {
    lateinit var preferences: SharedPreferences
    private lateinit var binding: ActivityGreetingBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=DataBindingUtil.setContentView(this,R.layout.activity_greeting)
        val actionBar = supportActionBar
        preferences= PreferenceManager.getDefaultSharedPreferences(this)
        //val sharedPreferences:SharedPreferences=getSharedPreferences(Constants.share_pref, Context.MODE_PRIVATE)
        val shrf: String? = preferences.getString(Constants.USER_INFO, "")
        val timer = Timer()
        actionBar!!.hide()
        if (shrf.isNullOrEmpty()) {
            timer.schedule(object : TimerTask() {
                override fun run() {
                    ToCallActivity.callActivity(this@GreetingsActivity,LoginActivity::class.java)
                    /*val intent = Intent(this@GreetingsActivity, LoginActivity::class.java)
                    startActivity(intent)
                     finish()*/
                    finish()
                }
            }, 3000)
            binding.buttonStart.setOnClickListener {
                timer.cancel()
                ToCallActivity.callActivity(this@GreetingsActivity,LoginActivity::class.java)
                finish()
            }
        }
        else{
            ToCallActivity.callActivity(this, HomeActivity::class.java)
            finish()
        }
    }
}