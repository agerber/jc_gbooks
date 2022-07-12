package edu.uchicago.gerber.favs.data.repository

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import edu.uchicago.gerber.books.common.Constants
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class Hilt4GoogleApi {

    @Provides
    @Singleton
    fun provideBooksApi(): BooksApi {
        return Retrofit.Builder()
            .baseUrl(Constants.googleApi)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(BooksApi::class.java)
    }

}