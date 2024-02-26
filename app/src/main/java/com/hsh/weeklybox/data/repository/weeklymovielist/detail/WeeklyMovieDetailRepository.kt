package com.hsh.weeklybox.data.repository.weeklymovielist.detail

import com.hsh.weeklybox.data.model.weeklymovielist.MovieDetailData
import retrofit2.Response

interface WeeklyMovieDetailRepository {
    suspend fun getWeeklyMovieDetailInfo(key: String, movieCd: String): Response<MovieDetailData>?
}