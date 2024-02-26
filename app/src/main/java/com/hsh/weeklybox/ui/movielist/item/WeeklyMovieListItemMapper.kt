package com.hsh.weeklybox.ui.movielist.item

import com.hsh.weeklybox.ui.movielist.WeeklyMovieListModel
import com.hsh.weeklybox.ui.movielist.model.WeeklyBoxOfficeUIModel

interface WeeklyMovieListItemMapper<out T : WeeklyMovieListModel> {
    fun mapToItemModel(
        boxofficeType: String?,
        showRange: String?,
        yearWeekTime: String?,
        weeklyBoxOfficeUIModelList: List<WeeklyBoxOfficeUIModel>?,
    ): T?
}