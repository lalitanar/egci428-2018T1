package com.egco428.egci428_practice

import android.content.Context
import android.content.Intent
import android.os.AsyncTask
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.egco428.egci428_practice.Helper.HTTPHelper
import com.egco428.egci428_practice.model.User
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_main.*
import android.support.v4.content.ContextCompat.startActivity
import kotlinx.android.synthetic.main.activity_sign_up.*


class MainActivity : AppCompatActivity() {
    var url = "https://egci428practice.firebaseio.com/userProfile/"
    var uname: String? = null
    var pname: String? = null
    var userprofile: User? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        signUpBtn.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
        }
        signInBtn.setOnClickListener {
            uname = userText.text.toString()
            pname = passText.text.toString()
            if (!uname.isNullOrEmpty() && !pname.isNullOrEmpty()) {
                var asyncTask = object : AsyncTask<String, String, String>() {

                    override fun onPreExecute() {
                        Toast.makeText(this@MainActivity, "Please wait...", Toast.LENGTH_SHORT).show()
                    }

                    override fun doInBackground(vararg p0: String?): String {
                        val helper = HTTPHelper()
                        Log.d("Uname", url + uname + ".json")
                        return helper.getHTTPData(url + uname + ".json")
                    }

                    override fun onPostExecute(result: String?) {
                        if (result != "null") {

                            userprofile = Gson().fromJson(result, User::class.java)
                            if (userprofile!!.password == pname) {

                                val intent = Intent(this@MainActivity, UserListActivity::class.java)

                                applicationContext.startActivity(intent)
                            } else {
                                Toast.makeText(this@MainActivity, "Username or Password is not matched", Toast.LENGTH_SHORT).show()
                            }

                        } else {
                            Toast.makeText(this@MainActivity, "Username or Password is not matched", Toast.LENGTH_SHORT).show()
                        }
                    }

                }
                asyncTask.execute()


            }
        }
        cancelBtn.setOnClickListener {
            userText.text = null
            passText.text = null


        }
    }

}
