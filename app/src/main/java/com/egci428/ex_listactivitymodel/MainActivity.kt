package com.egci428.ex_listactivitymodel

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val data = DataProvider.getData()
        val iterator = data.iterator()// Duplicate the data

        val builder = StringBuilder()
        while (iterator.hasNext()) {
            val course = iterator.next()
            builder.append(course.title).append("\n")
        }

        courseListView.text = builder.toString()
    }
}
