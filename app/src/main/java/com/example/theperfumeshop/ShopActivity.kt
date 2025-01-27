package com.example.theperfumeshop

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.theperfumeshop.databinding.ActivityShopBinding


class ShopActivity : AppCompatActivity() {

    private lateinit var binding: ActivityShopBinding
    private val selectedPerfumes = mutableListOf<Perfume>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Set up view binding
        binding = ActivityShopBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Load data from strings.xml
        val perfumeNames = resources.getStringArray(R.array.perfume_names)
        val perfumePrices = resources.getStringArray(R.array.perfume_prices).map { it.toDouble() }
        val perfumeImages = arrayOf(
            R.drawable.perfume1, R.drawable.perfume2,
            R.drawable.perfume3, R.drawable.perfume4
        )

        // Create perfume list
        val perfumes = perfumeNames.mapIndexed { index, name ->
            Perfume(name, perfumePrices[index], perfumeImages[index])
        }

        // Set up RecyclerView
        val adapter = PerfumeAdapter(perfumes) { perfume ->
            selectedPerfumes.add(perfume)
        }
        binding.shopRecyclerView.layoutManager = LinearLayoutManager(this)
        binding.shopRecyclerView.adapter = adapter

        // Navigation buttons
        binding.toBasketButton.setOnClickListener {


            val intent = Intent(this, BasketActivity::class.java)
            intent.putParcelableArrayListExtra("selectedPerfumes", ArrayList(selectedPerfumes))
            startActivity(intent)

        }

        binding.backToMainButton.setOnClickListener {
            finish()
        }
    }
}