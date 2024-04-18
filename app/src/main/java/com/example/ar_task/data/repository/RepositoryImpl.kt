package com.example.ar_task.data.repository

import com.example.ar_task.data.datasource.remote.RemoteDataSource

import com.example.ar_task.data.dto.articles.ArticleResponseDto
import com.example.ar_task.data.mappers.toArticleResponseModel
import com.example.ar_task.domain.repository.Repository
import com.example.ar_task.util.response.Response
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@Suppress("UNCHECKED_CAST")
class RepositoryImpl @Inject constructor(private val remoteDataSource: RemoteDataSource) :
    Repository {
    override suspend fun <T> getArticlesBySearch(id: String): Flow<Response<T>> {
        return try {
            remoteDataSource.getArticlesBySearch<ArticleResponseDto>(id).map { response ->
                Response.Success(response.data?.toArticleResponseModel() as T)
            }
        } catch (e: Exception) {
            flowOf(Response.Failure(e.message ?: "unknown error"))
        }
    }

}