package com.kotlin.mvvm.presentation.extension

import android.app.Activity
import android.content.ContextWrapper
import android.content.Intent
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders

inline fun <reified T : ViewModel> FragmentActivity.viewModel(body: T.() -> Unit): T {
    val vm = ViewModelProviders.of(this)[T::class.java]
    vm.body()
    return vm
}

inline fun <T : ViewDataBinding> FragmentActivity.setContentView(getLayoutId: () -> Int): T {
    return DataBindingUtil.setContentView(this, getLayoutId())
}

inline fun ContextWrapper.toast(@StringRes stringId: Int) {
    Toast.makeText(this, stringId, Toast.LENGTH_SHORT).show()
}

inline fun ContextWrapper.toast(stringId: String) {
    Toast.makeText(this, stringId, Toast.LENGTH_SHORT).show()
}

inline fun <reified T: Activity> Activity.startActivity() {
    startActivity(Intent(this, T::class.java))
}