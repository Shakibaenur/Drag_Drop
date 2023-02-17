package com.shakibaenur.dragdrop

import android.annotation.SuppressLint
import android.content.ClipData
import android.os.Bundle
import android.util.Log
import android.view.DragEvent
import android.view.DragEvent.*
import android.view.MotionEvent
import android.view.View
import android.view.View.DragShadowBuilder
import android.view.View.INVISIBLE
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.shakibaenur.dragdrop.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity(), View.OnTouchListener, View.OnDragListener {

    private lateinit var mViewBinding: ActivityMainBinding
    private val msg = "DragDrop"

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mViewBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mViewBinding.root)

        mViewBinding.ivTriangleDrag.setOnTouchListener(this)
        mViewBinding.ivTriangle.setOnDragListener(this)

    }


    override fun onTouch(v: View?, event: MotionEvent?): Boolean {
        return if (event!!.action == MotionEvent.ACTION_DOWN) {
            val data = ClipData.newPlainText("", "")
            val shadowBuilder = DragShadowBuilder(v)
            v?.startDragAndDrop(data, shadowBuilder, v, 0)
            true
        } else {
            false
        }
    }

    override fun onDrag(v: View?, event: DragEvent?): Boolean {
        when (event?.action) {
            ACTION_DRAG_STARTED -> {
                Log.d(msg, "Action is DragEvent.ACTION_DRAG_STARTED");
            }
            ACTION_DRAG_ENTERED -> {
                Log.d(msg, "Action is DragEvent.ACTION_DRAG_ENTERED")
            }
            ACTION_DRAG_EXITED -> {
                Log.d(msg, "Action is DragEvent.ACTION_DRAG_EXITED")
            }
            ACTION_DRAG_LOCATION -> {
                Log.d(msg, "Action is DragEvent.ACTION_DRAG_LOCATION")
            }
            ACTION_DRAG_ENDED -> {
                Log.d(msg, "Action is DragEvent.ACTION_DRAG_ENDED")
            }
            ACTION_DROP -> {
                Log.d(msg, "ACTION_DROP event")
                mViewBinding.ivTriangle.setImageResource(R.drawable.ic_triangle_drag)
                mViewBinding.ivTriangleDrag.visibility = INVISIBLE
                Toast.makeText(this@MainActivity,"You have matched the shape!!",Toast.LENGTH_SHORT).show()
            }

        }
        return true
    }
}