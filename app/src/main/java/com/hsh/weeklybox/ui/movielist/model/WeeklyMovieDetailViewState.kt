package com.hsh.weeklybox.ui.movielist.model

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import com.hsh.weeklybox.BR

class WeeklyMovieDetailViewState : BaseObservable() {

    @get:Bindable
    var hasData = false
        set(value) {
            field = value
            notifyPropertyChanged(BR.hasData)
        }

    @get:Bindable
    var textFailOverView = ""
        set(value) {
            field = value
            notifyPropertyChanged(BR.textFailOverView)
        }

    @get:Bindable
    var textMovieTitleKor = ""
        set(value) {
            field = value
            notifyPropertyChanged(BR.textMovieTitleKor)
        }

    @get:Bindable
    var textMovieTitleEng = ""
        set(value) {
            field = value
            notifyPropertyChanged(BR.textMovieTitleEng)
        }

    @get:Bindable
    var textMovieOpenDt = ""
        set(value) {
            field = value
            notifyPropertyChanged(BR.textMovieOpenDt)
        }

    @get:Bindable
    var textMovieOpenStat = ""
        set(value) {
            field = value
            notifyPropertyChanged(BR.textMovieOpenStat)
        }

    @get:Bindable
    var textMovieType = ""
        set(value) {
            field = value
            notifyPropertyChanged(BR.textMovieType)
        }

    @get:Bindable
    var textMovieCode = ""
        set(value) {
            field = value
            notifyPropertyChanged(BR.textMovieCode)
        }
}