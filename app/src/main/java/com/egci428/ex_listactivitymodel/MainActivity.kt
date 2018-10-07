package com.egci428.ex_listactivitymodel

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import android.content.Intent
import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import kotlinx.android.synthetic.main.course_item.view.*


class MainActivity : AppCompatActivity() {

    val DETAIL_REQUEST_CODE = 1001
    protected var data: ArrayList<Course>? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        data = DataProvider.getData()
        val courseArrayAdapter = CourseArrayAdapter(this, 0, data!!)

        list.setAdapter(courseArrayAdapter)
        list.setOnItemClickListener{ adapterView, view, position, _ ->
                val course = data!!.get(position)
                displayDetail(course, position)
        }
    }

    private fun displayDetail(course: Course, impos: Int) {
        val intent = Intent(this, DetailActivity::class.java)
        intent.putExtra("courseTitle", course.title)
        intent.putExtra("courseDesc", course.description)
        intent.putExtra("courseNumber", course.courseNumber)
        intent.putExtra("courseCredits", course.credits)
        intent.putExtra("imagepos", impos)
        startActivityForResult(intent, DETAIL_REQUEST_CODE)
    }

    private class CourseArrayAdapter(var context: Context, resource: Int, var objects: ArrayList<Course>) : BaseAdapter() {

        override fun getCount(): Int {
            return objects.size
        }

        override fun getItem(position: Int): Any {
            return objects[position]
        }

        override fun getItemId(position: Int): Long {
            return position.toLong()
        }

        override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
            val whiteColor = Color.parseColor("#FFFFFF")
            val greyColor = Color.parseColor("#F5F5F5")

            val course = objects[position]
            val view: View

            if(convertView==null){
                val layoutInflater = LayoutInflater.from(parent!!.context)
                view = layoutInflater.inflate(R.layout.course_item, parent, false)
                val viewHolder = ViewHolder(view.titleText, view.imageCourse)
                view.tag = viewHolder

            } else {
                view = convertView
            }

            val viewHolder = view.tag as ViewHolder
            viewHolder.titleTextView.text = course.title

            val imgpos = position%3+1
            val res = context.resources.getIdentifier("image1010" + imgpos, "drawable", context.packageName)
            viewHolder.imageCourse.setImageResource(res)

            if ((position%2)==1){
                view.setBackgroundColor(greyColor)
            } else {

                view.setBackgroundColor(whiteColor)
            }
            return view
        }
        private class ViewHolder (val titleTextView: TextView, val imageCourse: ImageView)
    }
}
