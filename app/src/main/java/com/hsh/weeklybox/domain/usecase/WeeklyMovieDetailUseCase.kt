package com.hsh.weeklybox.domain.usecase

import com.hsh.weeklybox.data.model.weeklymovielist.MovieDetailData
import com.hsh.weeklybox.data.repository.weeklymovielist.detail.WeeklyMovieDetailRepository
import com.hsh.weeklybox.framework.annotation.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import retrofit2.Response
import javax.inject.Inject

class WeeklyMovieDetailUseCase @Inject constructor(
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
    private val repository: WeeklyMovieDetailRepository
) {
    suspend fun getWeeklyMovieList(key: String, movieCd: String): Response<MovieDetailData>? {
        return withContext(ioDispatcher) {
            repository.getWeeklyMovieDetailInfo(key, movieCd)
        }
    }
}