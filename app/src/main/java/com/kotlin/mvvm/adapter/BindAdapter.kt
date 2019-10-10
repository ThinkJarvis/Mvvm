package com.kotlin.mvvm.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.kotlin.mvvm.BR
import com.kotlin.mvvm.model.WeatherInfo

abstract class BindAdapter<T : Any> :
    RecyclerView.Adapter<BindViewHolder>() {


    var onItemClickListener = { bean: T, view: View, position: Int -> Unit }

//    var onItemClickListeners = mutableListOf<(bean: T, view: View, position: Int) -> Unit>()

    private val dataList by lazy {
        mutableListOf<T>()
    }

    fun setDatas(list: List<T>?) {
        if (list != null) {
            dataList.addAll(list)
            notifyDataSetChanged()
        }

    }

    fun setDataByPosition(t: T?, position: Int) {
        if (t != null) {
            dataList.add(position, t)
            notifyDataSetChanged()
        }
    }


    fun addOnItemClickListener(transform: (bean: T, view: View, position: Int) -> Unit) {
        onItemClickListener = transform
//        onItemClickListeners.add(transform)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BindViewHolder {
        val binding = DataBindingUtil.inflate<ViewDataBinding>(
            LayoutInflater.from(parent.context),
            getLayout(),
            parent,
            false
        )
        return BindViewHolder(binding.root, binding)
    }

    override fun onBindViewHolder(holder: BindViewHolder, position: Int) {
        val bean = dataList[position]
        holder.itemView.setOnClickListener {
            onItemClickListener(bean, holder.itemView, position)
        }
        //build.gradle must need "apply plugin: 'kotlin-kapt'"
        holder.binding.setVariable(BR.item, bean)
        onDirectBindViewHolder(bean, holder, position)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    abstract fun getLayout(): Int

    open fun onDirectBindViewHolder(bean: T, holder: BindViewHolder, position: Int) {}

}