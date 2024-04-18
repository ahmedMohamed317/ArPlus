package com.example.ar_task.data.dto.articles

data class ArticleResponseDto(
    val status: String,
    val totalResults: Int,
    val articles: List<ArticleDto>
)