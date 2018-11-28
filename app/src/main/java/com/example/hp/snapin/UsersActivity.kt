package com.example.hp.snapin

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import com.google.firebase.database.ChildEventListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import java.util.ArrayList

class UsersActivity : AppCompatActivity() {

    var userListView:ListView?=null
    var emails:ArrayList<String>?= ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_users)

        userListView=findViewById(R.id.listView)

        val adapter=ArrayAdapter(this,android.R.layout.simple_list_item_1,emails)
        userListView?.adapter=adapter

        FirebaseDatabase.getInstance().getReference().child("users").addChildEventListener(object : ChildEventListener{
            override fun onCancelled(p0: DatabaseError) {
                 //To change body of created functions use File | Settings | File Templates.
            }

            override fun onChildMoved(p0: DataSnapshot, p1: String?) {
                 //To change body of created functions use File | Settings | File Templates.
            }

            override fun onChildChanged(p0: DataSnapshot, p1: String?) {
                 //To change body of created functions use File | Settings | File Templates.
            }

            override fun onChildAdded(p0: DataSnapshot, p1: String?) {
                 //To change body of created functions use File | Settings | File Templates.
                val email=p0.child("email").value as String
                emails?.add(email)
                adapter.notifyDataSetChanged()


            }

            override fun onChildRemoved(p0: DataSnapshot) {
                 //To change body of created functions use File | Settings | File Templates.
            }

        })

    }
}
