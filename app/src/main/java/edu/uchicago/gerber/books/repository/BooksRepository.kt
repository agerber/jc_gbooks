package edu.uchicago.gerber.favs.data.repository


import edu.uchicago.gerber.books.models.VolumesResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response
import javax.inject.Inject

//simple books repo
class BooksRepository @Inject constructor(private val api: BooksApi) {
    suspend fun getBooks(
        query: String,
        maxResults: Int,
        startIndex: Int,
    ): Response<VolumesResponse> {
        return withContext(Dispatchers.IO) {
            api.getBooks(
                query = query,
                maxResults = maxResults,
                startIndex = startIndex,
            )
        }
    }
}



