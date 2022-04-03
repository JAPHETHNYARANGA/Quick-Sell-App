package com.storesoko.quicksellecommerce.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.storesoko.quicksellecommerce.R
import com.storesoko.quicksellecommerce.models.user
import kotlinx.android.synthetic.main.individual_delete_adapter.view.*
import kotlinx.android.synthetic.main.individual_item_layout.view.*
import kotlinx.android.synthetic.main.individual_item_layout.view.description
import kotlinx.android.synthetic.main.individual_item_layout.view.name
import kotlinx.android.synthetic.main.individual_item_layout.view.price

class deleteAdapter (private val userList1:ArrayList<user>) : RecyclerView.Adapter<deleteAdapter.MyViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.individual_delete_adapter, parent, false)
        return  MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = userList1[position]

        holder.itemName.text = currentItem.name
        holder.itemDescription.text = currentItem.description
        holder.itemPrice.text = currentItem.price
    }

    override fun getItemCount(): Int {
        return userList1.size
    }


    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val itemName: TextView = itemView.deletename
        val itemDescription: TextView = itemView.deletedescription
        val itemPrice: TextView =itemView.deleteprice


    }
}