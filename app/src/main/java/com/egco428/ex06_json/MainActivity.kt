package com.egco428.ex06_json

import android.os.AsyncTask
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        submitBtn.setOnClickListener {
            var asyncTask = object: AsyncTask<String, String, String>(){

                override fun onPreExecute() {
                    Toast.makeText(this@MainActivity,"Please wait...", Toast.LENGTH_SHORT).show()
                }

                override fun doInBackground(vararg p0: String?): String {
                    val helper = Helper()
                    return helper.getHTTPData("https://talaikis.com/api/quotes/random/")
                }

                override fun onPostExecute(result: String?) {
                    val quotetext = Gson().fromJson(result, Quote::class.java)
                    textView.text = quotetext.quote
                    if (textView.visibility == View.INVISIBLE)
                        textView.visibility = View.VISIBLE
                }


            }
            asyncTask.execute()
        }
    }
}
