package com.kotlin.mvvm.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.kotlin.mvvm.BR
import com.kotlin.mvvm.model.WeatherInfo

abstract class BindAdapter<T : Any, VB : ViewDataBinding> :
    RecyclerView.Adapter<BindViewHolder<VB>>() {


    var onItemClickListener = { bean: T, view: View, position: Int -> Unit }

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
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BindViewHolder<VB> {
        val binding = DataBindingUtil.inflate<VB>(LayoutInflater.from(parent.context), getLayout(), parent, false)
        return BindViewHolder(binding.root, binding)
    }

    override fun onBindViewHolder(holder: BindViewHolder<VB>, position: Int) {
        val bean = dataList[position]
        holder.itemView.setOnClickListener {
            onItemClickListener(bean, holder.itemView, position)
        }

        holder.binding.setVariable(BR.item, bean)
        onDirectBindViewHolder(bean, holder, position)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    abstract fun getLayout(): Int

    open fun onDirectBindViewHolder(bean: T, holder: BindViewHolder<VB>, position: Int) {}


}