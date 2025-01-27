package com.example.theperfumeshop

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.theperfumeshop.databinding.ActivityBasketBinding

import android.widget.ImageView
import android.widget.TextView


class BasketActivity : AppCompatActivity() {

    private lateinit var binding: ActivityBasketBinding
    private var selectedPerfumes: ArrayList<Perfume>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBasketBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Get the passed data
        selectedPerfumes = intent.getParcelableArrayListExtra("selectedPerfumes")

        // Dynamically display the selected perfumes
        selectedPerfumes?.let { perfumes ->
            var totalPrice = 0.0
            for (perfume in perfumes) {
                // Add perfume details
                val perfumeItem = layoutInflater.inflate(R.layout.item_basket, binding.basketItemsContainer, false)

                // Set the perfume name, price, and image
                perfumeItem.findViewById<TextView>(R.id.perfume_name).text = perfume.name
                perfumeItem.findViewById<TextView>(R.id.perfume_price).text = getString(R.string.price_format, perfume.price)
                perfumeItem.findViewById<ImageView>(R.id.perfume_image).setImageResource(perfume.imageResId)

                // Add the perfume item to the basket container
                binding.basketItemsContainer.addView(perfumeItem)

                // Calculate total price
                totalPrice += perfume.price
            }

            // Set the total price
            binding.totalPriceText.text = getString(R.string.total_price_format, totalPrice)
        }

        // Button: Go back to ShopActivity
        binding.buttonBackToShop.setOnClickListener {
            finish()
        }

        // Button: Proceed to ConfirmActivity
        binding.buttonProceedToConfirm.setOnClickListener {
            val intent = Intent(this, ConfirmActivity::class.java)
            intent.putParcelableArrayListExtra("selectedPerfumes", selectedPerfumes)
            startActivity(intent)
        }
    }
}