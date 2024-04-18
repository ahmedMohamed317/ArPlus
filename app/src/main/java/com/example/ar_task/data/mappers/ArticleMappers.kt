package com.example.ar_task.data.mappers

import com.example.ar_task.data.dto.articles.SourceDto
import com.example.ar_task.data.dto.articles.ArticleDto
import com.example.ar_task.domain.model.articles.ArticlesModel
import com.example.ar_task.data.dto.articles.ArticleResponseDto
import com.example.ar_task.domain.model.articles.ArticlesResponseModel
import com.example.ar_task.domain.model.articles.SourceModel

fun ArticleResponseDto.toArticleResponseModel(): ArticlesResponseModel {
    return ArticlesResponseModel(
        articles = articles.map { it.toArticlesModel() },
        status = status,
        totalResults = totalResults,
    )
}
fun ArticleDto.toArticlesModel(): ArticlesModel {
    return ArticlesModel(source = source.toSourceModel(), title = title ?: "", description = description, author = author, url = url, urlToImage = urlToImage, publishedAt = publishedAt, content = content)
}
fun SourceDto.toSourceModel(): SourceModel {
    return SourceModel(id=id , name =name)
}



