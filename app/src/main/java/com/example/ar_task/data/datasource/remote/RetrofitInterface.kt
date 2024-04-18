package com.example.ar_task.data.datasource.remote

import com.example.ar_task.data.dto.articles.ArticleResponseDto
import retrofit2.http.GET
import retrofit2.http.Query


interface RetrofitInterface {
    @GET("everything")
    suspend fun getArticlesBySearch(
        @Query("q") query: String,
        @Query("apiKey") apiKey: String = "f156159b23e34d519097ebc2b6a13853"
    ): ArticleResponseDto

}