package com.egco428.ex23_cloudmessage.FirebaseServices

import android.util.Log
import com.google.firebase.iid.FirebaseInstanceId
import com.google.firebase.iid.FirebaseInstanceIdService

class FirebaseIdService: FirebaseInstanceIdService() {

    override fun onTokenRefresh() {
        super.onTokenRefresh()

        val refreshToken = FirebaseInstanceId.getInstance().token
        Log.d("FireMsg", "Refreshed Token: "+ refreshToken)
    }
}