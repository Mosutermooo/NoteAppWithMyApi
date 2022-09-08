package com.premium.noteappwithmyapi.network

import android.provider.SyncStateContract
import com.premium.noteappwithmyapi.utils.GlobalConstants.BASE_URL
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object ApiInstance {
    private val logging = HttpLoggingInterceptor()
    val token : String? = null

    private fun okHttpClient() : OkHttpClient {
        return OkHttpClient.Builder().addNetworkInterceptor(object : Interceptor{
            override fun intercept(chain: Interceptor.Chain): Response {
                val request: Request =
                    chain.request().newBuilder().addHeader("Authorization", "Bearer $token").build()
                return chain.proceed(request)
            }
        }).addInterceptor(logging).build()
    }

    private val buildRequest: Retrofit by lazy {
        Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).client(okHttpClient()).build()
    }

    val apiService: ApiService = buildRequest.create(ApiService::class.java)

}