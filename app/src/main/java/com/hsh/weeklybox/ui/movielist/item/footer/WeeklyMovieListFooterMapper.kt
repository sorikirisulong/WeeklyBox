package com.hsh.weeklybox.ui.movielist.item.footer

import com.hsh.weeklybox.ui.movielist.item.WeeklyMovieListItemMapper
import com.hsh.weeklybox.ui.movielist.model.WeeklyBoxOfficeUIModel

class WeeklyMovieListFooterMapper : WeeklyMovieListItemMapper<WeeklyMovieListFooterModel> {
    override fun mapToItemModel(
        boxofficeType: String?,
        showRange: String?,
        yearWeekTime: String?,
        weeklyBoxOfficeList: List<WeeklyBoxOfficeUIModel>?
    ): WeeklyMovieListFooterModel? {
        return WeeklyMovieListFooterModel(
            showType = boxofficeType.orEmpty(),
        )
    }
}