package com.kotlin.walletservice.extension

import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
import android.content.Intent
import android.net.ConnectivityManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import com.kotlin.walletservice.model.Failure
import com.kotlin.walletservice.platform.FragmentStack


val Context.connectivityManager: ConnectivityManager?
    get() = this.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager?

inline fun AppCompatActivity.buildFragmentStack(fragmentContainerId: Int): FragmentStack =
    FragmentStack(this, supportFragmentManager, fragmentContainerId)

inline fun <reified T : ViewModel> FragmentActivity.viewModel(body: T.() -> Unit): T {
    val vm = ViewModelProviders.of(this)[T::class.java]
    vm.body()
    return vm
}

inline fun <reified T : ViewModel> Fragment.viewModel(body: T.() -> Unit): T {
    val vm = ViewModelProviders.of(this)[T::class.java]
    vm.body()
    return vm
}

inline fun <T : ViewDataBinding> FragmentActivity.setContentView(getLayoutId: () -> Int): T {
    return DataBindingUtil.setContentView(this, getLayoutId())
}

inline fun <T : ViewDataBinding> Fragment.inflate(inflater: LayoutInflater,layoutId: Int,container: ViewGroup?): T {
    return DataBindingUtil.inflate(inflater, layoutId,container,false)
}

inline fun <reified T : Activity> Activity.startActivity() {

    startActivity(Intent(this, T::class.java))
}

fun <T : Any, L : LiveData<T>> LifecycleOwner.observe(liveData: L, body: (T?) -> Unit) =
    liveData.observe(this, Observer(body))

fun <L : LiveData<Failure>> LifecycleOwner.failure(liveData: L, body: (Failure?) -> Unit) =
    liveData.observe(this, Observer(body))

inline fun View.isVisible() = this.visibility == View.VISIBLE

inline fun View.visible() {
    this.visibility = View.VISIBLE
}

inline fun View.invisible() {
    this.visibility = View.GONE
}