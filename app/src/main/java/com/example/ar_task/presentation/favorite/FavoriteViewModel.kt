package com.example.ar_task.presentation.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ar_task.TaskApp
import com.example.ar_task.data.datasource.local.AppDatabase
import com.example.ar_task.domain.model.articles.ArticlesModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor() : ViewModel() {

    private val dao = AppDatabase.getDaoInstance(TaskApp.getAppContext())
    val allArticles = dao.getAllArticles()

    fun insert(article: ArticlesModel) = viewModelScope.launch {
        dao.insertArticle(article)
    }

    fun delete(article: ArticlesModel) = viewModelScope.launch {
        dao.deleteArticle(article)
    }
    fun isArticleFavorite(articleTitle: String): Flow<Boolean> {
        return dao.isArticleFavorite(articleTitle)
    }
}