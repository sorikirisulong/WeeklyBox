package com.hsh.weeklybox.ui.movielist.item

import com.hsh.weeklybox.ui.movielist.WeeklyMovieListModel
import com.hsh.weeklybox.ui.movielist.item.contents.WeeklyMovieListContentsMapper
import com.hsh.weeklybox.ui.movielist.item.footer.WeeklyMovieListFooterMapper
import com.hsh.weeklybox.ui.movielist.item.header.WeeklyMovieListHeaderMapper

enum class MovieListItemType(val mapper: Class<out WeeklyMovieListItemMapper<WeeklyMovieListModel>>?) {
    HEADER(WeeklyMovieListHeaderMapper::class.java),
    CONTENTS(WeeklyMovieListContentsMapper::class.java),
    FOOTER(WeeklyMovieListFooterMapper::class.java)
}