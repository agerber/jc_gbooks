package edu.uchicago.gerber.favs.data.repository

import edu.uchicago.gerber.books.models.VolumesResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import javax.inject.Singleton


@Singleton
interface BooksApi {

    @GET(value = "books/v1/volumes")
    suspend fun getBooks(
        @Query("q") query: String,
        @Query("maxResults") maxResults: Int,
        @Query("startIndex") startIndex: Int,
    ): Response<VolumesResponse>
}