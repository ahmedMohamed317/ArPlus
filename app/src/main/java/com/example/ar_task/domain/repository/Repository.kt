package com.example.ar_task.domain.repository

import com.example.ar_task.util.response.Response
import kotlinx.coroutines.flow.Flow

interface Repository {

    suspend fun <T> getArticlesBySearch(id : String) : Flow<Response<T>>


}