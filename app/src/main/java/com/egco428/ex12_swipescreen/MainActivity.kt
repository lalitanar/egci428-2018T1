package com.egco428.ex12_swipescreen

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.view.GestureDetectorCompat
import android.view.GestureDetector
import android.view.MotionEvent
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), GestureDetector.OnGestureListener, GestureDetector.OnDoubleTapListener {

    private val MIN_SWIPE_DISTANCE_X = 100
    private val MIN_SWIPE_DISTANCE_Y = 100

    private val MAX_SWIPE_DISTANCE_X = 1000
    private val MAX_SWIPE_DISTANCE_Y = 100

    private  var gestureDetector: GestureDetectorCompat? = null



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        this.gestureDetector = GestureDetectorCompat(this, this)
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        this.gestureDetector!!.onTouchEvent(event)
        return super.onTouchEvent(event)
    }

    override fun onDoubleTap(e: MotionEvent?): Boolean {
        statusMessage.text = "OnDoubleTap"
        return true
    }

    override fun onDoubleTapEvent(e: MotionEvent?): Boolean {
        statusMessage.text = "OnDoubleTapEvent"
        return true
    }

    override fun onSingleTapConfirmed(e: MotionEvent?): Boolean {
        statusMessage.text = "onSingleTapConfirmed"
        return false
    }

    override fun onDown(e: MotionEvent?): Boolean {
        statusMessage.text = "onDown"
        return true
    }

    override fun onScroll(e1: MotionEvent?, e2: MotionEvent?, distanceX: Float, distanceY: Float): Boolean {
        statusMessage.text = "onScoll"
        return true
    }

    override fun onLongPress(e: MotionEvent?) {
        statusMessage.text = "onLongPress"
    }

    override fun onFling(e1: MotionEvent?, e2: MotionEvent?, velocityX: Float, velocityY: Float): Boolean {
        //statusMessage.text = "onFling"
        val deltaX = e1!!.x - e2!!.x
        val deltaY = e1!!.y - e2!!.y
        val deltaXAbs = Math.abs(deltaX)
        val deltaYAbs = Math.abs(deltaY)

        if((deltaXAbs>= MIN_SWIPE_DISTANCE_X)&& (deltaXAbs<=MAX_SWIPE_DISTANCE_X))
        {
            if (deltaX>0){
                statusMessage.text = "Swipe to Left"
            } else {
                statusMessage.text = "Swipe to Right"
            }
        }
        if((deltaYAbs>= MIN_SWIPE_DISTANCE_Y)&& (deltaYAbs<=MAX_SWIPE_DISTANCE_Y))
        {
            if (deltaY>0){
                statusMessage.text = "Swipe UP"
            } else {
                statusMessage.text = "Swipe DOWN"
            }
        }
        return true
    }

    override fun onShowPress(e: MotionEvent?) {
        statusMessage.text = "onShowPress"
    }

    override fun onSingleTapUp(e: MotionEvent?): Boolean {
        statusMessage.text = "onSingleTapUp"
        return true
    }
}
