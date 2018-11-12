package com.egco428.ex17_firebaselistview

import android.content.Context
import android.os.Message
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView

class MessageAdapter(val mContext: Context, val layoutResId: Int, val messageList: List<message>): ArrayAdapter<message>(mContext, layoutResId, messageList) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val layoutInflator: LayoutInflater = LayoutInflater.from(mContext)
        val view: View = layoutInflator.inflate(layoutResId, null)
        val msg = messageList[position]
        val msgText = view.findViewById<TextView>(R.id.msgView)
        msgText.text = msg.message

        return view
    }
}