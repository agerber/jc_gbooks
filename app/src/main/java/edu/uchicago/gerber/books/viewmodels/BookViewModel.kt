package edu.uchicago.gerber.books.viewmodels

import android.app.Application
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import edu.uchicago.gerber.books.common.Constants
import edu.uchicago.gerber.books.models.Item
import edu.uchicago.gerber.favs.models.Paginate
import edu.uchicago.gerber.favs.data.repository.BooksRepository
import edu.uchicago.gerber.favs.presentation.screens.search.paging.BookSource
import edu.uchicago.gerber.favs.presentation.search.SearchOperation

import edu.uchicago.gerber.favs.presentation.search.SearchState
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BookViewModel @Inject constructor(
    private val booksRepository: BooksRepository,
    private val application: Application
) :
    ViewModel() {

    //////////////////////////////////////////
    // MUTABLE-STATES AND OBSERVABLE STATES
    //////////////////////////////////////////
    private var _queryText = mutableStateOf<String>("")
    val queryText: State<String> = _queryText


    private var _book = mutableStateOf<Item>(Constants.fakeBook)
    val book: State<Item> = _book

    private val _searchState = mutableStateOf(SearchState())
    val searchState: State<SearchState> = _searchState

    //////////////////////////////////////////
    // FUNCTIONS
    //////////////////////////////////////////
    fun setBusiness(business: Item) {
        _book.value = business
    }

    fun setQueryText(query: String) {
        _queryText.value = query
    }



    fun onSearch() {
        _searchState.value = SearchState(searchOperation = SearchOperation.LOADING)
        viewModelScope.launch {
            _searchState.value = SearchState(
                data = Pager(
                    config = PagingConfig(pageSize = 10, prefetchDistance = 2),
                    pagingSourceFactory = {
                        BookSource(
                            booksRepository = booksRepository,
                            paginateData = Paginate(
                                query = _queryText.value,
                            ),
                            application
                        )
                    }
                ).flow.cachedIn(viewModelScope),
                searchOperation = SearchOperation.DONE
            )
        }
    }


}


