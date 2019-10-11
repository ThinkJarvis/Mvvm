package com.kotlin.mvvm.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.kotlin.mvvm.BR
import com.kotlin.mvvm.model.Post
import com.kotlin.mvvm.model.WeatherInfo
import kotlin.properties.Delegates

abstract class BindAdapter<T : Any> :
    RecyclerView.Adapter<BindViewHolder>() {

    internal var onItemClickListener: (bean: T, view: View, position: Int) -> Unit = { _, _, _ -> }

    internal var collection: List<T> by Delegates.observable(emptyList()) { _, _, _ ->
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BindViewHolder {
        val binding = DataBindingUtil.inflate<ViewDataBinding>(
            LayoutInflater.from(parent.context), getLayout(), parent, false
        )
        return BindViewHolder(binding.root, binding)
    }

    override fun onBindViewHolder(holder: BindViewHolder, position: Int) {
        val bean = collection[position]
        holder.itemView.setOnClickListener {
            onItemClickListener(bean, holder.itemView, position)
        }
        //build.gradle must need "apply plugin: 'kotlin-kapt'"
        holder.binding.setVariable(BR.item, bean)
        onDirectBindViewHolder(bean, holder, position)
    }

    override fun getItemCount() = collection.size


    abstract fun getLayout(): Int

    open fun onDirectBindViewHolder(bean: T, holder: BindViewHolder, position: Int) {}

}