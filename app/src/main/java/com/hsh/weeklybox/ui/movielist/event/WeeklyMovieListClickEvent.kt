package com.hsh.weeklybox.ui.movielist.event

import com.hsh.weeklybox.ui.common.handler.ActionEvent

sealed class WeeklyMovieListClickEvent : ActionEvent {
    data class GoToDetailWeeklyMovieInfo(val movieCd: String): WeeklyMovieListClickEvent()
}