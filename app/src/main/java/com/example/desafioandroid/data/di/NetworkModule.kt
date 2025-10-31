package com.example.desafioandroid.data.di

import com.example.desafioandroid.BuildConfig
import com.example.desafioandroid.data.api.TrelloService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

/**
 * Dagger Hilt module that provides network-related dependencies.
 * This module is installed in the [SingletonComponent], meaning the provided instances
 * will be singletons for the entire application lifecycle.
 */
@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder().build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.API_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideTrelloApiService(retrofit: Retrofit): TrelloService {
        return retrofit.create(TrelloService::class.java)
    }
}