package com.example.thebookshelf.data

import com.example.thebookshelf.network.BooksApiService

class NetworkBooksRepository(
    private val booksApiService: BooksApiService
) : BooksRepository {

    override suspend fun getBooks(query: String): List<Book> {
        return try {
            val response = booksApiService.getBooks(query)
            response.items ?: emptyList()
        } catch (e: Exception) {
            throw e
        }
    }
}