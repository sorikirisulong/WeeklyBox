package com.hsh.weeklybox.network.provider



object EndpointProvider : IEndpointProvider {
    //base
    val host = "www.kobis.or.kr/kobisopenapi/webservice/rest"
    override val BASE_URL = "https://${host}/"
}