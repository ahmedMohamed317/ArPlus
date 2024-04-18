package com.example.ar_task.domain.model.articles

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "articles")

data class ArticlesModel(
    @PrimaryKey(autoGenerate = true)
    val itemId: Int = 0,
    @Embedded
    val source: SourceModel,
    val author: String?,
    val title: String,
    val description: String?,
    val url: String,
    val urlToImage: String?,
    val publishedAt: String,
    val content: String?,
    var isFavorite: Boolean = false,

    )