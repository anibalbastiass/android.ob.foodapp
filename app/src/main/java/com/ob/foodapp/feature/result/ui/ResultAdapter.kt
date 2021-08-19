package com.ob.foodapp.feature.result.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ob.foodapp.R
import com.ob.foodapp.databinding.FragmentResultItemBinding
import com.ob.foodapp.feature.result.presentation.model.UiResultItem
import com.ob.uicore.utils.ImageUtils

class ResultAdapter : RecyclerView.Adapter<ResultAdapter.ViewHolder>() {

    var items: MutableList<UiResultItem> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ResultAdapter.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = FragmentResultItemBinding.inflate(inflater)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(items[position])

    inner class ViewHolder(private val binding: FragmentResultItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: UiResultItem) {
            binding.tvFoodTitle.text = item.name
            binding.tvFoodPrice.text = formatPrice(item.price)

            with(ImageUtils) {
                binding.ivFood.loadImage(
                    imageUrl = item.imageUrl,
                    placeHolder = R.drawable.food_placeholder
                )
            }
        }
    }

    private fun formatPrice(rawPrice: Long): String {
        return "$${(rawPrice / 100).toFloat()}"
    }
}