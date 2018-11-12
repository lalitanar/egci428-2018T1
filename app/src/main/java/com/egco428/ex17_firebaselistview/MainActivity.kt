package com.egco428.ex17_firebaselistview

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    lateinit var dataReference : DatabaseReference
    lateinit var msgList: MutableList<message>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        val database = FirebaseDatabase.getInstance()
//        val myRef = database.getReference("message")
//
//        myRef.setValue("Hello, World!")

        msgList = mutableListOf()
        dataReference = FirebaseDatabase.getInstance().getReference("dataMessage")
        saveBtn.setOnClickListener {
            saveData()
        }

        dataReference.addValueEventListener(object: ValueEventListener{
            override fun onCancelled(p0: DatabaseError?) {

            }

            override fun onDataChange(p0: DataSnapshot?) {
                if(p0!!.exists()){
                    msgList.clear()
                    for (i in p0.children){
                        val message = i.getValue(message::class.java)
                        msgList.add(message!!)
                    }
                    val adapter = MessageAdapter(applicationContext, R.layout.mesaages, msgList)
                    listView.adapter = adapter
                }
            }
        })
    }
    private  fun saveData(){
        val msg = editText.text.toString()
        if(msg.isEmpty()){
            editText.error = "Please enter a message"
            return
        }
        val messageId = dataReference.push().key
        val messageData = message(messageId, msg, ratingBar.rating.toInt())
        dataReference.child(messageId).setValue(messageData).addOnCompleteListener{
            Toast.makeText(applicationContext, "Save successfully", Toast.LENGTH_SHORT).show()
        }

    }
}
