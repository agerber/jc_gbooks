package edu.uchicago.gerber.favs.presentation.screens.search.paging

import android.app.Application
import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import edu.uchicago.gerber.books.common.Constants
import edu.uchicago.gerber.books.models.Item
import edu.uchicago.gerber.favs.models.Paginate
import edu.uchicago.gerber.favs.data.repository.BooksRepository

import javax.inject.Inject

class BusinessSource @Inject constructor(
    private val booksRepository: BooksRepository,
    private val paginateData: Paginate,
    private val application: Application
) :
    PagingSource<Int, Item>() {
    override fun getRefreshKey(state: PagingState<Int, Item>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Item> {
        return try {
            val prev = params.key ?: 0

            val response = booksRepository.getBooks(
                startIndex = prev,
                maxResults = params.loadSize,
                query = paginateData.query,
            )

            if (response.isSuccessful) {
                val body = response.body()?.items
                LoadResult.Page(
                    data = body!!,
                    prevKey = if (prev == 0) null else prev - 1,
                    nextKey = if (body.size < params.loadSize) null else prev + 10
                )
            } else {
                 LoadResult.Error(Exception())
            }

        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override val keyReuseSupported: Boolean
        get() = true

}