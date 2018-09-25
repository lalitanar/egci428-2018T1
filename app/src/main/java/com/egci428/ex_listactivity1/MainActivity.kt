package com.egci428.ex_listactivity1

import android.app.ListActivity
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter



class MainActivity : ListActivity() {
    var mobileList = arrayOf("Samsung", "Apple", "Microsoft", "LG", "HTC", "Wiko", "Asus")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, mobileList)
        listAdapter = adapter

    }
}
