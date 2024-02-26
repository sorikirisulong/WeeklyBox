package com.hsh.weeklybox.ui.movielist.model

import com.hsh.weeklybox.ui.movielist.WeeklyMovieListModel

sealed class WeeklyMovieListEvent {
    class Items(val items: List<WeeklyMovieListModel>) : WeeklyMovieListEvent()
    object SearchButtonClick : WeeklyMovieListEvent()
    class GoToWeeklyMovieDetailInfoScreen(val movieCd: String) : WeeklyMovieListEvent()
}