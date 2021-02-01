package com.example.my_first_application.activity

import android.app.Activity
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.preference.PreferenceManager
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.my_first_application.R
import com.example.my_first_application.adapter.UserDetailsAdapter
import com.example.my_first_application.adapter.listener.BaseListener
import com.example.my_first_application.constant.Constants
import com.example.my_first_application.data.UserDetails
import com.example.my_first_application.data.UserViewModel
import com.example.my_first_application.databinding.ActivityHomeBinding
import com.example.my_first_application.model.UserInfo
import com.example.my_first_application.util.ToCallActivity
import com.example.my_first_application.util.extensions.clear
import com.google.gson.Gson
import java.util.*

class HomeActivity : AppCompatActivity() {
    lateinit var preferences: SharedPreferences
    private lateinit var binding: ActivityHomeBinding
    private val gson= Gson()
    private val userList = ArrayList<UserDetails>()
    private lateinit var userViewModel:UserViewModel
    private lateinit var userListAdapter: UserDetailsAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=DataBindingUtil.setContentView(this,R.layout.activity_home)

        preferences= PreferenceManager.getDefaultSharedPreferences(this)
        //val sharedPreferences: SharedPreferences =getSharedPreferences(Constants.share_pref, Context.MODE_PRIVATE)
        val json: String? = preferences.getString(Constants.USER_INFO, "")
        val userInfo: UserInfo =gson.fromJson(json, UserInfo::class.java)
        val usermail =userInfo.getEmail().toString()



        val listener=object:BaseListener{
            override fun onClickItem(v:View,position:Int) {
                val editName=userList.get(position).name
                val editAdress=userList.get(position).address
                val editGender=userList.get(position).gender
                val editAge=userList.get(position).age

                val gson = Gson()
                val userInfo = UserInfo()

                userInfo.setName(editName)
                userInfo.setAddress(editAdress)
                userInfo.setAge(editAge)
                userInfo.setGender(editGender)

                val json: String = gson.toJson(userInfo)

                val intent=Intent(applicationContext,
                    EditUserActivity::class.java)
                intent.putExtra(Constants.EDITUSER,json)
                startActivityForResult(intent,1)
                Toast.makeText(applicationContext, "Item selected", Toast.LENGTH_SHORT).show()
            }

        }

        userListAdapter = UserDetailsAdapter(userList,listener)

        val layoutManager = LinearLayoutManager(applicationContext)
        val userListView: RecyclerView = findViewById(R.id.recycler_view)


        userListView.layoutManager = layoutManager
        userListView.itemAnimator = DefaultItemAnimator()
        userListView.adapter = userListAdapter

        userViewModel=ViewModelProvider(this).get(UserViewModel::class.java)

        val userDetails:List<UserDetails> =userViewModel.readData(usermail)


        if(userDetails!=null){
            userList.addAll(userDetails)
            userListAdapter.notifyDataSetChanged()
        }

        binding.floatingActionButtonAdd.setOnClickListener {
            ToCallActivity.callActivity(this, AddUserActivity::class.java)
        }
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode== Activity.RESULT_OK){
            if (requestCode==1){
                if (data!=null){
                    val json:String?=data.getStringExtra(Constants.EDITUSER)
                    val userInfo= gson.fromJson(json, UserInfo::class.java)
                   // userList.add(userInfo)
                    userListAdapter.notifyItemChanged(0)

                }
            }

        }
    }
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.drop_menu, menu)
        return true
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.item1 -> {
                Toast.makeText(applicationContext, getString(R.string.item1Selected), Toast.LENGTH_SHORT).show()
                true
            }
            R.id.item2 -> {
                Toast.makeText(applicationContext, getString(R.string.item2Selected), Toast.LENGTH_SHORT).show()
                true
            }
            R.id.logoutItem -> {
               /* val sharedPreferences:SharedPreferences=getSharedPreferences(Constants.share_pref, Context.MODE_PRIVATE)
                val editor: Editor = sharedPreferences.edit()
                editor.clear()
                editor.apply()
                editor.commit()*/
                preferences= PreferenceManager.getDefaultSharedPreferences(this)
                preferences.clear()
                ToCallActivity.callActivity(this, LoginActivity::class.java)
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}