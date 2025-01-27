package com.example.theperfumeshop

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.theperfumeshop.databinding.ActivityConfirmBinding

class ConfirmActivity : AppCompatActivity() {

    private lateinit var binding: ActivityConfirmBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityConfirmBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Cancel button: Return to MainActivity
        binding.buttonCancel.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
            startActivity(intent)
        }

        // Confirm button: Show a toast and return to MainActivity
        binding.buttonConfirm.setOnClickListener {
            val name = binding.editTextFirstName.text.toString()
            val surname = binding.editTextLastName.text.toString()
            val address = binding.editTextAddress.text.toString()
            val city = binding.editTextCity.text.toString()
            val cardNumber = binding.editTextCardNumber.text.toString()

            if (name.isBlank() || surname.isBlank() || address.isBlank() || city.isBlank() || cardNumber.isBlank()) {
                Toast.makeText(this, getString(R.string.fill_all_fields), Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, getString(R.string.order_confirmed), Toast.LENGTH_LONG).show()
                val intent = Intent(this, MainActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
                startActivity(intent)
            }
        }
    }
}