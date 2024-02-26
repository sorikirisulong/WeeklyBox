package com.hsh.weeklybox.ui.movielist.item.header

import com.hsh.weeklybox.ui.movielist.item.WeeklyMovieListItemMapper
import com.hsh.weeklybox.ui.movielist.model.WeeklyBoxOfficeUIModel

class WeeklyMovieListHeaderMapper : WeeklyMovieListItemMapper<WeeklyMoviewListHeaderModel> {
    override fun mapToItemModel(
        boxofficeType: String?,
        showRange: String?,
        yearWeekTime: String?,
        weeklyBoxOfficeList: List<WeeklyBoxOfficeUIModel>?
    ): WeeklyMoviewListHeaderModel? {
        return WeeklyMoviewListHeaderModel(
            showRange = showRange.orEmpty(),
            yearWeekTime = yearWeekTime.orEmpty(),
        )
    }
}