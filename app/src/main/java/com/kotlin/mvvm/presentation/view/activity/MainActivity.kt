package com.kotlin.mvvm.presentation.view.activity


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import com.kotlin.mvvm.R
import com.kotlin.mvvm.databinding.MainViewDataBinding
import com.kotlin.mvvm.presentation.view.fragment.MainFragment
import com.kotlin.mvvm.presentation.viewmodel.MainViewModel
import com.kotlin.walletservice.extension.buildFragmentStack
import com.kotlin.walletservice.extension.setContentView
import com.kotlin.walletservice.extension.viewModel


class MainActivity : AppCompatActivity() {

    private lateinit var mainViewDataBinding: MainViewDataBinding

    private lateinit var mainVM: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mainViewDataBinding = setContentView { R.layout.activity_main }

        mainVM = viewModel {}

        mainViewDataBinding.mainViewModel = mainVM

        bindMainFragment()
    }


    private fun bindMainFragment() {

        val fragmentStack = buildFragmentStack(R.id.main_container)

        val mainFragment = supportFragmentManager.fragmentFactory.instantiate(
            ClassLoader.getSystemClassLoader(), MainFragment::class.java.name
        )

        if (fragmentStack.findCallback(mainFragment, MainFragment::class.java) == null) {
            fragmentStack.replace(mainFragment)
        }
    }

}


