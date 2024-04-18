package com.example.ar_task.domain.usecases

import com.example.ar_task.domain.repository.Repository
import com.example.ar_task.util.response.Response
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetArticlesBySearch @Inject constructor(private val repository: Repository) {

    suspend fun <T> execute(userId : String) : Flow<Response<T>>
    {
        return repository.getArticlesBySearch(userId)
    }
}