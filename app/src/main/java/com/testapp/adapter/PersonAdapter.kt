package com.testapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.testapp.databinding.ItemPersonBinding
import com.testapp.model.DataItem

class PersonAdapter : BaseAdapter<DataItem?, PersonAdapter.PersonViewHolder>() {

    lateinit var context: Context

    inner class PersonViewHolder(val binding: ItemPersonBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun onBind(dataItem: DataItem?) {
            dataItem?.let {
                binding.dataItem = dataItem
            }

            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonViewHolder {
        context = parent.context
        val binding = ItemPersonBinding.inflate(LayoutInflater.from(context), parent, false)
        return PersonViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PersonViewHolder, position: Int) {
        holder.onBind(dataList[position])
    }

}