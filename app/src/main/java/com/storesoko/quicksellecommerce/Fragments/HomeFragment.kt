package com.storesoko.quicksellecommerce.Fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.storesoko.quicksellecommerce.R
import com.storesoko.quicksellecommerce.adapter.MyAdapter
import com.storesoko.quicksellecommerce.models.user


class HomeFragment : Fragment() {
    private lateinit var userRecyclerView: RecyclerView
    private lateinit var userArrayList: ArrayList<user>
    private lateinit var auth: FirebaseAuth
    private lateinit var databaseReference: DatabaseReference

    private lateinit var myAdapter: MyAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        userRecyclerView = view.findViewById(R.id.homeRecyclerView)
        userRecyclerView.layoutManager = GridLayoutManager(context, 3, LinearLayoutManager.VERTICAL, false)
        userRecyclerView.setHasFixedSize(true)




        userArrayList = arrayListOf()
        getUserData()



        //firebase auth
        auth = FirebaseAuth.getInstance()


        //firebase
        databaseReference = FirebaseDatabase.getInstance().getReference("Items")

        return view
    }

    private fun getUserData() {
        databaseReference = FirebaseDatabase.getInstance().getReference("Items")

        databaseReference.addValueEventListener(object : ValueEventListener {
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


}