package com.starbucks.peru.core.utils

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.view.MotionEvent
import android.view.View
import android.view.animation.Animation
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton

//@SuppressLint("ClickableViewAccessibility")
fun ExtendedFloatingActionButton.animateOnPress() {
    this.setOnTouchListener(object: View.OnTouchListener{
        override fun onTouch(view: View?, event: MotionEvent?): Boolean {
            when(event?.action) {
                MotionEvent.ACTION_DOWN -> {
                    val scaleX = ObjectAnimator.ofFloat(view,
                        "scaleX", 0.9f)
                    val scaleY = ObjectAnimator.ofFloat(view,
                        "scaleY", 0.9f)
                    scaleX.duration = 200
                    scaleY.duration = 200
                    val scaleDown = AnimatorSet()
                    scaleDown.play(scaleX).with(scaleY)
                    scaleDown.start()
                    return true
                }
                MotionEvent.ACTION_UP -> {
                    val scaleX = ObjectAnimator.ofFloat(view,
                        "scaleX", 1f)
                    val scaleY = ObjectAnimator.ofFloat(view,
                        "scaleY", 1f)
                    scaleX.duration = 200
                    scaleY.duration = 200
                    val scaleUp = AnimatorSet()
                    scaleUp.play(scaleX).with(scaleY)
                    scaleUp.start()

                    performClick()
                    return true
                }
                else -> return false
            }
        }
    })
}

fun getRotateAnimation(view: View): ObjectAnimator{
    val rotateAnim = ObjectAnimator.ofFloat(view, View.ROTATION, 0.0f, 360.0f)
    rotateAnim.duration = 1000
    rotateAnim.repeatCount = Animation.INFINITE

    return rotateAnim
}