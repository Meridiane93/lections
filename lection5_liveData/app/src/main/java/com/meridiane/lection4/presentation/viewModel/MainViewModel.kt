package com.meridiane.lection4.presentation.viewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.meridiane.lection4.data.getBookData.BookAndAvailability
import com.meridiane.lection4.data.getBookData.BookListUiState
import com.meridiane.lection4.domain.MockRepository
import kotlinx.coroutines.*

class MainViewModel : ViewModel() {

    val liveData = MutableLiveData<MutableList<BookListUiState>>()
    val liveDataError = MutableLiveData<String>()

    private val repository = MockRepository()

    fun start() {
        viewModelScope.launch {
            try {

                fun getBooksAsync() = viewModelScope.async {
                    repository.getBooks()
                }

                fun getAuthorsAsync() = viewModelScope.async {
                    repository.getAuthors()
                }

                fun getAvailabilityAsync() = viewModelScope.async {
                    repository.getAvailability()
                }

                val listBooks =  getBooksAsync().await().apply {
                    if (this.isFailure){
                        liveDataError.value = "getBooks"
                        throw Exception("getBooks")
                    }
                }.getOrNull()

                val listAuthors = getAuthorsAsync().await().apply {
                    if (this.isFailure){
                        liveDataError.value = "getAuthors"
                        throw Exception("getAuthors")
                    }
                }.getOrNull()

                val listAvailability = getAvailabilityAsync().await().apply {
                    if (this.isFailure){
                        liveDataError.value = "getAvailability"
                        throw Exception("getAvailability")
                    }
                }.getOrNull()

                    val listBookListUiState = mutableListOf<BookListUiState>()

                    val listBookAndAvailability = mutableListOf<BookAndAvailability>()
                    val listBookAndAvailability2 = mutableListOf<BookAndAvailability>()

                    var count = 1

                    if (listAuthors != null &&
                        listBooks != null &&
                        listAvailability != null
                    ) {

                        for (author in listAuthors) {

                            for (book in listBooks) {

                                if (book.authorId == author.authorId) {

                                    for (available in listAvailability) {

                                        if (available.bookId == book.bookId) {
                                            if (count == 1) {

                                                listBookAndAvailability.add(
                                                    BookAndAvailability(
                                                        book.bookId,
                                                        book.title,
                                                        available.inStock
                                                    )
                                                )

                                            } else {

                                                listBookAndAvailability2.add(
                                                    BookAndAvailability(
                                                        book.bookId,
                                                        book.title,
                                                        available.inStock
                                                    )
                                                )

                                            }
                                        }
                                    }
                                }
                            }

                            if (count == 1) {
                                listBookListUiState.add(
                                    BookListUiState(
                                        author.name,
                                        (listBookAndAvailability).sortedBy { it.title.lowercase()  }
                                    )
                                )
                            } else {

                                listBookListUiState.add(
                                    BookListUiState(
                                        author.name,
                                        (listBookAndAvailability2).sortedBy { it.title.lowercase() }
                                    )
                                )
                            }

                            count++
                        }
                    }
                    liveData.value = listBookListUiState

                }
            catch (e: Exception) {
               Log.d("MyLog", "${e.message}")
            }
        }

    }

    override fun onCleared() {
        viewModelScope.cancel()
    }
}