package com.hsh.weeklybox.ui.movielist.item.contents

import com.hsh.weeklybox.ui.movielist.item.WeeklyMovieListItemMapper
import com.hsh.weeklybox.ui.movielist.model.WeeklyBoxOfficeUIModel

class WeeklyMovieListContentsMapper : WeeklyMovieListItemMapper<WeeklyMovieListContentsModel> {
    override fun mapToItemModel(
        boxofficeType: String?,
        showRange: String?,
        yearWeekTime: String?,
        weeklyBoxOfficeList: List<WeeklyBoxOfficeUIModel>?
    ): WeeklyMovieListContentsModel? {
        return weeklyBoxOfficeList?.isEmpty()?.not()?.let {
            WeeklyMovieListContentsModel(
                hasWeeklyBoxOfficeList = it,
                weeklyBoxOfficeList = weeklyBoxOfficeList
            )
        }
    }
}