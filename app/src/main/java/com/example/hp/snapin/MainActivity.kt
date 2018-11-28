package com.example.hp.snapin

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText

class MainActivity : AppCompatActivity() {

    var emailEditText: EditText?=null
    var passwordEditText: EditText?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        emailEditText=findViewById(R.id.emailEditText)
        passwordEditText=findViewById(R.id.passwordEditText)
    }


    fun loginClicked(view : View){

    }
}
