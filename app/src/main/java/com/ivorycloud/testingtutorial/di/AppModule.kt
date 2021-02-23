package com.ivorycloud.testingtutorial.di

import android.content.Context
import androidx.room.Room
import com.ivorycloud.testingtutorial.local.data.ShoppingDao
import com.ivorycloud.testingtutorial.other.Constants.BASE_URL
import com.ivorycloud.testingtutorial.other.Constants.DATABASE_NAME
import com.ivorycloud.testingtutorial.local.data.ShoppingItemDatabase
import com.ivorycloud.testingtutorial.local.remote.PixabayAPI
import com.ivorycloud.testingtutorial.repository.DefaultShoppingRepository
import com.ivorycloud.testingtutorial.repository.ShoppingRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideDefaultShoppingRepository(
        dao: ShoppingDao,
        api: PixabayAPI
    ) = DefaultShoppingRepository(dao, api) as ShoppingRepository


    @Singleton
    @Provides
    fun provideShoppingItemDatabase(
        @ApplicationContext context: Context
    ) = Room.databaseBuilder(context, ShoppingItemDatabase::class.java, DATABASE_NAME).build()

    @Singleton
    @Provides
    fun provideShoppingDao(
        database: ShoppingItemDatabase
    ) = database.shoppingDao()

    @Singleton
    @Provides
    fun providePixabayApi(): PixabayAPI {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
            .create(PixabayAPI::class.java)
    }

    /*
    same as above
    just for explaniation
    *    @Singleton
    @Provides
    fun providePixabayApi()=
        Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
            .create(PixabayAPI::class.java)
    * */
}