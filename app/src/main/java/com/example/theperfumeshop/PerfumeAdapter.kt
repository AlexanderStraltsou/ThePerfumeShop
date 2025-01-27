package com.example.theperfumeshop

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.theperfumeshop.databinding.ItemPerfumeBinding

class PerfumeAdapter(
    private val perfumes: List<Perfume>,
    private val onAddToBasket: (Perfume) -> Unit
) : RecyclerView.Adapter<PerfumeAdapter.PerfumeViewHolder>() {


    inner class PerfumeViewHolder(val binding: ItemPerfumeBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(perfume: Perfume) {
            binding.perfumeName.text = perfume.name
            binding.perfumePrice.text = binding.root.context.getString(
                R.string.price_placeholder, perfume.price
            )
            binding.perfumeImage.setImageResource(perfume.imageResId)

            binding.addToBasketButton.setOnClickListener {
                onAddToBasket(perfume)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PerfumeViewHolder {
        val binding = ItemPerfumeBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return PerfumeViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PerfumeViewHolder, position: Int) {
        holder.bind(perfume = perfumes[position])
    }

    override fun getItemCount(): Int = perfumes.size
}