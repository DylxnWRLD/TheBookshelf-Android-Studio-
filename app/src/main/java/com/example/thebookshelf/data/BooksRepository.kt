package com.example.thebookshelf.data

interface BooksRepository {
    suspend fun getBooks(query: String): List<Book>
}