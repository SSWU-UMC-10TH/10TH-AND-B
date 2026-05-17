package com.example.umc_week2

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

val Context.dataStore by preferencesDataStore(name = "product_store")

object ProductDataStore {

    private val gson = Gson()

    private val HOME_PRODUCT_LIST_KEY = stringPreferencesKey("home_product_list")
    private val BUY_PRODUCT_LIST_KEY = stringPreferencesKey("buy_product_list")

    suspend fun saveHomeProducts(context: Context, productList: List<ProductData>) {
        val jsonString = gson.toJson(productList)
        context.dataStore.edit { preferences ->
            preferences[HOME_PRODUCT_LIST_KEY] = jsonString
        }
    }

    fun getHomeProducts(context: Context): Flow<List<ProductData>> {
        return context.dataStore.data.map { preferences ->
            val jsonString = preferences[HOME_PRODUCT_LIST_KEY] ?: "[]"
            val type = object : TypeToken<List<ProductData>>() {}.type
            gson.fromJson(jsonString, type)
        }
    }

    suspend fun saveBuyProducts(context: Context, productList: List<ProductData>) {
        val jsonString = gson.toJson(productList)
        context.dataStore.edit { preferences ->
            preferences[BUY_PRODUCT_LIST_KEY] = jsonString
        }
    }

    fun getBuyProducts(context: Context): Flow<List<ProductData>> {
        return context.dataStore.data.map { preferences ->
            val jsonString = preferences[BUY_PRODUCT_LIST_KEY] ?: "[]"
            val type = object : TypeToken<List<ProductData>>() {}.type
            gson.fromJson(jsonString, type)
        }
    }
}