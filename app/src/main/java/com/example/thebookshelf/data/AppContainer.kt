package com.example.thebookshelf.data

import com.example.thebookshelf.network.RetrofitInstance

interface AppContainer {
    val booksRepository: BooksRepository
}

class DefaultAppContainer : AppContainer {
    override val booksRepository: BooksRepository by lazy {
        NetworkBooksRepository(RetrofitInstance.retrofitService)
    }
}