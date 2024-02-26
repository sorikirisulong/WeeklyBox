package com.hsh.weeklybox.data.repository.weeklymovielist

import com.hsh.weeklybox.data.model.weeklymovielist.MoveListData
import retrofit2.Response

interface WeeklyMovieListRepository {
    suspend fun getWeeklyMovieList(key: String, targetDt: String): Response<MoveListData>?
}