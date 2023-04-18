package com.meridiane.teacher.di

import com.meridiane.teacher.data.DemoRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataModule {
    @Provides
    @Singleton
    fun provideDemoRepository(): DemoRepository = DemoRepository()
}