package com.kotlin.mvvm.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView

class BindViewHolder<VB> constructor(itemView: View,val binding: VB ) : RecyclerView.ViewHolder(itemView) 