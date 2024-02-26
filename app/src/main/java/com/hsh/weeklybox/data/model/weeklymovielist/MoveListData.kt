package com.hsh.weeklybox.data.model.weeklymovielist

import com.tickaroo.tikxml.annotation.Element
import com.tickaroo.tikxml.annotation.PropertyElement
import com.tickaroo.tikxml.annotation.Xml

@Xml(name = "boxOfficeResult")
data class MoveListData(
    @PropertyElement(name = "boxofficeType") val boxofficeType: String?,
    @PropertyElement(name = "showRange") val showRange: String?,
    @PropertyElement(name = "yearWeekTime") val yearWeekTime: String?,
    @Element(name = "weeklyBoxOfficeList") val weeklyBoxOfficeList: WeeklyBoxOfficeList?
)

@Xml(name = "weeklyBoxOfficeList")
data class WeeklyBoxOfficeList(
    @Element(name = "weeklyBoxOffice") val weeklyBoxOffice: List<WeeklyBoxOffice>?
)

@Xml(name = "weeklyBoxOffice")
data class WeeklyBoxOffice(
    @PropertyElement(name = "rnum") val rnum: String?,
    @PropertyElement(name = "rank") val rank: String?,
    @PropertyElement(name = "rankInten") val rankInten: String?,
    @PropertyElement(name = "rankOldAndNew") val rankOldAndNew: String?,
    @PropertyElement(name = "movieCd") val movieCd: String?,
    @PropertyElement(name = "movieNm") val movieNm: String?,
    @PropertyElement(name = "openDt") val openDt: String?,
    @PropertyElement(name = "salesAmt") val salesAmt: Long?,
    @PropertyElement(name = "salesShare") val salesShare: Double?,
    @PropertyElement(name = "salesInten") val salesInten: Long?,
    @PropertyElement(name = "salesChange") val salesChange: Double?,
    @PropertyElement(name = "salesAcc") val salesAcc: Long?,
    @PropertyElement(name = "audiCnt") val audiCnt: Int?,
    @PropertyElement(name = "audiInten") val audiInten: Int?,
    @PropertyElement(name = "audiChange") val audiChange: Double?,
    @PropertyElement(name = "audiAcc") val audiAcc: Int?,
    @PropertyElement(name = "scrnCnt") val scrnCnt: Int?,
    @PropertyElement(name = "showCnt") val showCnt: Int?
)
