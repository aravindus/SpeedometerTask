package com.aravind.speedometer

import android.os.Bundle
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {

    private lateinit var gestureDetector: GestureDetector
    private lateinit var speedometer: SpeedometerView
    private lateinit var tachometerView: TachometerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

      /*  val windowInsetsController =
            WindowCompat.getInsetsController(window, window.decorView)
        windowInsetsController.hide(WindowInsetsCompat.Type.systemBars())*/

        // Set up touch listeners for the views
        val rootView: ViewGroup = findViewById(R.id.rootView)
        speedometer = findViewById(R.id.speedometer)
        tachometerView = findViewById(R.id.tachometerView)

        speedometer.setSpeed(95, 1000L)
        tachometerView.setSpeed(50, 5000L)

        gestureDetector = GestureDetector(this, object : GestureDetector.SimpleOnGestureListener() {
            override fun onScroll(
                e1: MotionEvent,
                e2: MotionEvent,
                distanceX: Float,
                distanceY: Float
            ): Boolean {
                if (Math.abs(distanceX) > Math.abs(distanceY)) {
                    // Horizontal scroll detected, transition views.
                    if (distanceX > 0) {
                        showSpeedometer()
                    } else {
                        showTachometer()
                    }
                }
                return true
            }
        })
        rootView.setOnTouchListener { _, event ->
            gestureDetector.onTouchEvent(event)
        }

        // Initialize the initial view
        showSpeedometer()
    }

    private fun showSpeedometer() {
        speedometer.visibility = View.VISIBLE
        tachometerView.visibility = View.GONE
    }

    private fun showTachometer() {
        speedometer.visibility = View.GONE
        tachometerView.visibility = View.VISIBLE

    }
}