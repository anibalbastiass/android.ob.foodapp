package com.ob.foodapp.feature.result.ui

import android.graphics.PorterDuff
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.DrawableCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.ob.foodapp.R
import com.ob.foodapp.databinding.FragmentResultItemBinding
import com.ob.foodapp.feature.result.presentation.model.UiResultItem
import com.ob.foodapp.feature.result.ui.adapter.ResultDiffCallback
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

            if (item.favorite) {
                tintFavorite(binding.ivFavorite, true)
                binding.ivFavorite.setBackgroundResource(R.drawable.button_favorite_selected)
            } else {
                tintFavorite(binding.ivFavorite, false)
                binding.ivFavorite.setBackgroundResource(R.drawable.button_favorite)
            }

            with(ImageUtils) {
                binding.ivFood.loadImage(
                    imageUrl = item.imageUrl,
                    placeHolder = R.drawable.food_placeholder
                )
            }
        }
    }

    private fun tintFavorite(
        ivFavorite: ImageView,
        favorite: Boolean
    ) {
        ContextCompat.getDrawable(
            ivFavorite.context,
            R.drawable.ic_favourite
        )?.let {
            DrawableCompat.wrap(
                it
            ).apply {
                setTint(
                    if (favorite) {
                        ContextCompat.getColor(ivFavorite.context, R.color.white)
                    } else {
                        ContextCompat.getColor(ivFavorite.context, R.color.textPrimaryColor)
                    }
                )
                setTintMode(PorterDuff.Mode.SRC_IN)
                ivFavorite.setImageDrawable(this)
            }
        }
    }

    private fun formatPrice(rawPrice: Long): String {
        return "$${(rawPrice / 100).toFloat()}"
    }

    fun setData(newItems: List<UiResultItem>) {
        val diffCallback = ResultDiffCallback(items, newItems)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        items.clear()
        items.addAll(newItems)
        diffResult.dispatchUpdatesTo(this)
    }
}