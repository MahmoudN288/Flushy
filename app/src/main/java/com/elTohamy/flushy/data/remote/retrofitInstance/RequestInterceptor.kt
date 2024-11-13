package com.elTohamy.flushy.data.remote.retrofitInstance

import com.elTohamy.flushy.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response

class RequestRapidInterceptor: Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
            .newBuilder()
            .addHeader("x-rapidapi-key", BuildConfig.API_KEY)
            .build()
        return chain.proceed(request)
    }
}

class RequestNewsInterceptor: Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
            .newBuilder()
            .addHeader("apikey", BuildConfig.NEWS_API_KEY)
            .build()
        return chain.proceed(request)
    }
}