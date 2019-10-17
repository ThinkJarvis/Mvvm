package com.kotlin.mvvm.presentation.platform

import android.view.View
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

class BindViewHolder constructor(itemView: View,val binding: ViewDataBinding) : RecyclerView.ViewHolder(itemView)