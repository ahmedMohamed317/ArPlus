package com.example.ar_task.data.datasource.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.ar_task.domain.model.articles.ArticlesModel
import kotlinx.coroutines.flow.Flow

@Dao
interface ArticleDao {

    @Query("SELECT * FROM articles")
    fun getAllArticles(): Flow<List<ArticlesModel>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertArticle(article: ArticlesModel)

    @Delete
    suspend fun deleteArticle(article: ArticlesModel)

    @Query("SELECT EXISTS (SELECT 1 FROM articles WHERE title = :articleTitle)")
    fun isArticleFavorite(articleTitle: String): Flow<Boolean>
}