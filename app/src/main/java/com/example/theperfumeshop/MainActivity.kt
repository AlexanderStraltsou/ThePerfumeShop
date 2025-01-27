package com.example.theperfumeshop

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.content.Intent
import android.content.res.Configuration
import android.widget.Toast
import java.util.*

import com.example.theperfumeshop.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.shopDescription.text = getString(R.string.shop_description)

        binding.selectLanguageSwedish.setOnClickListener {
            setLocale("sv")
            Toast.makeText(this, getString(R.string.language_swedish), Toast.LENGTH_SHORT).show()
        }

        binding.selectLanguageEnglish.setOnClickListener {
            setLocale("en")
            Toast.makeText(this, getString(R.string.language_english), Toast.LENGTH_SHORT).show()
        }

        binding.goToShop.setOnClickListener {
            val intent = Intent(this, ShopActivity::class.java)
            startActivity(intent)
        }
    }

    private fun setLocale(languageCode: String) {
        val locale = Locale(languageCode)
        Locale.setDefault(locale)
        val config = Configuration()
        config.locale = locale
        resources.updateConfiguration(config, resources.displayMetrics)
        recreate()
    }
}