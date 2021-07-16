package com.example.practice2

import androidx.recyclerview.widget.RecyclerView

interface ItemActionListener{
    fun onItemMoved(from: Int, to: Int)
    fun onItemSwiped(position: Int)
}