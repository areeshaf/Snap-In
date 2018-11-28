package com.example.hp.snapin

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.PopupMenu
import com.google.firebase.auth.FirebaseAuth

class Main2Activity : AppCompatActivity() {

    val mAuth=FirebaseAuth.getInstance()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
    }





         override fun onCreateOptionsMenu(menu: Menu?): Boolean {

            val inflator=menuInflater
            inflator.inflate(R.menu.snaps,menu)

            return super.onCreateOptionsMenu(menu)
        }


         override fun onOptionsItemSelected(item: MenuItem?): Boolean {

            if(item?.itemId==R.id.newsnap){

                val intent=Intent(this,CreateSnapActivity::class.java)
                startActivity(intent)
            }else if(item?.itemId==R.id.logout){
                mAuth.signOut()
                finish()
            }

            return super.onOptionsItemSelected(item)
        }



    override fun onBackPressed() {
        super.onBackPressed()
        mAuth.signOut()
    }
}
