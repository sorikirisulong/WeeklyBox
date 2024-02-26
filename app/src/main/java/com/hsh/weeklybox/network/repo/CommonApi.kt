package com.hsh.weeklybox.network.repo

import com.hsh.weeklybox.data.model.weeklymovielist.MoveListData
import com.hsh.weeklybox.data.model.weeklymovielist.MovieDetailData
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface CommonApi {
    @GET("boxoffice/searchWeeklyBoxOfficeList.xml")
    suspend fun getWeeklyMovieList(
        @Query("key") key: String,
        @Query("targetDt") targetDt: String
    ): Response<MoveListData>?

    @GET("movie/searchMovieInfo.xml")
    suspend fun getMovieDetailInfo(
        @Query("key") key: String,
        @Query("movieCd") movieCd: String
    ): Response<MovieDetailData>?
}