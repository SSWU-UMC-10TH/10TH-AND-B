package com.example.week01

import com.example.week01.databinding.ActivityMainBinding
import android.graphics.Color
import androidx.activity.enableEdgeToEdge
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.imgHappy.setOnClickListener {
            binding.txtHappy.setTextColor(Color.parseColor("#FFEFB6"))
        }

        binding.imgGood.setOnClickListener {
            binding.txtGood.setTextColor(Color.parseColor("#CEE7F5"))
        }

        binding.imgNormal.setOnClickListener {
            binding.txtNormal.setTextColor(Color.parseColor("#BEC3ED"))
        }

        binding.imgSad.setOnClickListener {
            binding.txtSad.setTextColor(Color.parseColor("#B1D3B9"))
        }

        binding.imgAngry.setOnClickListener {
            binding.txtAngry.setTextColor(Color.parseColor("#EB8B8B"))
        }
    }
}