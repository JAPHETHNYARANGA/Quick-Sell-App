package com.storesoko.quicksellecommerce.Fragments


import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*

import com.google.firebase.storage.StorageReference
import com.storesoko.quicksellecommerce.R
import com.storesoko.quicksellecommerce.adapter.MyAdapter

import com.storesoko.quicksellecommerce.models.user
import kotlinx.android.synthetic.main.fragment_sell_item.*
import kotlinx.android.synthetic.main.fragment_sell_item.view.*


class SellItemFragment : Fragment() {

    private lateinit var auth: FirebaseAuth
    private lateinit var databaseReference: DatabaseReference
    private lateinit var storageReference : StorageReference
    private lateinit var imageUri : Uri

    private lateinit var userRecyclerView: RecyclerView
    private lateinit var userArrayList: ArrayList<user>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_sell_item, container, false)



        userRecyclerView = view.findViewById(R.id.sellRecyclerView)
        userRecyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL,true)
        userRecyclerView.setHasFixedSize(true)



        userArrayList = arrayListOf()
        getUserData()



        //firebase auth
        auth = FirebaseAuth.getInstance()







        //upload button onclick
        view.upload.setOnClickListener(object:View.OnClickListener{
            override fun onClick(v: View?) {
                validation()
            }
        })



        return view
    }

    private fun getUserData() {

        val user1 = FirebaseAuth.getInstance().currentUser
        val userId = user1!!.uid

        databaseReference = FirebaseDatabase.getInstance().getReference("Items")

        databaseReference.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                if(snapshot.exists()){
                    for(userSnapshot in snapshot.children){
                        val user = userSnapshot.getValue(user::class.java)
                        userArrayList.add(user!!)
                    }

                    userRecyclerView.adapter = MyAdapter(userArrayList)
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })
    }


    private fun validation() {

        val user1 = FirebaseAuth.getInstance().currentUser
        val userId = user1!!.uid

        val uid = auth.currentUser?.uid

        val itemName = itemName.text.toString().trim()
        val itemDescription = itemDescription.text.toString().trim()
        val itemPrice = itemPrice.text.toString().trim()
        val user = user(itemName, itemDescription, itemPrice)

        if(itemName.isEmpty()){
            itName.error = "Cannot be Empty"
        }else if(itemDescription.isEmpty()){
            itemDes.error = "Cannot be empty"
        }else if(itemPrice.isEmpty()){
            itPrice.error = "Cannot be Empty"
        }else {
            itName.error = ""
            itemDes.error = ""
            itPrice.error = ""





            if( uid != null){
                databaseReference.push().setValue(user).addOnCompleteListener{
                    if(it.isSuccessful){
                        Toast.makeText(activity, "Data stored successfully", Toast.LENGTH_SHORT).show()

//                        uploadProfilePic()

                    }else{
                        Toast.makeText(activity, "Failed to store data", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }


    }



}