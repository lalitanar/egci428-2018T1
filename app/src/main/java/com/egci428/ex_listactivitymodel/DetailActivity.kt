package com.egci428.ex_listactivitymodel

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_detail.*
import android.R.menu
import android.view.Menu


class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        val courseTitle = intent.getStringExtra("courseTitle")
        titleText.text = courseTitle

        val courseDesc = intent.getStringExtra("courseDesc")
        descriptionText.text = courseDesc
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_detail, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.getItemId()
        if (id == android.R.id.home) {
            finish()
        }
        return super.onOptionsItemSelected(item)
    }
}
