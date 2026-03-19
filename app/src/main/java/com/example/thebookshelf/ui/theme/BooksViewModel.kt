package com.example.thebookshelf.ui.theme

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.thebookshelf.data.Book
import com.example.thebookshelf.data.BooksRepository
import com.example.thebookshelf.BookshelfApplication

import kotlinx.coroutines.launch
import java.io.IOException

sealed interface BooksUiState {
    data class Success(val books: List<Book>) : BooksUiState
    object Error : BooksUiState
    object Loading : BooksUiState
}

class BooksViewModel(private val booksRepository: BooksRepository) : ViewModel() {

    var booksUiState: BooksUiState by mutableStateOf(BooksUiState.Loading)
        private set


    private var lastQuery: String? = null

    fun getBooks(query: String) {
        if (query == lastQuery) return
        lastQuery = query

        viewModelScope.launch {
            booksUiState = BooksUiState.Loading
            try {
                val books = booksRepository.getBooks(query)
                booksUiState = if (books.isEmpty()) {
                    BooksUiState.Error
                } else {
                    BooksUiState.Success(books)
                }
            } catch (e: Exception) {
                booksUiState = BooksUiState.Error
            }
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as BookshelfApplication)
                val booksRepository = application.container.booksRepository
                BooksViewModel(booksRepository = booksRepository)
            }
        }
    }
}