package com.example.hp.snapin

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import com.google.firebase.auth.FirebaseAuth
import android.widget.Toast
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.AuthResult
import com.google.android.gms.tasks.Task
import android.support.annotation.NonNull
import com.google.android.gms.tasks.OnCompleteListener
import android.R.attr.password
import android.content.Intent
import com.google.firebase.database.FirebaseDatabase


class MainActivity : AppCompatActivity() {

    var emailEditText: EditText?=null
    var passwordEditText: EditText?=null

    val mAuth=FirebaseAuth.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        emailEditText=findViewById(R.id.emailEditText)
        passwordEditText=findViewById(R.id.passwordEditText)

        if (mAuth.currentUser!=null){
            login()
        }


    }

    fun login(){

        //move to next activity
        val intent=Intent(this,Main2Activity::class.java)
        val newIntent = "new intent";
        startActivity(intent)
    }

    fun loginClicked(view : View){

        //check in the user in not logged in or sign up new user
        mAuth.signInWithEmailAndPassword(emailEditText?.text.toString(), passwordEditText?.text.toString())
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    login()
                } else {
                    mAuth.createUserWithEmailAndPassword(emailEditText?.text.toString(), passwordEditText?.text.toString())
                        .addOnCompleteListener(this){
                                task ->
                            if(task.isSuccessful){
                                //add to database
                                FirebaseDatabase.getInstance().getReference().child("users").child(task.result.user.uid).child("email").setValue(emailEditText?.text.toString())
                                login()
                            }else{
                                Toast.makeText(this,"Login Failed! Try again.",Toast.LENGTH_SHORT).show()
                            }
                        }
                }

            }
    }
}
