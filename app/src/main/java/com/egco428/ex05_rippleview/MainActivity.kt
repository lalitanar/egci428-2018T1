package com.egco428.ex05_rippleview

import android.graphics.BitmapFactory
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        glView.run {
            addBackgroundImages(listOf(BitmapFactory.decodeResource(resources, R.drawable.sithakarn),BitmapFactory.decodeResource(resources, R.drawable.sithakarn2)))
            setRippleOffset(0.01f)
            setFadeInterval(2000)
            startCrossFadeAnimation()
        }
    }
}
