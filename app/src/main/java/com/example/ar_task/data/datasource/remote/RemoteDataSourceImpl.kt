package com.example.ar_task.data.datasource.remote

import android.util.Log
import com.example.ar_task.util.response.Response
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

@Suppress("UNCHECKED_CAST")
class RemoteDataSourceImpl @Inject constructor(private val retrofitInterface: RetrofitInterface) :
    RemoteDataSource {

    override suspend fun <T> getArticlesBySearch(query: String): Flow<Response<T>> {
        return flow {
            try {
                Log.d("getArticlesBySearch","in try")
                emit(
                    Response.Success(
                        retrofitInterface.getArticlesBySearch(query) as T
                    )
                )
            } catch (e: Exception) {
                emit(Response.Failure(e.message ?: "unknown error"))
            }
        }
    }

}