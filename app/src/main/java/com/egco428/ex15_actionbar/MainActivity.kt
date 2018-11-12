package com.egco428.ex15_actionbar

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var flag = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)


        val item = menu!!.add("New Toast")
        item.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM)
        item.setOnMenuItemClickListener(object : MenuItem.OnMenuItemClickListener{
            override fun onMenuItemClick(item: MenuItem?): Boolean {
                Toast.makeText(this@MainActivity, "Toast New Message Here", Toast.LENGTH_LONG).show()
                return true
            }
        })

        return super.onCreateOptionsMenu(menu)
    }

    fun showToast(item: MenuItem){
        Toast.makeText(this, "Toast Message Here", Toast.LENGTH_LONG).show()
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return super.onOptionsItemSelected(item)
    }

    fun imageChangeMethod(item: MenuItem){
        if (flag){
            imageView.setImageResource(R.drawable.abc_ic_arrow_drop_right_black_24dp)
            flag = false
        } else {
            imageView.setImageResource(R.drawable.abc_ic_go_search_api_material)
            flag = true
        }
    }
}
