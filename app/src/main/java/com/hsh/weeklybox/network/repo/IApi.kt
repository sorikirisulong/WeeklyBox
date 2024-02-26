package com.hsh.weeklybox.network.repo

import com.hsh.weeklybox.data.model.weeklymovielist.MoveListData
import com.hsh.weeklybox.data.model.weeklymovielist.MovieDetailData
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class IApi @Inject constructor(
    private val api: CommonApi
) : Api, Convertible {
    override suspend fun getWeeklyMovieList(key: String, targetDt: String): Response<MoveListData>? {
        return api.getWeeklyMovieList(key, targetDt)
    }

    override suspend fun getMovieDetailInfo(
        key: String,
        movieCd: String
    ): Response<MovieDetailData>? {
        return api.getMovieDetailInfo(key, movieCd)
    }
}