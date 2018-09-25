package com.egci428.ex_listview

import android.content.Context
import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ListView
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.row_main.view.*

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //val redColor = Color.parseColor("#FF0000")
        //listView.setBackgroundColor(redColor)

        main_listView.adapter = myCustomAdapter(this)



    }

    private class myCustomAdapter(context: Context): BaseAdapter(){


        private val mContext: Context
        private val names = arrayListOf<String>(
                "Donald Trump", "Steve Jobs", "Tim Cook", "Mark Zuckerberg", "Barack Obama"
        )

        init {
            mContext = context
        }
        override fun getCount(): Int {
            return names.size
        }

        override fun getItemId(position: Int): Long {
            return position.toLong()
        }

        override fun getItem(position: Int): Any {
            return "Test String"
        }



        override fun getView(position: Int, convertView: View?, viewGroup: ViewGroup?): View {
            val whiteColor = Color.parseColor("#FFFFFF")
            val greyColor = Color.parseColor("#E0E0E0")
            val layoutInflator = LayoutInflater.from(mContext)
            val rowMain = layoutInflator.inflate(R.layout.row_main, viewGroup, false)


            rowMain.name_textView.text = names.get(position)
            rowMain.position_textView.text = "Row Number: $position"

            if ((position%2)==1){
                rowMain.setBackgroundColor(greyColor)
            } else {

                rowMain.setBackgroundColor(whiteColor)
            }

            return rowMain
        }

    }
}
