package com.example.myapplication.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.example.myapplication.databinding.SliderItemContainerBinding
import com.example.myapplication.domain.SliderModel

class SliderAdapter(
    private var sliderItems: List<SliderModel>,
    private val viewPager: ViewPager2
) : RecyclerView.Adapter<SliderAdapter.SliderViewHolder>()
{
    private lateinit var context: Context
    private val runnable = Runnable {
        // Since this is used for infinite scrolling, re-assigning the list
        // and notifying data set change is the correct logic for looping.
        sliderItems = sliderItems
        notifyDataSetChanged()
    }


    class SliderViewHolder(private val binding: SliderItemContainerBinding):
    // FIX 1: Removed the named argument 'itemView ='
        RecyclerView.ViewHolder(binding.root){
        fun setImage(sliderModel: SliderModel, context: Context){
            Glide
                .with(context)
                .load(sliderModel.url)
                .into(binding.imageSlide)
        }
    }


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SliderAdapter.SliderViewHolder {
        context = parent.context

        // FIX 2: Correctly called ViewBinding.inflate using positional arguments (inflater, parent, attachToParent)
        val binding = SliderItemContainerBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false // attachToParent
        )
        return SliderViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SliderAdapter.SliderViewHolder, position: Int) {
        // Simplified the setImage call (optional, but cleaner)
        holder.setImage(sliderItems[position], context)

        // FIX 3: Removed the named argument 'action ='
        if(position == sliderItems.lastIndex -1){
            viewPager.post(runnable)
        }
    }

    override fun getItemCount(): Int =sliderItems.size

}