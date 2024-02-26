package com.hsh.weeklybox.ui.movielist

import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hsh.weeklybox.data.model.weeklymovielist.WeeklyBoxOfficeList
import com.hsh.weeklybox.domain.usecase.WeeklyMovieListUseCase
import com.hsh.weeklybox.framework.event.SingleLiveEvent
import com.hsh.weeklybox.ui.common.handler.ActionEvent
import com.hsh.weeklybox.ui.common.handler.ActionHandle
import com.hsh.weeklybox.ui.movielist.event.WeeklyMovieListClickEvent
import com.hsh.weeklybox.ui.movielist.item.WeeklyMovieListItemViewType
import com.hsh.weeklybox.ui.movielist.mapper.WeeklyBoxOfficeListUIModelMapper
import com.hsh.weeklybox.ui.movielist.model.WeeklyMovieListEvent
import com.hsh.weeklybox.ui.movielist.model.WeeklyMovieListViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.drop
import kotlinx.coroutines.flow.scan
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeeklyMovieListViewModel @Inject constructor(
    private val weeklyMovieListUseCase: WeeklyMovieListUseCase
): ViewModel() {
    private val _event = SingleLiveEvent<WeeklyMovieListEvent>()
    val event: LiveData<WeeklyMovieListEvent>
        get() = _event

    val viewState = WeeklyMovieListViewState()

    private val _progress = SingleLiveEvent<Boolean>()
    val progress: LiveData<Boolean>
        get() = _progress

    private val _clickEvent = SingleLiveEvent<ActionEvent>()
    val clickEvent: LiveData<ActionEvent>
        get() = _clickEvent

    private val items = mutableListOf<WeeklyMovieListModel>()

    private val backPressEvent = MutableSharedFlow<Unit>(
        replay = 0,
        extraBufferCapacity = 1,
        onBufferOverflow = BufferOverflow.DROP_OLDEST
    )

    fun onBackButtonClick() {
        backPressEvent.tryEmit(Unit)
    }

    fun observeBackPressEvent() {
        viewModelScope.launch {
            backPressEvent
                .scan(listOf(System.currentTimeMillis() - BACK_BUTTON_INTERVAL)) { acc, _ ->
                    acc.takeLast(1) + System.currentTimeMillis()
                }
                .drop(1)
                .collectLatest {
                    if (it.last() - it.first() < BACK_BUTTON_INTERVAL) {
                        _event.value = WeeklyMovieListEvent.CloseWeeklyMovieListScreen
                    } else {
                        _event.value = WeeklyMovieListEvent.ShowExitWarningToast
                    }
                }
        }
    }

    private val eventHandler = object : ActionHandle {
        override fun handleClick(event: ActionEvent) {
            when (event) {
                is WeeklyMovieListClickEvent.GoToDetailWeeklyMovieInfo -> {
                    _clickEvent.value = event
                    return
                }
            }
        }
    }

    init {
        initViewState()
    }

    private fun initViewState() {
        viewState.textFailOverView = SEARCH_MESSAGE
        viewState.hasData = false
    }

    fun getWeeklyMovieList(key: String, targetDt: String) {
        _progress.value = true
        viewModelScope.launch {
            try {
                val response = weeklyMovieListUseCase.getWeeklyMovieList(key, targetDt)
                if (response?.isSuccessful == true) {
                    val responseBody = response.body()
                    setDetailItemList(
                        responseBody?.boxofficeType,
                        responseBody?.showRange,
                        responseBody?.yearWeekTime,
                        responseBody?.weeklyBoxOfficeList
                    )
                    viewState.hasData =
                        responseBody?.weeklyBoxOfficeList?.weeklyBoxOffice?.isNotEmpty() == true
                } else {
                    viewState.textFailOverView = NO_RESULT_MESSAGE
                }
            } catch (e: Exception) {
                viewState.hasData = false
                viewState.textFailOverView = NO_RESULT_MESSAGE
            }
            _progress.value = false
        }
    }

    fun onSearchButtonClick() {
        _event.value = WeeklyMovieListEvent.SearchButtonClick
    }

    private fun setDetailItemList(
        boxofficeType: String?,
        showRange: String?,
        yearWeekTime: String?,
        weeklyBoxOfficeList: WeeklyBoxOfficeList?
    ) {
        makeDetailItems(boxofficeType, showRange, yearWeekTime, weeklyBoxOfficeList)
        _event.value = WeeklyMovieListEvent.Items(items)
    }

    private fun makeDetailItems(
        boxofficeType: String?,
        showRange: String?,
        yearWeekTime: String?,
        weeklyBoxOfficeList: WeeklyBoxOfficeList?
    ) {
        items.clear()
        WeeklyMovieListItemViewType.values().forEach { viewType ->
            (viewType.mapper?.getConstructor()?.newInstance())?.mapToItemModel(
                boxofficeType = boxofficeType,
                showRange = showRange,
                yearWeekTime = yearWeekTime,
                weeklyBoxOfficeUIModelList = WeeklyBoxOfficeListUIModelMapper().mapToItemModel(
                    weeklyBoxOfficeList = weeklyBoxOfficeList?.weeklyBoxOffice,
                    eventHandler
                )
            )?.let {
                items.add(it)
            }
        }
    }

    companion object {
        private const val BACK_BUTTON_INTERVAL = 2000
        private const val SEARCH_MESSAGE = "ex) 20120101 6자리 날짜형식에 맞게 주간영화정보를 조회해보세요."
        private const val NO_RESULT_MESSAGE = "조회된 데이터가 없습니다. 올바른 날짜형식으로 다시 조회해주세요."
    }
}