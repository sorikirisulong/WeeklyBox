package com.hsh.weeklybox.data.repository.weeklymovielist.detail

import com.hsh.weeklybox.data.model.weeklymovielist.MovieDetailData
import com.hsh.weeklybox.network.repo.IApi
import retrofit2.Response
import javax.inject.Inject

class WeeklyMovieDetailRepositoryImpl @Inject constructor(
    private val api: IApi
) : WeeklyMovieDetailRepository {
    override suspend fun getWeeklyMovieDetailInfo(key: String, movieCd: String): Response<MovieDetailData>? {
        return api.getMovieDetailInfo(key, movieCd)
    }
}