package com.egco428.ex18_fireimagestorage

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.Toast
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import kotlinx.android.synthetic.main.activity_main.*
import java.io.IOException
import java.util.*

class MainActivity : AppCompatActivity(), View.OnClickListener
{
    private var filePath: Uri? = null
    private var storage: FirebaseStorage? = null
    private var storageReference: StorageReference? = null
    private val IMAGE_REQUEST = 1234

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        storage = FirebaseStorage.getInstance()
        storageReference = storage!!.reference

        chooseBtn.setOnClickListener(this)
        uploadBtn.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        if(v === chooseBtn){
            showFileChoose()
        } else if(v === uploadBtn){
            uploadFile()
        } else {

        }
    }
    private fun showFileChoose(){
        val intent = Intent()
        intent.type = "image/*"
        intent.action = Intent.ACTION_GET_CONTENT
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), IMAGE_REQUEST)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == IMAGE_REQUEST && resultCode == Activity.RESULT_OK && data != null && data.data !=null){
            filePath = data.data
            try{
                val bitmap = MediaStore.Images.Media.getBitmap(contentResolver, filePath)
                imageView.setImageBitmap(bitmap)

            } catch (e: IOException){
                e.printStackTrace()
            }
        }
    }

    private fun uploadFile(){
        if(filePath != null){
            Toast.makeText(applicationContext, "Uploading...", Toast.LENGTH_SHORT).show()
            val imageRef = storageReference!!.child("images/"+ UUID.randomUUID().toString())
            imageRef.putFile(filePath!!)
                    .addOnSuccessListener {
                        Toast.makeText(applicationContext,"File Uploaded", Toast.LENGTH_SHORT).show()
                    }
                    .addOnFailureListener{
                        Toast.makeText(applicationContext, "Failed...", Toast.LENGTH_SHORT).show()
                    }
                    .addOnProgressListener { taskSnapshot ->
                        val progress = 100.0 + taskSnapshot.bytesTransferred/taskSnapshot.totalByteCount
                        Toast.makeText(applicationContext,"Uploaded "+progress.toInt()+"%...", Toast.LENGTH_SHORT).show()
                    }
        }
    }
}
