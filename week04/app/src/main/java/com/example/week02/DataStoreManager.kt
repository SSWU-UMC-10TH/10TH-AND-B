package com.example.week02

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

// 스마트폰 메모리 안에 'shop_prefs'라는 이름의 저장소를 만듭니다.
private val Context.dataStore by preferencesDataStore(name = "shop_prefs")

class DataStoreManager(private val context: Context) {
    private val gson = Gson()

    // 저장소 서랍장의 '이름표(Key)'를 만듭니다.
    private val PRODUCTS_KEY = stringPreferencesKey("all_products")

    // 1. 데이터 저장하기 (객체 List -> JSON String)
    suspend fun saveProducts(productList: List<Product>) {
        val jsonString = gson.toJson(productList) // 과제 조건!
        context.dataStore.edit { prefs ->
            prefs[PRODUCTS_KEY] = jsonString
        }
    }

    // 2. 데이터 꺼내오기 (JSON String -> 객체 List)
    fun getProducts(): Flow<List<Product>> {
        return context.dataStore.data.map { prefs ->
            val jsonString = prefs[PRODUCTS_KEY]
            if (jsonString != null) {
                // JSON 글자를 다시 List<Product>로 변환합니다.
                val type = object : TypeToken<List<Product>>() {}.type
                gson.fromJson(jsonString, type)
            } else {
                emptyList() // 저장된 게 없으면 빈 리스트 반환
            }
        }
    }
}