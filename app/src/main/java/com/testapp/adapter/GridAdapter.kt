package com.testapp.adapter

import android.content.Context
import android.graphics.Color
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.testapp.GridItems
import com.testapp.databinding.ItemGridBinding
import kotlin.random.Random

class GridAdapter(val cont: Int) : BaseAdapter<GridItems, GridAdapter.GridViewHolder>() {

    lateinit var context: Context

    init {
        for (n in 1..cont) {
            dataList.add(GridItems())
        }
    }

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

    fun isWonTheGame() {
        val filteredItem = dataList.filter { it.color != Color.BLUE }
        if (filteredItem.isEmpty()) {
            Toast.makeText(context, "You won the Game", Toast.LENGTH_LONG).show()
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