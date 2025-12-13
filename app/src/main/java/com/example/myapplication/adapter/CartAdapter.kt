package com.example.myapplication.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myapplication.Helper.ChangeNumberItemsListener
import com.example.myapplication.Helper.ManagmentCart
import com.example.myapplication.databinding.ViewholderCartBinding
import com.example.myapplication.domain.ItemsModel

class CartAdapter(
    private val listItemSelected: ArrayList<ItemsModel>,
    private val context: Context,
    var changeNumberItemsListener: ChangeNumberItemsListener
) : RecyclerView.Adapter<CartAdapter.ViewHolder>() {


    class ViewHolder(val binding: ViewholderCartBinding) :
        RecyclerView.ViewHolder(binding.root)


    private val managmentCart = ManagmentCart(context)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val binding = ViewholderCartBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = listItemSelected[position]

        holder.binding.apply {
            titleTxt.text = item.title

            val total = item.numberInCart * item.price
            totalEachTime.text = "$${String.format("%.2f", total)}"
            numberItemTxt.text = item.numberInCart.toString()

            Glide.with(holder.itemView.context)
                .load(item.picUrl[0])
                .into(pic)


            plusBtn.setOnClickListener {

                val currentPosition = holder.getBindingAdapterPosition()
                if (currentPosition != RecyclerView.NO_POSITION) {
                    managmentCart.plusItem(listItemSelected, currentPosition, object : ChangeNumberItemsListener {
                        override fun onChanged() {
                            notifyItemChanged(currentPosition)
                            changeNumberItemsListener.onChanged()
                        }
                    })
                }
            }


            minusBtn.setOnClickListener {

                val currentPosition = holder.getBindingAdapterPosition()
                if (currentPosition != RecyclerView.NO_POSITION) {
                    managmentCart.minusItem(listItemSelected, currentPosition, object : ChangeNumberItemsListener {
                        override fun onChanged() {

                            notifyDataSetChanged()
                            changeNumberItemsListener.onChanged()
                        }
                    })
                }
            }
        }
    }

    override fun getItemCount(): Int = listItemSelected.size
}