package com.egco428.ex08_file

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.io.BufferedReader
import java.io.InputStreamReader
import java.lang.Exception
import java.lang.StringBuilder

class MainActivity : AppCompatActivity() {

    private  var data: String? = null
    private var file = "mydata"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }
    fun save(view: View){
        data = editText.text.toString()
        try {
            val fOut = openFileOutput(file,Context.MODE_PRIVATE)
            fOut.write(data!!.toByteArray())
            fOut.close()
            Toast.makeText(baseContext, "file saved", Toast.LENGTH_SHORT).show()
        } catch (e: Exception){
            e.printStackTrace()
        }
    }
    fun  read(view: View){
        try {
            val fIn = openFileInput(file)
            val mfile = InputStreamReader(fIn)
            val br = BufferedReader(mfile)
            var line = br.readLine()
            val all = StringBuilder()
            while (line != null){
                all.append(line + "\n")
                line = br.readLine()
            }
            br.close()
            mfile.close()
            editText.setText(all)

            Toast.makeText(baseContext, "file read", Toast.LENGTH_SHORT).show()

        } catch (e: Exception){
            e.printStackTrace()
        }
    }
}
