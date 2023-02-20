package com.starbucks.peru.ui.flows.sign_off.fragments.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.starbucks.peru.databinding.SbViewPromotionItemBinding
import com.starbucks.peru.databinding.SbViewRewardItemBinding
import com.starbucks.peru.domain.response.SBPromotionsModel

class PromotionsAdapter(
    private var items: List<SBPromotionsModel>,
    private val onClickLister: (SBPromotionsModel) -> Unit
) : RecyclerView.Adapter<PromotionsAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: SbViewPromotionItemBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(promotion: SBPromotionsModel) {
            binding.item = promotion
            //products.products?.let { binding.cvProducts.init(it) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = SbViewPromotionItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val promotion = items[position]
        holder.bind(promotion)
        holder.binding.buttonFirst.setOnClickListener {
            onClickLister(promotion)
        }

    }

}