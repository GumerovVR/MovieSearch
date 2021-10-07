package com.example.moviesearch.di

import android.content.Context
import com.example.moviesearch.common.Const
import com.example.moviesearch.data.local.db.AppDatabase
import com.example.moviesearch.data.local.db.FavouriteMovieDao
import com.example.moviesearch.data.network.api.MovieApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideMovieApi() : MovieApiService {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(Const.BASE_URL)
            .build()
            .create(MovieApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideDB(@ApplicationContext context: Context) : FavouriteMovieDao {
        return AppDatabase.invoke(context).getFavouriteMovieDao()
    }
}