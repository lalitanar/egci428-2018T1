package com.egco428.ex14_photo

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import java.io.FileNotFoundException
import java.io.IOException
import java.io.InputStream

class MainActivity : AppCompatActivity() {

    private  val REQUESTCODE = 1
    private var bitmap: Bitmap? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
    fun onClick(view:View){
        val intent = Intent()
        intent.type = "image/*"
        intent.action = Intent.ACTION_GET_CONTENT
        intent.addCategory(Intent.CATEGORY_OPENABLE)
        startActivityForResult(intent, REQUESTCODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        var stream: InputStream? = null
        if(requestCode == REQUESTCODE && resultCode == Activity.RESULT_OK){
            try {
                if(bitmap !=null){
                    bitmap!!.recycle()
                }
                stream = contentResolver.openInputStream(data!!.data)
                bitmap = BitmapFactory.decodeStream(stream)
                imageView.setImageBitmap(bitmap)

            } catch (e:FileNotFoundException){
                e.printStackTrace()
            } finally {
                if(stream!=null){
                    try {
                        stream.close()
                    } catch (e:IOException){
                        e.printStackTrace()
                    }

                }
            }
        }
    }
}
