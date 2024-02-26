package com.hsh.weeklybox.domain.usecase

import com.hsh.weeklybox.data.model.weeklymovielist.MoveListData
import com.hsh.weeklybox.data.repository.weeklymovielist.WeeklyMovieListRepository
import com.hsh.weeklybox.framework.annotation.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import retrofit2.Response
import javax.inject.Inject

class WeeklyMovieListUseCase @Inject constructor(
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
    private val repository: WeeklyMovieListRepository
) {
    suspend fun getWeeklyMovieList(key: String, targetDt: String): Response<MoveListData>? {
        return withContext(ioDispatcher) {
            repository.getWeeklyMovieList(key, targetDt)
        }
    }
}
