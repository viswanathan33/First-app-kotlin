package com.example.my_first_application.activity

import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.preference.PreferenceManager
import com.example.my_first_application.R
import com.example.my_first_application.constant.Constants
import com.example.my_first_application.data.UserLogIn
import com.example.my_first_application.data.UserViewModel
import com.example.my_first_application.databinding.ActivitySignupBinding
import com.example.my_first_application.model.UserInfo
import com.example.my_first_application.util.ToCallActivity
import com.example.my_first_application.util.extensions.put
import com.google.gson.Gson

class SigupActivity : AppCompatActivity() {
    private lateinit var preferences: SharedPreferences
    private lateinit var binding: ActivitySignupBinding
    private lateinit var mUserViewModel: UserViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=DataBindingUtil.setContentView(this,R.layout.activity_signup)
        val actionBar = supportActionBar

        preferences=PreferenceManager.getDefaultSharedPreferences(this)
        mUserViewModel= ViewModelProvider(this).get(UserViewModel::class.java)
        actionBar!!.hide()

        val sigupname=binding.editTextTextSignUpPersonName
        val sigupmail=binding.editTextTextSigUpEmailAddress
        val siguppassword=binding.editTextTextSigUpPassword
        val sigupconfirmpassword=binding.editTextTextSigUpPasswordConfirm

        binding.buttonSignup.setOnClickListener {

            if (sigupname.text.toString().isEmpty())
            {
                sigupname.error = getString(R.string.nameEmpty)
            }
            else if (sigupmail.text.toString().isEmpty()) {
                sigupmail.error = getString(R.string.mailEmpty)
            } else if (sigupmail.text.toString().trim().matches(Constants.mailPattern.toRegex())) {
                if (siguppassword.text.toString().isEmpty()) {
                    siguppassword.error = getString(R.string.passwordEmpty)
                } else if (siguppassword.text.toString().length < 8) {
                    siguppassword.error = getString(R.string.passwordLength)
                } else if (!siguppassword.text.toString().trim().matches(Constants.passwordPatternSplChar.toRegex())) {
                    siguppassword.error = getString(R.string.passwordSplChar)
                } else if (!siguppassword.text.toString().trim().matches(Constants.passwordPatternUpperCase.toRegex())) {
                    siguppassword.error = getString(R.string.passwordUpperCase)
                } else if (!siguppassword.text.toString().trim().matches(Constants.passwordPatternLowerCase.toRegex())) {
                    siguppassword.error = getString(R.string.passwordLowerCase)
                } else if (!siguppassword.text.toString().trim().matches(Constants.passwordPatternDigit.toRegex())) {
                    siguppassword.error = getString(R.string.passwordDigit)
                }
                else if (siguppassword.text.toString() != sigupconfirmpassword.text.toString()) {
                    sigupconfirmpassword.error = getString(R.string.passwordMatch)
                }
                else {

                    val gson=Gson()
                    val userInfo= UserInfo()
                    userInfo.setName(sigupname.text.toString())
                    userInfo.setEmail(sigupmail.text.toString())
                    userInfo.setpassword(siguppassword.text.toString())
                    userInfo.setConfirmPassword(sigupconfirmpassword.text.toString())

                    val json:String=gson.toJson(userInfo)

                    val dbmail=binding.editTextTextSigUpEmailAddress.text.toString()
                    val dbpassword=binding.editTextTextSigUpPassword.text.toString()
                    val userLogIn= UserLogIn(dbmail,dbpassword)
                    mUserViewModel.addLogin(userLogIn)
                    Toast.makeText(applicationContext, getString(R.string.signUpSuccesfully), Toast.LENGTH_SHORT).show()

                    preferences.put(Constants.USER_INFO,json)

                    ToCallActivity.callActivity(this, HomeActivity::class.java)
                    finish()
                }
            } else {
                sigupmail.error = getString(R.string.invalidMail)
            }

        }
        binding.textViewLogin.setOnClickListener {
            ToCallActivity.callActivity(this, LoginActivity::class.java)
            finish()
        }
    }
}
