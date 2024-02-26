package com.hsh.weeklybox.ui.movielist

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.hsh.weeklybox.framework.event.SingleLiveEvent
import com.hsh.weeklybox.ui.movielist.model.WeeklyMovieListEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class WeeklyMovieListSharedViewModel @Inject constructor() : ViewModel() {
    private val _event = SingleLiveEvent<WeeklyMovieListEvent>()
    val event: LiveData<WeeklyMovieListEvent>
        get() = _event

    fun goToWeeklyMovieDetailInfoScreen(movieCd: String) {
        _event.value = WeeklyMovieListEvent.GoToWeeklyMovieDetailInfoScreen(movieCd)
    }
}