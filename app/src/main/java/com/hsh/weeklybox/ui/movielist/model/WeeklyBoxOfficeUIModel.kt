package com.hsh.weeklybox.ui.movielist.model

import com.hsh.weeklybox.data.model.weeklymovielist.WeeklyBoxOffice
import com.hsh.weeklybox.ui.common.handler.ActionHandle
import com.hsh.weeklybox.ui.movielist.event.WeeklyMovieListClickEvent

class WeeklyBoxOfficeUIModel(
    val weeklyBoxOffice: WeeklyBoxOffice,
    private val eventHandler: ActionHandle,
) {
    fun onClickDetailMovieInfo(movieCd: String) {
        eventHandler.handleClick(WeeklyMovieListClickEvent.GoToDetailWeeklyMovieInfo(movieCd))
    }
}