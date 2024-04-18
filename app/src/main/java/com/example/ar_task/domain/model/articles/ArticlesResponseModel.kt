package com.example.ar_task.domain.model.articles

data class ArticlesResponseModel(
    val status: String = "",
    val totalResults: Int = 0,
    val articles: List<ArticlesModel>
)