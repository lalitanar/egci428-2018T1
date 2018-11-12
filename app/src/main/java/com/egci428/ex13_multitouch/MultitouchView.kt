package com.egci428.ex13_multitouch

import android.R.attr.y
import android.R.attr.x
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.PointF
import android.view.MotionEvent
import android.graphics.Paint.ANTI_ALIAS_FLAG
import android.util.SparseArray
import android.graphics.Color.LTGRAY
import android.graphics.Color.DKGRAY
import android.graphics.Paint
import android.view.View
import android.content.Context


/**
 * Created by lalita on 8/14/2017 AD.
 */
class MultitouchView(context: Context) : View(context) {

    private var mActivePointers: SparseArray<PointF>? = null
    private var mPaint: Paint? = null
    private val colors = intArrayOf(Color.BLUE, Color.GREEN, Color.MAGENTA, Color.BLACK, Color.CYAN, Color.GRAY, Color.RED, Color.DKGRAY, Color.LTGRAY, Color.YELLOW)

    private var textPaint: Paint? = null


    init {
        initView()
    }

    private fun initView() {
        mActivePointers = SparseArray<PointF>()
        mPaint = Paint(Paint.ANTI_ALIAS_FLAG)
        // set painter color to a color you like
        mPaint!!.setColor(Color.BLUE)
        mPaint!!.setStyle(Paint.Style.FILL_AND_STROKE)
        textPaint = Paint(Paint.ANTI_ALIAS_FLAG)
        textPaint!!.setTextSize(20F)
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {

        // get pointer index from the event object
        val pointerIndex = event.actionIndex

        // get pointer ID
        val pointerId = event.getPointerId(pointerIndex)

        // get masked (not specific to a pointer) action
        val maskedAction = event.actionMasked

        when (maskedAction) {

            MotionEvent.ACTION_DOWN, MotionEvent.ACTION_POINTER_DOWN -> {
                // We have a new pointer. Lets add it to the list of pointers

                val f = PointF()
                f.x = event.getX(pointerIndex)
                f.y = event.getY(pointerIndex)
                mActivePointers!!.put(pointerId, f)
            }
            MotionEvent.ACTION_MOVE -> { // a pointer was moved
                val size = event.pointerCount
                var i = 0
                while (i < size) {
                    val point = mActivePointers!!.get(event.getPointerId(i))
                    if (point != null) {
                        point.x = event.getX(i)
                        point.y = event.getY(i)
                    }
                    i++
                }
            }
            MotionEvent.ACTION_UP, MotionEvent.ACTION_POINTER_UP, MotionEvent.ACTION_CANCEL -> {
                mActivePointers!!.remove(pointerId)
            }
        }
        invalidate()

        return true
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        // draw all pointers
        val size = mActivePointers!!.size()
        var i = 0
        while (i < size) {
            val point = mActivePointers!!.valueAt(i)
            if (point != null)
                mPaint!!.setColor(colors[i % 9])
            canvas.drawCircle(point!!.x, point.y, SIZE.toFloat(), mPaint)
            i++
        }
        canvas.drawText("Total pointers: " + mActivePointers!!.size(), 10F, 40F, textPaint)
    }

    companion object {

        private val SIZE = 60
    }

}