package com.kotlin.walletservice.platform

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import androidx.viewpager.widget.ViewPager
import com.kotlin.walletservice.R

class MaterialViewPager : ViewPager {

    private var scrollable = false

    constructor(context: Context) : this(context, null)

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        var a = context.obtainStyledAttributes(attrs, R.styleable.MaterialViewPager)
        scrollable = a.getBoolean(R.styleable.MaterialViewPager_scrollable, true)
        a.recycle()
    }

    override fun onInterceptTouchEvent(ev: MotionEvent?) = scrollable

    override fun onTouchEvent(ev: MotionEvent) = scrollable
}