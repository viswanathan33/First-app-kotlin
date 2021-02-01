package com.example.my_first_application.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.my_first_application.R
import com.example.my_first_application.adapter.listener.BaseListener
import com.example.my_first_application.data.UserDetails

internal class UserDetailsAdapter(
    private var userList: List<UserDetails>,
    private var listener: BaseListener
) : RecyclerView.Adapter<UserDetailsAdapter.ExampleViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExampleViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.user_item, parent, false)
        return ExampleViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ExampleViewHolder, position: Int) {
        val currentItem = userList[position]

        holder.name.text = currentItem.getUserName()
        holder.gender.text = currentItem.getUserGender()
        holder.adress.text = currentItem.getUserAddress()
        holder.age.text = currentItem.getUserAge()


        /*holder.itemView.setOnClickListener { view ->
            val curContext = view.context
            val intent = Intent(curContext, EditUserActivity::class.java)
            curContext.startActivity(intent)
        }*/

    }

    override fun getItemCount(): Int {
        return userList.size
    }

    internal inner class ExampleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
        View.OnClickListener {
        init {
            itemView.setOnClickListener(this)
        }
        val name: TextView = itemView.findViewById(R.id.name)
        val gender: TextView = itemView.findViewById(R.id.gender)
        val adress: TextView = itemView.findViewById(R.id.adress)
        val age: TextView = itemView.findViewById(R.id.age)
        override fun onClick(v: View?) {
            listener.onClickItem(itemView,adapterPosition)
        }


    }
}