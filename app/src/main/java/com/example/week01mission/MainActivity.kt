package com.example.week01mission

import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.week01mission.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 1. 행복
        binding.icHappy.setOnClickListener {
            binding.tvHappy.setTextColor(Color.parseColor("#FFD700"))
        }

        // 2. 들뜸
        binding.icExcited.setOnClickListener {
            binding.tvExcited.setTextColor(Color.parseColor("#1E90FF"))
        }

        // 3. 평범
        binding.icSoso.setOnClickListener {
            binding.tvSoso.setTextColor(Color.parseColor("#9370DB"))
        }

        // 4. 불안
        binding.icAnxious.setOnClickListener {
            binding.tvAnxious.setTextColor(Color.parseColor("#32CD32"))
        }

        // 5. 화남
        binding.icAngry.setOnClickListener {
            binding.tvAngry.setTextColor(Color.parseColor("#FF0000"))
        }
    }
}