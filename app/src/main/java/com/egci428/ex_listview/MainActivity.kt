package com.egci428.ex_listview

import android.content.Context
import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ListView
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val listView = findViewById<ListView>(R.id.main_listView)
        //val redColor = Color.parseColor("#FF0000")
        //listView.setBackgroundColor(redColor)

        listView.adapter = myCustomAdapter(this)

    }

    private class myCustomAdapter(context: Context): BaseAdapter(){

        private val mContext: Context
        init {
            mContext = context
        }
        override fun getCount(): Int {
            return 5
        }

        override fun getItemId(position: Int): Long {
            return position.toLong()
        }

        override fun getItem(position: Int): Any {
            return "Test String"
        }

        override fun getView(position: Int, convertView: View?, viewGroup: ViewGroup?): View {
            val textView = TextView(mContext)
            textView.text = "Here is row for my Listview"
            return textView
        }

    }
}
