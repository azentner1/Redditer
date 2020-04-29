package com.demo.redditer.data.api

import com.demo.redditer.BuildConfig
import com.demo.redditer.data.response.FrontPageResponse
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import java.util.concurrent.TimeUnit


interface ApiService {

    @GET(".json")
    fun fetchFrontPage() : Call<FrontPageResponse>

    companion object {
        operator fun invoke(): ApiService {
            val interceptor = Interceptor {
                val request = it.request()
                        .newBuilder()
                        .build()
                return@Interceptor it.proceed(request)
            }

            val httpLoggingInterceptor = HttpLoggingInterceptor()

            if (BuildConfig.DEBUG) {
                httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
            } else {
                httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.NONE
            }

            val httpClient = OkHttpClient.Builder()
                    .addInterceptor(interceptor)
                    .addInterceptor(httpLoggingInterceptor)
                    .connectTimeout(60, TimeUnit.SECONDS)
                    .writeTimeout(60, TimeUnit.SECONDS)
                    .readTimeout(60, TimeUnit.SECONDS)
                    .build()

            return Retrofit.Builder()
                    .client(httpClient).baseUrl(BuildConfig.API)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                    .create(ApiService::class.java)
        }
    }
}