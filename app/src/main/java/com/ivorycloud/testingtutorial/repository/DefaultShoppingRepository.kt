package com.ivorycloud.testingtutorial.repository

import androidx.lifecycle.LiveData
import com.ivorycloud.testingtutorial.local.data.ShoppingDao
import com.ivorycloud.testingtutorial.local.data.ShoppingItem
import com.ivorycloud.testingtutorial.local.remote.PixabayAPI
import com.ivorycloud.testingtutorial.local.remote.response.ImageResponse
import com.ivorycloud.testingtutorial.other.Resource
import javax.inject.Inject

class DefaultShoppingRepository @Inject constructor(
    private val shoppingDao: ShoppingDao,
    private val pixabayAPI: PixabayAPI
) : ShoppingRepository {

    override suspend fun insertShoppingItem(shoppingItem: ShoppingItem) {
        shoppingDao.insertShoppingItem(shoppingItem)
    }

    override suspend fun deleteShoppingItem(shoppingItem: ShoppingItem) {
        shoppingDao.deleteShoppingItem(shoppingItem)
    }

    override fun observeAllShoppingItems(): LiveData<List<ShoppingItem>> {
        return shoppingDao.observeAllShoppingItems()
    }

    override fun observeTotalPrice(): LiveData<Float> {
        return shoppingDao.observeTotalPrice()
    }

    override suspend fun searchForImage(imageQuery: String): Resource<ImageResponse> {
        return try {
            val response = pixabayAPI.searchForImage(imageQuery)
            when (response.code()){
              200 ->  response.body()?.let {
                  return@let Resource.success(it)
              } ?: Resource.error("An unknown error occured", null)

              401 -> Resource.unauthorized("You need to sign to be able to use this app", null)

              else -> Resource.error("An unknown error occured", null)
          }
        } catch(e: Exception) {
            Resource.error("Couldn't reach the server. Check your internet connection", null)
        }
    }
}