package com.kotlin.mvvm.presentation.extension

import android.view.View

class View {
   inline fun View.isVisible() = this.visibility == View.VISIBLE

   inline fun View.visible() { this.visibility = View.VISIBLE }

   inline fun View.invisible() { this.visibility = View.GONE }
}