package com.egci428.ex_listactivitymodel

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import android.util.Log
import android.widget.ArrayAdapter
import android.content.Intent



class MainActivity : AppCompatActivity() {

    val DETAIL_REQUEST_CODE = 1001
    protected var data: ArrayList<Course>? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        data = DataProvider.getData()

        val courseArrayAdapter = ArrayAdapter<Course>(this, android.R.layout.simple_list_item_1, data)

        list.setAdapter(courseArrayAdapter)
        list.setOnItemClickListener{ adapterView, view, position, _ ->
                val course = data!!.get(position)
                displayDetail(course)
        }
    }

    private fun displayDetail(course: Course) {
        //Log.d("MainActivity","Displaying Course: "+ course.getTitle());
        val intent = Intent(this, DetailActivity::class.java)
        intent.putExtra("courseTitle", course.title)
        intent.putExtra("courseDesc", course.description)
        startActivityForResult(intent, DETAIL_REQUEST_CODE)
    }
}
