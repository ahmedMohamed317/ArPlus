package com.example.ar_task.di

import androidx.lifecycle.ViewModel
import com.example.ar_task.data.datasource.remote.RemoteDataSourceImpl
import com.example.ar_task.data.repository.RepositoryImpl
import com.example.ar_task.domain.repository.Repository
import com.example.ar_task.data.datasource.remote.RemoteDataSource
import com.example.ar_task.domain.usecases.GetArticlesBySearch
import com.example.ar_task.presentation.search.SearchViewModel
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped


@Module
@InstallIn(ViewModelComponent::class)
interface ViewModelModule {


    @Binds
    fun provideRemoteDataSource(remoteDataSourceImpl: RemoteDataSourceImpl): RemoteDataSource

    @ViewModelScoped
    @Binds
    fun provideSearchViewModel(searchViewModel: SearchViewModel): ViewModel


    @Binds
    fun provideRepository(repositoryImpl: RepositoryImpl): Repository

    companion object {

        @ViewModelScoped
        @Provides
        fun provideGetArticlesBySearch(repository: Repository): GetArticlesBySearch =
            GetArticlesBySearch(repository)


    }
}