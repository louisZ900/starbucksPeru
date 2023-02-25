package com.starbucks.peru.ui.flows.sign_off.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.starbucks.peru.databinding.SbViewRewardItemBinding
import com.starbucks.peru.domain.response.SBPromotionsCarouselModel

class PromotionsCarouselAdapter (
    private var items: List<SBPromotionsCarouselModel>,
    private val onClickLister: (SBPromotionsCarouselModel) -> Unit
) : RecyclerView.Adapter<PromotionsCarouselAdapter.ViewHolder>() {
    inner class ViewHolder(val binding: SbViewRewardItemBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(promotion: SBPromotionsCarouselModel) {
            binding.item = promotion
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = SbViewRewardItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val promotion = items[position]
        holder.bind(promotion)
    }

}