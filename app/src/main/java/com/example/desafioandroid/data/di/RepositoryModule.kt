package com.example.desafioandroid.data.di

import com.example.desafioandroid.data.repository.BoardsRepository
import com.example.desafioandroid.data.repository.BoardsRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

/**
 * Hilt module that provides repository-level dependencies.
 * This module is installed in the [SingletonComponent], meaning the provided
 * dependencies will have a singleton scope throughout the application's lifecycle.
 */
@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindBoardsRepository(impl: BoardsRepositoryImpl): BoardsRepository
}