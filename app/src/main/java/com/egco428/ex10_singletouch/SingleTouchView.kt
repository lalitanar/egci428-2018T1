package com.egco428.ex10_singletouch

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.view.MotionEvent
import android.view.View

class SingleTouchView(context: Context): View(context) {
    private val paint = Paint()
    private val path = Path()

    private var eventX: Float = 0f
    private var eventY: Float = 0f
    private var fingerDown = false

    init {
        paint.setColor(Color.BLACK)
        paint.setStrokeWidth(6f)
        paint.setStyle(Paint.Style.STROKE)
        paint.setStrokeJoin(Paint.Join.ROUND)
        paint.setAntiAlias(true)
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas!!.drawPath(path, paint)
        if(fingerDown){
            canvas.drawCircle(eventX, eventY, 20F, paint)
        }
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        eventX = event!!.x
        eventY = event!!.y

        when(event.action) {
            MotionEvent.ACTION_DOWN -> {
                fingerDown = true
                path.moveTo(eventX,eventY)
                return true
            }
            MotionEvent.ACTION_MOVE -> path.lineTo(eventX, eventY)
            MotionEvent.ACTION_UP -> fingerDown = false
            else -> return false
        }
        invalidate()

        return true
    }
}