package com.kotlin.mvvm.presentation.view.widget

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.annotation.StringRes
import androidx.constraintlayout.widget.ConstraintLayout
import com.kotlin.mvvm.R
import com.kotlin.walletservice.platform.MaterialTabLayout
import kotlinx.android.synthetic.main.layout_custom_tab.view.*


abstract class CustomTabLayout : MaterialTabLayout {


    override fun addTabs() {
        for (i in 0 until tabs.size) {
            var customTabView = CustomTabView(context)
            customTabView.setText(tabs[i].tagId)
            addTab(newTab().setCustomView(customTabView))
        }
    }

    override fun onTabSelected(tab: Tab) {
        super.onTabSelected(tab)
        (tab.customView as CustomTabView).setTextSelectedStyle()
    }

    override fun onTabUnselected(tab: Tab) {
        super.onTabUnselected(tab)
        (tab.customView as CustomTabView).setTextUnSelectedStyle()
    }

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)


    private class CustomTabView : ConstraintLayout {

        private val layoutId = R.layout.layout_custom_tab

        constructor(context: Context) : super(context) {
            LayoutInflater.from(context).inflate(layoutId, this)
        }

        fun setText(@StringRes resId: Int) {
            title.setText(resId)
            setTextUnSelectedStyle()
        }

        fun setTextSelectedStyle() {
            title.setTextColor(Color.LTGRAY)
        }

        fun setTextUnSelectedStyle() {
            title.setTextColor(Color.BLACK)
        }

    }

}