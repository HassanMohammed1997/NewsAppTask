package com.task.newsapptask.network

import com.task.newsapptask.app.API_KEY
import com.task.newsapptask.models.NewsResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitService {
    @GET("everything")
    suspend fun getAllArticle(
        @Query("q") query: String,
        @Query("apiKey") apiKey: String = API_KEY,
        @Query("pageSize") pageSize: Int = 20,
        @Query("page") page: Int = 1
    ): NewsResponse
}