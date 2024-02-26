package com.hsh.weeklybox.network.repo

import com.hsh.weeklybox.data.model.weeklymovielist.MoveListData
import com.hsh.weeklybox.data.model.weeklymovielist.MovieDetailData
import retrofit2.Response


interface Api {
    suspend fun getWeeklyMovieList(key: String, targetDt: String): Response<MoveListData>?
    suspend fun getMovieDetailInfo(key: String, movieCd: String): Response<MovieDetailData>?
}