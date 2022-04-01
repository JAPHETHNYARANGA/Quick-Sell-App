package com.storesoko.quicksellecommerce.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.storesoko.quicksellecommerce.R
import com.storesoko.quicksellecommerce.models.user
import kotlinx.android.synthetic.main.individual_item_layout.view.*

class MyAdapter(private val userList:ArrayList<user>) : RecyclerView.Adapter<MyAdapter.MyViewHolder>(){



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.individual_item_layout, parent, false)
        return  MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
         val currentItem = userList[position]

        holder.itemName.text = currentItem.name
        holder.itemDescription.text = currentItem.description
        holder.itemPrice.text = currentItem.price
    }

    override fun getItemCount(): Int {
        return userList.size
    }


    class MyViewHolder(itemView:View) : RecyclerView.ViewHolder(itemView){
        val itemName: TextView = itemView.name
        val itemDescription: TextView = itemView.description
        val itemPrice: TextView =itemView.price


    }
}