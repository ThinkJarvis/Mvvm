package com.kotlin.mvvm.presentation.view.widget

import android.content.Context
import android.util.AttributeSet
import com.kotlin.mvvm.R
import com.kotlin.mvvm.presentation.view.fragment.Tab1Fragment
import com.kotlin.mvvm.presentation.view.fragment.Tab2Fragment

class UserTabLayout : CustomTabLayout {
    override fun setUpTabs() {
        addCustomTab(R.string.tab_1, Tab1Fragment::class.java.name)
        addCustomTab(R.string.tab_2, Tab2Fragment::class.java.name)
    }


    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)


}