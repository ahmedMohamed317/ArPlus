package com.example.ar_task.data.datasource.remote

import com.example.ar_task.util.response.Response
import kotlinx.coroutines.flow.Flow

interface RemoteDataSource {

    suspend fun <T> getArticlesBySearch(query : String) : Flow<Response<T>>



}