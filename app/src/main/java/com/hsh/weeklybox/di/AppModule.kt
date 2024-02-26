package com.hsh.weeklybox.di

import com.hsh.weeklybox.network.repo.Api
import com.hsh.weeklybox.network.repo.IApi
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class AppModule {
    @Binds
    @Singleton
    abstract fun bindApi(api: IApi): Api
}
