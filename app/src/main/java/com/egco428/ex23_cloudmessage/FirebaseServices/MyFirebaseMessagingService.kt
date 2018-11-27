package com.egco428.ex23_cloudmessage.FirebaseServices

import android.util.Log
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class MyFirebaseMessagingService: FirebaseMessagingService() {
    val TAG = "FireMSG"

    override fun onMessageReceived(remoteMessage: RemoteMessage?) {
        super.onMessageReceived(remoteMessage)

        Log.d(TAG, "From: "+remoteMessage!!.from)
        if(remoteMessage.data.size>0){
            Log.d(TAG, "Message data payload: "+remoteMessage!!.data)
        }
        if(remoteMessage.notification != null){
            Log.d(TAG, "Mess Notification Body: "+remoteMessage.notification!!.body)
        }
    }
}