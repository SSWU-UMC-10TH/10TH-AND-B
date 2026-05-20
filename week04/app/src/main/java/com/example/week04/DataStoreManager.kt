package com.example.week04

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.week04.Product
import com.google.gson.Gson
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map


val Context.dataStore by preferencesDataStore(name = "product_prefs")

class DataStoreManager(private val context: Context) {

    private val gson = Gson()

    companion object {
        private val PRODUCT_KEY = stringPreferencesKey("products")
    }


    suspend fun saveProducts(productList: List<Product>) {
        val json = gson.toJson(productList)

        context.dataStore.edit { preferences ->
            preferences[PRODUCT_KEY] = json
        }
    }


    fun getProducts(): Flow<List<Product>> {
        return context.dataStore.data.map { preferences ->
            val json = preferences[PRODUCT_KEY] ?: "[]"

            gson.fromJson(json, Array<Product>::class.java).toList()
        }
    }


    fun isFirstRun(): Flow<Boolean> {
        return context.dataStore.data.map { preferences ->
            preferences[PRODUCT_KEY] == null
        }
    }
}