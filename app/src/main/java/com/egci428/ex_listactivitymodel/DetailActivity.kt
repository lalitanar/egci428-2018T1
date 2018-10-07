package com.egci428.ex_listactivitymodel

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_detail.*
import android.view.Menu
import java.text.NumberFormat


class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        val courseTitle = intent.getStringExtra("courseTitle")
        titleText.text = courseTitle

        val courseDesc = intent.getStringExtra("courseDesc")
        descriptionText.text = courseDesc

        val courseNo = intent.getIntExtra("courseNumber", 10101)
        courseNoText.text = "Course No #: " + courseNo

        val credits = intent.getDoubleExtra("courseCredits", 1.0)
        val formatter = NumberFormat.getInstance()
        formatter.setMinimumFractionDigits(1)
        creditsText.text = "Credits: " + formatter.format(credits)

        val imgpos = intent.getIntExtra("imagepos", 0)
        val modpos = ((imgpos%3)+1).toString()

        val res = resources.getIdentifier("image1010" + modpos, "drawable", packageName)
        imageCourse.setImageResource(res)
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
