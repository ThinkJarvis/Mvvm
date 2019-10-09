package com.kotlin.mvvm.adapter

import android.view.View
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.kotlin.mvvm.BR
import com.kotlin.mvvm.model.WeatherInfo

abstract class BindAdapter<T : Any, VB : ViewDataBinding> :
    RecyclerView.Adapter<BindViewHolder<VB>>() {


    var onItemClickListener: OnItemClickListener<T>? = null


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

    fun addOnItemClickListener(listener: OnItemClickListener<T>) {
        onItemClickListener = listener
    }

    override fun onBindViewHolder(holder: BindViewHolder<VB>, position: Int) {
        val bean = dataList[position]
        holder.itemView.setOnClickListener {
            onItemClickListener?.itemClick(bean, holder.itemView, position)
        }

        holder.binding.setVariable(BR.item, bean)
        onDirectBindViewHolder(bean, holder, position)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    abstract fun getLayout(): Int

    abstract fun onDirectBindViewHolder(bean: T, holder: BindViewHolder<VB>, position: Int)

    interface OnItemClickListener<T> {
        fun itemClick(bean: T, view: View, position: Int)
    }
}