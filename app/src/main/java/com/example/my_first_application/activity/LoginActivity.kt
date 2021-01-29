package com.example.my_first_application.activity

import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.my_first_application.R
import com.example.my_first_application.constant.Constants
import com.example.my_first_application.data.UserLogIn
import com.example.my_first_application.data.UserViewModel
import com.example.my_first_application.databinding.ActivityLoginBinding
import com.example.my_first_application.model.UserInfo
import com.example.my_first_application.util.ToCallActivity
import com.example.my_first_application.util.extensions.put
import com.google.gson.Gson

class LoginActivity : AppCompatActivity() {
    private lateinit var preferences: SharedPreferences
    private lateinit var binding: ActivityLoginBinding
    private lateinit var mUserViewModel: UserViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)
        val actionBar = supportActionBar
        preferences= androidx.preference.PreferenceManager.getDefaultSharedPreferences(this)
        actionBar!!.hide()
        mUserViewModel = ViewModelProvider(this).get(UserViewModel::class.java)
        val loginMail=binding.editTextTextEmailAddress
        val loginPassword=binding.editTextTextPassword
        binding.logButton.setOnClickListener {
            if (loginMail.text.toString().isEmpty()) {
                loginMail.error = getString(R.string.mailEmpty)
            } else if (loginMail.text.toString().trim().matches(Constants.mailPattern.toRegex())) {
                if (loginPassword.text.toString().isEmpty()) {
                    loginPassword.error = getString(R.string.passwordEmpty)
                } else if (loginPassword.text.toString().length < 8) {
                    loginPassword.error = getString(R.string.passwordLength)
                } else if (!loginPassword.text.toString().trim()
                        .matches(Constants.passwordPatternSplChar.toRegex())
                ) {
                    loginPassword.error = getString(R.string.passwordSplChar)
                } else if (!loginPassword.text.toString().trim()
                        .matches(Constants.passwordPatternUpperCase.toRegex())
                ) {
                    loginPassword.error = getString(R.string.passwordUpperCase)
                } else if (!loginPassword.text.toString().trim()
                        .matches(Constants.passwordPatternLowerCase.toRegex())
                ) {
                    loginPassword.error = getString(R.string.passwordLowerCase)
                } else if (!loginPassword.text.toString().trim()
                        .matches(Constants.passwordPatternDigit.toRegex())
                ) {
                    loginPassword.error = getString(R.string.passwordDigit)
                } else {
                    val gson = Gson()
                    val userInfo = UserInfo()
                    userInfo.setEmail(loginMail.text.toString())
                    userInfo.setpassword(loginPassword.text.toString())
                    val json: String = gson.toJson(userInfo)

                    val lMail = binding.editTextTextEmailAddress.text.toString()
                    val lPpassword = binding.editTextTextPassword.text.toString()


                    val userLogIn: UserLogIn = mUserViewModel.logInCheck(lMail, lPpassword)
                    if (userLogIn == null) {
                        Toast.makeText(
                            applicationContext,
                            getString(R.string.invalidMailOrPassword),
                            Toast.LENGTH_SHORT
                        ).show()
                    } else {
                        Toast.makeText(applicationContext, getString(R.string.loginSuccesfully), Toast.LENGTH_SHORT).show()
                        preferences.put(Constants.USER_INFO,json)

                        ToCallActivity.callActivity(this, HomeActivity::class.java)
                        finish()
                    }
                }
            } else {
                loginMail.error = getString(R.string.invalidMail)
            }

        }

        binding.signup.setOnClickListener {
            ToCallActivity.callActivity(this, SigupActivity::class.java)
            finish()
        }

    }
}