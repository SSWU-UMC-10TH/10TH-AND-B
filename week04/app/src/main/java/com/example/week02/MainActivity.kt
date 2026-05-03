package com.example.week02

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.week02.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val TAG = "LIFE_QUIZ"

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Log.d(TAG, "onCreate")

        supportFragmentManager.beginTransaction()
            .replace(R.id.main_frm, HomeFragment())
            .commitAllowingStateLoss()

        binding.bottomNavigation.setOnItemSelectedListener { item ->
            when (item.itemId) {
                // 1. '홈' 버튼을 눌렀을 때
                R.id.nav_home -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.main_frm, HomeFragment())
                        .commitAllowingStateLoss()
                    return@setOnItemSelectedListener true
                }
                // 2. '구매하기' 버튼을 눌렀을 때
                R.id.nav_buy -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.main_frm, BuyFragment())
                        .commitAllowingStateLoss()
                    return@setOnItemSelectedListener true
                }

                // 3. '위시리스트' 버튼을 눌렀을 때
                R.id.nav_wishlist -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.main_frm, WishlistFragment())
                        .commitAllowingStateLoss()
                    return@setOnItemSelectedListener true
                }

                // 4. '장바구니' 버튼을 눌렀을 때
                R.id.nav_busket -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.main_frm, BusketFragment())
                        .commitAllowingStateLoss()
                    return@setOnItemSelectedListener true
                }

                // 5. '프로필' 버튼을 눌렀을 때
                R.id.nav_profile -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.main_frm, ProfileFragment())
                        .commitAllowingStateLoss()
                    return@setOnItemSelectedListener true
                }
            }
            false
        }
    }

    fun changeTabToBuy() {
        binding.bottomNavigation.selectedItemId = R.id.nav_buy
    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d(TAG, "onRestart")
    }
}