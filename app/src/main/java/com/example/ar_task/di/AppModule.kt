package com.example.ar_task.di
import android.content.Context
import com.example.ar_task.data.datasource.remote.RetrofitInterface
import com.example.ar_task.util.connectivity.ConnectivityObserver
import com.example.ar_task.util.connectivity.NetworkConnectivityObserver
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton
import com.example.ar_task.BuildConfig



@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideConnectivityObserver(@ApplicationContext context: Context): ConnectivityObserver =
        NetworkConnectivityObserver(context = context)

    @Singleton
    @Provides
    fun providesRetrofitApi(): RetrofitInterface
            = Retrofit.Builder()
        .baseUrl("https://newsapi.org/v2/").addConverterFactory(
            GsonConverterFactory.create()).build()
        .create(RetrofitInterface::class.java)

}