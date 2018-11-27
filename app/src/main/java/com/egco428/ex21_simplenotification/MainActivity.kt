package com.egco428.ex21_simplenotification

import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.NotificationCompat
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        notifyBtn.setOnClickListener {
            val intent = Intent(this@MainActivity,ResultActivity::class.java)
            val pIntent = PendingIntent.getActivity(this@MainActivity, System.currentTimeMillis().toInt(), intent, 0)


            val callAction = NotificationCompat.Action.Builder(R.drawable.ic_call, "Call", pIntent).build()


            val builder = NotificationCompat.Builder(this@MainActivity)
            builder.setSmallIcon(R.drawable.ic_message)
            builder.setContentTitle("New message from test@gmail.com")
            builder.setContentText("This is a test message sending from android")
            builder.addAction(callAction)
            builder.setWhen(System.currentTimeMillis()+5000)

            val notifyManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

            notifyManager.notify(0, builder.build())

        }
    }
}
