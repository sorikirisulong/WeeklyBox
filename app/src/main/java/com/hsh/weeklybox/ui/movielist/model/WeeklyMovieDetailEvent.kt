package com.hsh.weeklybox.ui.movielist.model

sealed class WeeklyMovieDetailEvent {
    object CloseMovieDetailScreen : WeeklyMovieDetailEvent()
}