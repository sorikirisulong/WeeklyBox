package com.hsh.weeklybox.di

import com.hsh.weeklybox.data.repository.weeklymovielist.WeeklyMovieListRepository
import com.hsh.weeklybox.data.repository.weeklymovielist.WeeklyMovieListRepositoryImpl
import com.hsh.weeklybox.data.repository.weeklymovielist.detail.WeeklyMovieDetailRepository
import com.hsh.weeklybox.data.repository.weeklymovielist.detail.WeeklyMovieDetailRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
abstract class RepositoryModule {
    @Binds
    @Singleton
    abstract fun bindWeeklyMovieListRepository(repository: WeeklyMovieListRepositoryImpl): WeeklyMovieListRepository

    @Binds
    @Singleton
    abstract fun bindMovieDetailInfoRepository(repository: WeeklyMovieDetailRepositoryImpl): WeeklyMovieDetailRepository
}