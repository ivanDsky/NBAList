package ua.zloydi.core.network

import okhttp3.Interceptor
import okhttp3.Response

class HeadersInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val requestWithHeader = chain.request().newBuilder()
            .addHeader("X-RapidAPI-Key", "a63e9df914mshdb760e1440bde6ep11786ejsn7d0ebfc72df6")
            .addHeader("X-RapidAPI-Host", "nba-stats4.p.rapidapi.com")
            .build()

        return chain.proceed(requestWithHeader)
    }
}
