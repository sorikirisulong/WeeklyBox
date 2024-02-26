package com.hsh.weeklybox.ui.movielist

import androidx.lifecycle.LiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hsh.weeklybox.data.model.weeklymovielist.MovieDetailData
import com.hsh.weeklybox.domain.usecase.WeeklyMovieDetailUseCase
import com.hsh.weeklybox.framework.event.SingleLiveEvent
import com.hsh.weeklybox.ui.common.const.ExtraKey
import com.hsh.weeklybox.ui.movielist.model.WeeklyMovieDetailEvent
import com.hsh.weeklybox.ui.movielist.model.WeeklyMovieDetailViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeeklyMovieDetailViewModel @Inject constructor(
    private val movieDetailUseCase: WeeklyMovieDetailUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val movieCode = savedStateHandle.get<String>(ExtraKey.MOVIE_CODE)
    private val _event = SingleLiveEvent<WeeklyMovieDetailEvent>()
    val event: LiveData<WeeklyMovieDetailEvent>
        get() = _event

    private val _progress = SingleLiveEvent<Boolean>()
    val progress: LiveData<Boolean>
        get() = _progress

    val viewState = WeeklyMovieDetailViewState()

    init {
        initViewState()
    }

    fun onCloseBtnClick() {
        _event.value = WeeklyMovieDetailEvent.CloseMovieDetailScreen
    }

    private fun initViewState() {
        viewState.hasData = false
        viewState.textFailOverView = ""
    }

    private fun updateViewState(movieDetailData: MovieDetailData) {
        viewState.hasData = movieDetailData.movie.movieNm.isNullOrEmpty().not()
        viewState.textFailOverView = if (viewState.hasData) "" else NO_RESULT_MESSAGE
        viewState.textMovieCode = movieDetailData.movie.movieCd.orEmpty()
        viewState.textMovieOpenDt = movieDetailData.movie.openDt.orEmpty()
        viewState.textMovieType = movieDetailData.movie.typeNm.orEmpty()
        viewState.textMovieOpenStat = movieDetailData.movie.prdtStatNm.orEmpty()
        viewState.textMovieTitleEng = movieDetailData.movie.movieNmEn.orEmpty()
        viewState.textMovieTitleKor = movieDetailData.movie.movieNm.orEmpty()

    }

    fun getWeeklyMovieList(apiKey: String) {
        _progress.value = true
        viewModelScope.launch {
            try {
                val response = movieDetailUseCase.getWeeklyMovieList(apiKey, movieCode.orEmpty())
                if (response?.isSuccessful == true) {
                    val responseBody = response.body()
                    responseBody?.let {
                        updateViewState(it)
                    }
                } else {
                    viewState.hasData = false
                    viewState.textFailOverView = NO_RESULT_MESSAGE
                }
            } catch (e: Exception) {
                viewState.textFailOverView = NO_RESULT_MESSAGE
            }
            _progress.value = false
        }
    }

    companion object {
        private const val NO_RESULT_MESSAGE = "조회된 영화 상세정보가 없습니다."
    }
}