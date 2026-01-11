package com.example.zajecia5

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MyAdapter (
    private val data : MutableList<MyAnimal>,
    private val onItemClick : (String) -> Unit
) : RecyclerView.Adapter <MyAdapter.MyViewHolder> () {
    override fun onCreateViewHolder( parent: ViewGroup, viewType: Int ): MyViewHolder {
        val myInflatedView : View = LayoutInflater.from(parent.context)
            .inflate(R.layout.my_recycle_view_row, parent, false)

        return MyViewHolder(myInflatedView)
    }

    override fun onBindViewHolder( holder: MyViewHolder, position: Int ) {
        val currentAnimal = data[position]

        holder.myRowName.text = currentAnimal.Name
        holder.myRowDescription.text = currentAnimal.Description

        holder.itemView.setOnClickListener { onItemClick (currentAnimal.Sound) }
    }

    override fun getItemCount(): Int {
        return data.size
    }

    inner class MyViewHolder (view : View) : RecyclerView.ViewHolder(view) {
        val myRowName : TextView = view.findViewById<TextView>(R.id.myRowNameTextView)
        val myRowDescription : TextView = view.findViewById<TextView>(R.id.myRowDescriptionTextView)
    }

    fun updateList(newData: List<MyAnimal>) {
        data.clear()
        data.addAll(newData)
        notifyDataSetChanged()
    }
}