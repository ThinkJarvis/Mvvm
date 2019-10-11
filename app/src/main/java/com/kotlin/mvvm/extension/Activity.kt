package com.kotlin.mvvm.extension

import android.widget.Toast
import androidx.annotation.StringRes
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders

inline fun <reified T : ViewModel> FragmentActivity.viewModel(body: T.() -> Unit): T {
    val vm = ViewModelProviders.of(this)[T::class.java]
    vm.body()
    return vm
}

fun FragmentActivity.toast(@StringRes stringId: Int) {
    Toast.makeText(this, stringId, Toast.LENGTH_SHORT).show()
}