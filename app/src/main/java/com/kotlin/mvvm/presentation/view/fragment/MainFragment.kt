package com.kotlin.mvvm.presentation.view.fragment

import android.os.Bundle
import android.util.Log
import android.view.View
import com.kotlin.mvvm.R
import com.kotlin.walletservice.platform.BaseFragment
import com.orhanobut.logger.Logger
import kotlinx.android.synthetic.main.fragment_main.*

class MainFragment : BaseFragment() {
    override val layoutId = R.layout.fragment_main

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        layout_table.setupWithViewPager(view_pager, childFragmentManager)
    }
}