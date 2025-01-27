package com.example.theperfumeshop

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.theperfumeshop.databinding.ActivityBasketBinding
import com.example.theperfumeshop.databinding.ItemBasketBinding


class BasketActivity : AppCompatActivity() {

    private lateinit var binding: ActivityBasketBinding
    private var selectedPerfumes: ArrayList<Perfume>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBasketBinding.inflate(layoutInflater)
        setContentView(binding.root)

        selectedPerfumes = intent.getParcelableArrayListExtra("selectedPerfumes")

        selectedPerfumes?.let { perfumes ->
            var totalPrice = 0.0

            for (perfume in perfumes) {

                val perfumeItemBinding = ItemBasketBinding.inflate(layoutInflater, binding.basketItemsContainer, false)

                perfumeItemBinding.perfumeName.text = perfume.name
                perfumeItemBinding.perfumePrice.text = getString(R.string.price_format, perfume.price)
                perfumeItemBinding.perfumeImage.setImageResource(perfume.imageResId)

                binding.basketItemsContainer.addView(perfumeItemBinding.root)

                totalPrice += perfume.price
            }

            binding.totalPriceText.text = getString(R.string.total_price_format, totalPrice)
        }

        binding.buttonBackToShop.setOnClickListener {
            finish()
        }

        binding.buttonProceedToConfirm.setOnClickListener {
            val intent = Intent(this, ConfirmActivity::class.java)
            intent.putParcelableArrayListExtra("selectedPerfumes", selectedPerfumes)
            startActivity(intent)
        }
    }
}