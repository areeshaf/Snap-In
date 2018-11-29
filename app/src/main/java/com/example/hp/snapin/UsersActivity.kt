package com.example.hp.snapin

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Adapter
import android.widget.AdapterView
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
    var keys:ArrayList<String>?= ArrayList()


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
                keys?.add(p0?.key!!)
                adapter.notifyDataSetChanged()


            }

            override fun onChildRemoved(p0: DataSnapshot) {
                 //To change body of created functions use File | Settings | File Templates.
            }

        })

        userListView?.onItemClickListener=AdapterView.OnItemClickListener { adapterView, view, i, l ->
            val snapsMap : Map<String, String>
            snapsMap= mapOf("from" to "","imageName" to "","imageUrl" to "","message" to "")
            FirebaseDatabase.getInstance().getReference().child("users").child(keys?.get(i)!!).child("snaps").push().setValue(snapsMap)
            val intent=Intent(this,Main2Activity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            startActivity(intent)
        }



    }
}
