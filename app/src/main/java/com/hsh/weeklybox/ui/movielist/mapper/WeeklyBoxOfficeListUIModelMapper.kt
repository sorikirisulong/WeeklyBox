package com.hsh.weeklybox.ui.movielist.mapper

import com.hsh.weeklybox.data.model.weeklymovielist.WeeklyBoxOffice
import com.hsh.weeklybox.ui.common.handler.ActionHandle
import com.hsh.weeklybox.ui.movielist.model.WeeklyBoxOfficeUIModel

class WeeklyBoxOfficeListUIModelMapper  {
    fun mapToItemModel(weeklyBoxOfficeList: List<WeeklyBoxOffice>?, eventHandler: ActionHandle): List<WeeklyBoxOfficeUIModel> {
        if (weeklyBoxOfficeList == null) return emptyList()

        return weeklyBoxOfficeList.map { weeklyBoxOffice ->
            WeeklyBoxOfficeUiModelMapper().mapToItemModel(weeklyBoxOffice, eventHandler)
        }
    }
}