package com.example.practice2

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.example.practice2.databinding.ItemLayoutBinding

class CustomAdapter(context: Context,private val businessCardArrayList:ArrayList<BusinessCard>) : BaseAdapter() {

    var inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
    lateinit var binding: ItemLayoutBinding

    override fun getCount(): Int = businessCardArrayList.size

    override fun getItem(position: Int): Any = businessCardArrayList[position]

    override fun getItemId(position: Int): Long = position.toLong()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        binding = ItemLayoutBinding.inflate(inflater, parent,false)
        binding.nameListviewItem.text = businessCardArrayList[position].name
        binding.contentsListviewItem.text=businessCardArrayList[position].contents
        binding.imgListviewItem.setImageResource(businessCardArrayList[position].img)

        return binding.root
    }

}