package com.example.ar_task.presentation.search

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ar_task.di.Dispatcher
import com.example.ar_task.di.DispatcherAnnotations
import com.example.ar_task.domain.model.articles.ArticlesResponseModel
import com.example.ar_task.domain.model.articles.ArticlesModel
import com.example.ar_task.domain.usecases.GetArticlesBySearch
import com.example.ar_task.util.response.Response
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    @Dispatcher(DispatcherAnnotations.IO) private val ioDispatcher: CoroutineDispatcher,
    private val getArticlesBySearch: GetArticlesBySearch,
) : ViewModel() {


    private val _state: MutableStateFlow<ArticlesResponseModel> = MutableStateFlow(
        ArticlesResponseModel(
        articles = emptyList<ArticlesModel>()
    )
    )
    val state = _state.asStateFlow()

    private val _error: MutableSharedFlow<String> = MutableSharedFlow()
    val error = _error.asSharedFlow()


    fun getArticlesBySearch(query: String) {
        viewModelScope.launch(ioDispatcher) {
            getArticlesBySearch.execute<ArticlesResponseModel>(query).collectLatest { response ->
                when (response) {
                    is Response.Failure -> {

                    }
                    is Response.Success -> {
                        _state.update { articleModel ->

                            articleModel.copy(
                                articles = response.data?.articles?: emptyList(),
                                status = response.data?.status?:"",
                                totalResults = response.data?.totalResults?:0)

                        }
                    }
                }

            }

        }
    }


}