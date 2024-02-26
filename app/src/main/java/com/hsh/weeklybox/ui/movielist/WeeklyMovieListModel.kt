package com.hsh.weeklybox.ui.movielist

import com.hsh.weeklybox.ui.movielist.item.MovieListItemType
import com.hsh.weeklybox.ui.movielist.item.contents.WeeklyMovieListContentsModel
import com.hsh.weeklybox.ui.movielist.item.footer.WeeklyMovieListFooterModel
import com.hsh.weeklybox.ui.movielist.item.header.WeeklyMoviewListHeaderModel

interface WeeklyMovieListModel {

    fun getViewType(): MovieListItemType {
        return when (this) {
            is WeeklyMoviewListHeaderModel -> MovieListItemType.HEADER
            is WeeklyMovieListContentsModel -> MovieListItemType.CONTENTS
            is WeeklyMovieListFooterModel -> MovieListItemType.FOOTER
            else -> MovieListItemType.HEADER
        }
    }
}