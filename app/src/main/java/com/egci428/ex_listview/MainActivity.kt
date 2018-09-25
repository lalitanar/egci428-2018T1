package com.egci428.ex_listview


import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.row_main.view.*


class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        main_listView.adapter = myCustomAdapter()

        main_listView.setOnItemClickListener { adapterView, view, position, id ->
            val item = adapterView.getItemAtPosition(position) as String
            Toast.makeText(this, "${item} $position", Toast.LENGTH_LONG).show()
        }
    }

    private class myCustomAdapter: BaseAdapter(){

        private val names = arrayListOf<String>(
                "Donald Trump", "Steve Jobs", "Tim Cook", "Mark Zuckerberg", "Barack Obama",
                "Donald Trump", "Steve Jobs", "Tim Cook", "Mark Zuckerberg", "Barack Obama",
                "Donald Trump", "Steve Jobs", "Tim Cook", "Mark Zuckerberg", "Barack Obama",
                "Donald Trump", "Steve Jobs", "Tim Cook", "Mark Zuckerberg", "Barack Obama",
                "Donald Trump", "Steve Jobs", "Tim Cook", "Mark Zuckerberg", "Barack Obama",
                "Donald Trump", "Steve Jobs", "Tim Cook", "Mark Zuckerberg", "Barack Obama",
                "Donald Trump", "Steve Jobs", "Tim Cook", "Mark Zuckerberg", "Barack Obama"
        )

        override fun getCount(): Int {
            return names.size
        }

        override fun getItemId(position: Int): Long {
            return position.toLong()
        }

        override fun getItem(position: Int): Any {
            return names[position]
        }

        override fun getView(position: Int, convertView: View?, viewGroup: ViewGroup?): View {
            val whiteColor = Color.parseColor("#FFFFFF")
            val greyColor = Color.parseColor("#E0E0E0")
            val rowMain: View

            //Checking if convertView is null, meaning we have to inflate a new row
            if (convertView == null){
                val layoutInflator = LayoutInflater.from(viewGroup!!.context)
                rowMain = layoutInflator.inflate(R.layout.row_main, viewGroup, false)
                Log.d("getView", "Calling findViewById which is expensive")
                val viewHolder = ViewHolder(rowMain.name_textView, rowMain.position_textView)
                rowMain.tag = viewHolder
            } else {
                //well, we have our row as convertView, so just set rowMain as that view
                rowMain = convertView
            }

            val viewHolder = rowMain.tag as ViewHolder
            viewHolder.nameTextView.text = names.get(position)
            viewHolder.positionTextView.text = "Row Number: $position"

            if ((position%2)==1){
                rowMain.setBackgroundColor(greyColor)
            } else {

                rowMain.setBackgroundColor(whiteColor)
            }
            return rowMain
        }

        private class ViewHolder(val nameTextView: TextView, val positionTextView: TextView)
    }
}
