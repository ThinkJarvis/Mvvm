package com.kotlin.walletservice.platform

import android.content.Context
import android.util.AttributeSet
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.google.android.material.tabs.TabLayout

abstract class MaterialTabLayout : TabLayout, TabLayout.OnTabSelectedListener {

    protected var tabs = mutableListOf<ViewPagerInfo>()

    private var viewPager: MaterialViewPager? = null

    private var fragmentManager: FragmentManager? = null


    private var tabLayoutOnPageChangeListener: TabLayoutOnPageChangeListener? = null

    constructor(context: Context) : this(context, null)

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)


    override fun onTabReselected(tab: Tab) {
    }

    override fun onTabUnselected(tab: Tab) {
    }

    override fun onTabSelected(tab: Tab) {
        viewPager?.setCurrentItem(tab.position, false)
    }

    fun setupWithViewPager(
        viewPager: MaterialViewPager, fragmentManager: FragmentManager
    ) {
        this.viewPager = viewPager
        this.fragmentManager = fragmentManager
        setUpTabs()

        addOnTabSelectedListener(this)
        tabLayoutOnPageChangeListener = TabLayoutOnPageChangeListener(this)
        viewPager.run {
            addOnPageChangeListener(tabLayoutOnPageChangeListener!!)
            adapter = TabFragmentStatePagerAdapter(fragmentManager, tabs)
        }
        addTabs()
    }

    fun addCustomTab(tagId: Int, fragmentClassName: String) {
        val viewPageInfo = ViewPagerInfo(
            tagId,
            fragmentManager?.fragmentFactory!!.instantiate(
                ClassLoader.getSystemClassLoader(),
                fragmentClassName
            )
        )
        tabs.add(viewPageInfo)
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        if (tabLayoutOnPageChangeListener != null) {
            viewPager?.removeOnPageChangeListener(tabLayoutOnPageChangeListener!!)
        }
        removeOnTabSelectedListener(this)
    }

    abstract fun setUpTabs()

    abstract fun addTabs()

    private class TabFragmentStatePagerAdapter : FragmentPagerAdapter {

        var viewPagers: MutableList<ViewPagerInfo> = mutableListOf()

        constructor(fm: FragmentManager, viewPagers: MutableList<ViewPagerInfo>)
                : super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
            this.viewPagers.run {
                clear()
                addAll(viewPagers)
            }
        }

        override fun getItem(position: Int) = viewPagers[position].fragment

        override fun getCount() = viewPagers.size
    }


    data class ViewPagerInfo(val tagId: Int, val fragment: Fragment)

}