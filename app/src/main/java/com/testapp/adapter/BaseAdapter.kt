package com.testapp.adapter

import androidx.recyclerview.widget.RecyclerView

abstract class BaseAdapter<T, VH : RecyclerView.ViewHolder> : RecyclerView.Adapter<VH>() {

    var dataList: ArrayList<T> = arrayListOf()

    fun add(data: T) {
        dataList.add(data)
        notifyItemInserted(dataList.size - 1)
    }

    fun addAll(data: List<T>) {
        for (datum in data) {
            add(datum)
        }
    }

    fun update(position: Int, data: T) {
        if (dataList.isNotEmpty() && dataList.size > position) {
            dataList[position] = data
            notifyItemChanged(position)
        }
    }

    fun add(position: Int, data: T) {
        dataList.add(position, data)
        notifyItemInserted(position)
    }

    fun clear() {
        val size = dataList.size
        for (i in 0 until size) {
            remove(0)
        }
    }

    fun remove(position: Int) {
        dataList.removeAt(position)
        notifyItemRemoved(position)
        notifyItemRangeChanged(position, dataList.size)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

}