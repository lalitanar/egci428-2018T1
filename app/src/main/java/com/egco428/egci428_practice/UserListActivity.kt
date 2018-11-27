package com.egco428.egci428_practice


import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.egco428.egci428_practice.model.User
import com.egco428.egci428_practice.model.UserAdapter
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_user_list.*

class UserListActivity : AppCompatActivity() {
    var url = "https://egci428practice.firebaseio.com/userProfile.json"
    lateinit var userList: MutableList<User>
    lateinit var dataReference: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_list)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        userList = mutableListOf()
        dataReference = FirebaseDatabase.getInstance().getReference("userProfile")


        dataReference.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onDataChange(p0: DataSnapshot?) {
                if (p0!!.exists()) {
                    userList.clear()
                    for (i in p0.children) {
                        val message = i.getValue(User::class.java)
                        userList.add(message!!)
                    }
                    val adapter = UserAdapter(applicationContext, R.layout.userslist, userList)
                    listView.adapter = adapter
                }
            }
        })

        listView.setOnItemClickListener { parent, view, position, id ->

            val auser = userList!!.get(position)
            displayMap(auser)
        }
    }

    private fun displayMap(ausr: User) {
        val intent = Intent(this, MapsActivity::class.java)
        intent.putExtra("selUser", ausr.username)
        intent.putExtra("selLat", ausr.latitude)
        intent.putExtra("selLon", ausr.longitude)
        startActivity(intent)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_userlist, menu)
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
