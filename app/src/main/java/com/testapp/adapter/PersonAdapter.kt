package com.testapp.adapter

import android.content.Context
import android.graphics.Color
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.testapp.databinding.ItemGridBinding
import com.testapp.model.DataItem
import kotlin.random.Random

class PersonAdapter() : BaseAdapter<DataItem, PersonAdapter.GridViewHolder>() {

    lateinit var context: Context

    inner class GridViewHolder(val binding: ItemGridBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun onBind(gridItems: GridItems) {
            binding.cardView.setBackgroundColor(gridItems.color)
            if (gridItems.color == Color.WHITE) {
                val nextInt = Random(5).nextInt()
                Handler(Looper.getMainLooper())
                    .postDelayed({
                        gridItems.color = Color.RED
                        update(adapterPosition, gridItems)
                    }, (nextInt * 1000).toLong())
            } else if (gridItems.color == Color.RED) {
                binding.cardView.setOnClickListener {
                    gridItems.color = Color.BLUE
                    update(adapterPosition, gridItems)
                }
            } else if (gridItems.color == Color.BLUE) {
                binding.cardView.setOnClickListener {
                    isWonTheGame()
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GridViewHolder {
        context = parent.context
        val binding = ItemGridBinding.inflate(LayoutInflater.from(context), parent, false)
        return GridViewHolder(binding)
    }

    override fun onBindViewHolder(holder: GridViewHolder, position: Int) {
        holder.onBind(dataList[position])
    }

}