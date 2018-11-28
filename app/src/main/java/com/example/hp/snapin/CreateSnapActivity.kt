package com.example.hp.snapin

import android.content.Intent
import android.graphics.Bitmap
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.Button
import android.widget.ImageView

class CreateSnapActivity : AppCompatActivity() {

    var createSnapImageView:ImageView?=null
    var cameraButton:Button?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_snap)
        createSnapImageView=findViewById(R.id.imageView)
        cameraButton=findViewById(R.id.cameraButton)
        cameraButton?.setOnClickListener {
            val intent= Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            startActivityForResult(intent,0)
        }


    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        var bitmap:Bitmap?= data?.extras?.get("data") as Bitmap?
        createSnapImageView?.setImageBitmap(bitmap)

    }

    fun nextButton(view: View){

    }

}
