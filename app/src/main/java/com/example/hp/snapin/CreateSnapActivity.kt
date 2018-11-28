package com.example.hp.snapin

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.storage.FirebaseStorage
import java.io.ByteArrayOutputStream
import java.util.*

class CreateSnapActivity : AppCompatActivity() {

    val imageName=UUID.randomUUID().toString()+".jpg"
    var createSnapImageView:ImageView?=null
    var cameraButton:Button?=null
    var messageTextView:TextView?=null
    var nextgoButton:Button?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_snap)
        createSnapImageView=findViewById(R.id.imageView)
        cameraButton=findViewById(R.id.cameraButton)
        messageTextView=findViewById(R.id.messageeditText)
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

        // Get the data from an ImageView as bytes
        createSnapImageView?.isDrawingCacheEnabled = true
        createSnapImageView?.buildDrawingCache()
        val bitmap = (createSnapImageView?.drawable as BitmapDrawable).bitmap
        val baos = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos)
        val data = baos.toByteArray()

        var uploadTask =FirebaseStorage.getInstance().getReference().child("images").child(imageName).putBytes(data)
        uploadTask.addOnFailureListener {
            // Handle unsuccessful uploads
            Toast.makeText(this,"Upload Fails!",Toast.LENGTH_SHORT).show()
        }.addOnSuccessListener {
            Log.i("Image","uploaded")
            val intent=Intent(this,UsersActivity::class.java)
            startActivity(intent)
        }
    }

}
