package com.hsh.weeklybox.data.model.weeklymovielist

import com.tickaroo.tikxml.annotation.Element
import com.tickaroo.tikxml.annotation.PropertyElement
import com.tickaroo.tikxml.annotation.Xml

@Xml(name = "movieInfoResult")
data class MovieDetailData(
    @Element val movie: MovieInfo,
    @PropertyElement val source: String
)

@Xml(name = "movieInfo")
data class MovieInfo(
    @PropertyElement(name = "movieCd") val movieCd: String?,
    @PropertyElement(name = "movieNm") val movieNm: String?,
    @PropertyElement(name = "movieNmEn") val movieNmEn: String?,
    @PropertyElement(name = "movieNmOg") val movieNmOg: String?,
    @PropertyElement(name = "prdtYear") val prdtYear: String?,
    @PropertyElement(name = "showTm") val showTm: String?,
    @PropertyElement(name = "openDt") val openDt: String?,
    @PropertyElement(name = "prdtStatNm") val prdtStatNm: String?,
    @PropertyElement(name = "typeNm") val typeNm: String?,
    @Element(name = "nations") val nations: Nations?,
    @Element(name = "genres") val genres: Genres?,
    @Element(name = "directors") val directors: Directors?,
    @Element(name = "actors") val actors: Actors?,
    @Element(name = "showTypes") val showTypes: ShowTypes?,
    @Element(name = "audits") val audits: Audits?,
    @Element(name = "companys") val companys: Companys?,
    @Element(name = "staffs") val staffs: Staffs?
)

@Xml(name = "nations")
data class Nations(
    @Element(name = "nation") val nationList: List<Nation>?
)

@Xml(name = "nation")
data class Nation(
    @PropertyElement(name = "nationNm") val nationNm: String
)

@Xml(name = "genres")
data class Genres(
    @Element(name = "genre") val genreList: List<Genre>?
)

@Xml(name = "genre")
data class Genre(
    @PropertyElement(name = "genreNm") val genreNm: String
)

@Xml(name = "directors")
data class Directors(
    @Element(name = "director") val directorList: List<Director>?
)

@Xml(name = "director")
data class Director(
    @PropertyElement(name = "peopleNm") val peopleNm: String,
    @PropertyElement(name = "peopleNmEn") val peopleNmEn: String
)

@Xml(name = "actors")
data class Actors(
    @Element(name = "actor") val actorList: List<Actor>?
)

@Xml(name = "actor")
data class Actor(
    @PropertyElement(name = "peopleNm") val peopleNm: String?,
    @PropertyElement(name = "peopleNmEn") val peopleNmEn: String?,
    @PropertyElement(name = "cast") val cast: String?,
    @PropertyElement(name = "castEn") val castEn: String?
)

@Xml(name = "showTypes")
data class ShowTypes(
    @Element(name = "showType") val showTypeList: List<ShowType>?
)

@Xml(name = "showType")
data class ShowType(
    @PropertyElement(name = "showTypeGroupNm") val showTypeGroupNm: String?,
    @PropertyElement(name = "showTypeNm") val showTypeNm: String?
)

@Xml(name = "audits")
data class Audits(
    @Element(name = "audit") val auditList: List<Audit>?
)

@Xml(name = "audit")
data class Audit(
    @PropertyElement(name = "auditNo") val auditNo: String?,
    @PropertyElement(name = "watchGradeNm") val watchGradeNm: String?
)

@Xml(name = "companys")
data class Companys(
    @Element(name = "company") val companyList: List<Company>?
)

@Xml(name = "company")
data class Company(
    @PropertyElement(name = "companyCd") val companyCd: String?,
    @PropertyElement(name = "companyNm") val companyNm: String?,
    @PropertyElement(name = "companyNmEn") val companyNmEn: String?,
    @PropertyElement(name = "companyPartNm") val companyPartNm: String?
)

@Xml(name = "staffs")
data class Staffs(
    @Element(name = "staff") val staffList: List<Staff>?
)

@Xml(name = "staff")
data class Staff(
    @PropertyElement(name = "peopleNm") val peopleNm: String?,
    @PropertyElement(name = "peopleNmEn") val peopleNmEn: String?,
    @PropertyElement(name = "staffRoleNm") val staffRoleNm: String?
)
