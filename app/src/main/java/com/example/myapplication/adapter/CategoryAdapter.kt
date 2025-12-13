package com.example.myapplication.adapter

import android.content.Intent
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.activity.ListItemsActivity
import com.example.myapplication.databinding.ViewholderCategoryBinding
import com.example.myapplication.domain.CategoryModel


class CategoryAdapter (private val items: List<CategoryModel>):
    RecyclerView.Adapter<CategoryAdapter.Viewholder>()
{
    private var selectedPosotion = -1
    private var lastSelectedPosition = -1


    class Viewholder(val binding: ViewholderCategoryBinding):
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Viewholder {
        val binding = ViewholderCategoryBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return Viewholder(binding)
    }

    override fun onBindViewHolder(holder: Viewholder, position: Int) {
        val item = items[position]

        holder.binding.titleTxt.text = item.title

        if (selectedPosotion == position) {
            holder.binding.titleTxt.setBackgroundResource(R.drawable.purple_bg)
            holder.binding.titleTxt.setTextColor(
                ContextCompat.getColor(
                    holder.itemView.context,
                    R.color.white
                )
            )

        } else {
            holder.binding.titleTxt.setBackgroundResource(R.drawable.white_bg)
            holder.binding.titleTxt.setTextColor(
                ContextCompat.getColor(
                    holder.itemView.context,
                    R.color.black
                )
            )
        }

        holder.binding.root.setOnClickListener{
            val clickedPosition = holder.adapterPosition
            if(clickedPosition != RecyclerView.NO_POSITION){
                lastSelectedPosition = selectedPosotion
                selectedPosotion = clickedPosition
                notifyItemChanged(lastSelectedPosition)
                notifyItemChanged(selectedPosotion)
            }


            Handler(Looper.getMainLooper()).postDelayed({
                val intent = Intent(holder.itemView.context, ListItemsActivity::class.java).apply{


                    putExtra("title",item.title)
                    putExtra("id",item.id.toString())
                }
                ContextCompat.startActivity( holder.itemView.context,intent, null)

            }, 1000)
        }
    }

    override fun getItemCount(): Int = items.size
}