package com.firstwords.feature.home.module

import com.firstwords.feature.home.dao.CategoryRepository
import com.firstwords.feature.home.dao.CategoryRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    abstract fun bindsCategoryRepository(impl: CategoryRepositoryImpl): CategoryRepository
}