package com.hsh.weeklybox.data.repository.weeklymovielist

import com.hsh.weeklybox.data.model.weeklymovielist.MoveListData
import com.hsh.weeklybox.network.repo.IApi
import retrofit2.Response
import javax.inject.Inject

class WeeklyMovieListRepositoryImpl @Inject constructor(
    private val api: IApi
) : WeeklyMovieListRepository {
    override suspend fun getWeeklyMovieList(key: String, targetDt: String): Response<MoveListData>? {
        return api.getWeeklyMovieList(key, targetDt)
    }
}