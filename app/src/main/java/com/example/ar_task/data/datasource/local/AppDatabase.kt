package com.example.ar_task.data.datasource.local

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import android.content.Context
import com.example.ar_task.domain.model.articles.ArticlesModel


@Database(entities = [ArticlesModel::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract val articleDao : ArticleDao

    companion object {
        @Volatile
        private var INSTANCE: ArticleDao? = null

        fun getDaoInstance(context: Context): ArticleDao {
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: buildDatabase(context).articleDao
            }
        }

        private fun buildDatabase(context: Context): AppDatabase {
            return Room.databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java,
                "app_database"
            ).fallbackToDestructiveMigration().build()
        }
    }
}
