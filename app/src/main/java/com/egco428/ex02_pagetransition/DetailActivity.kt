package com.egco428.ex02_pagetransition

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val bundle = intent.extras
        val inp1: String

        if(bundle != null){
            inp1 = bundle.getString("input")
            resultText.text = inp1
        }
    }

    fun backToMain(view: View){
        finish()
    }
}
