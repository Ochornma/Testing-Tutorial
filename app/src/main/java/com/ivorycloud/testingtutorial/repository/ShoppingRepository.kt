package com.ivorycloud.testingtutorial.repository

import androidx.lifecycle.LiveData
import com.ivorycloud.testingtutorial.local.data.ShoppingItem
import com.ivorycloud.testingtutorial.local.remote.response.ImageResponse
import com.ivorycloud.testingtutorial.other.Resource

interface ShoppingRepository {
    suspend fun insertShoppingItem(shoppingItem: ShoppingItem)

    suspend fun deleteShoppingItem(shoppingItem: ShoppingItem)

    fun observeAllShoppingItems(): LiveData<List<ShoppingItem>>

    fun observeTotalPrice(): LiveData<Float>

    suspend fun searchForImage(imageQuery: String): Resource<ImageResponse>
}