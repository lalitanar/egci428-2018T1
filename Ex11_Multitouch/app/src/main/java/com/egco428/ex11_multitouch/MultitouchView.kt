package com.egco428.ex11_multitouch

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.PointF
import android.util.SparseArray
import android.view.MotionEvent
import android.view.View

class MultitouchView(context: Context): View(context) {

    private  var mPaint: Paint? = null
    private val colors = intArrayOf(Color.BLUE, Color.GREEN, Color.MAGENTA, Color.BLACK, Color.GRAY, Color.RED, Color.CYAN, Color.YELLOW)

    private var textPaint: Paint? = null

    private var activePointers: SparseArray<PointF>? = null
    val Size = 60f

    init{

        activePointers = SparseArray<PointF>()
        mPaint = Paint()
        mPaint = Paint(Paint.ANTI_ALIAS_FLAG)
        //mPaint!!.isAntiAlias = true
        mPaint!!.color = Color.BLUE
        mPaint!!.style = Paint.Style.FILL_AND_STROKE

        textPaint =  Paint(Paint.ANTI_ALIAS_FLAG)
        textPaint!!.setTextSize(20F)
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        val size = activePointers!!.size()
        var i = 0
        while(i< size){
            val point = activePointers!!.valueAt(i)
            if(point != null) {
                mPaint!!.color = colors[i%7]
            }
            canvas!!.drawCircle(point.x, point.y, Size, mPaint)
            i++
        }
        canvas!!.drawText("Total Pointers: "+activePointers!!.size(), 10f, 40f, textPaint)


    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        val pointIndex = event!!.actionIndex
        val pointerId = event.getPointerId(pointIndex)
        val maskedAction = event.actionMasked

        when(maskedAction){
            MotionEvent.ACTION_DOWN, MotionEvent.ACTION_POINTER_DOWN ->{
                val f = PointF()
                f.x = event.getX(pointIndex)
                f.y = event.getY(pointIndex)
                activePointers!!.put(pointerId, f)
            }
            MotionEvent.ACTION_MOVE ->{
                val size = event.pointerCount
                var i = 0
                while(i<size){
                    val point = activePointers!!.get(event.getPointerId(i))
                    if(point != null){
                        point.x = event.getX(i)
                        point.y = event.getY(i)
                    }
                    i++
                }
            }
            MotionEvent.ACTION_UP, MotionEvent.ACTION_POINTER_UP, MotionEvent.ACTION_CANCEL ->{
                activePointers!!.remove(pointerId)
            }
        }
        invalidate()
        return true
    }
}