package com.example.thebookshelf.network

import com.example.thebookshelf.data.Book
import com.example.thebookshelf.data.BookResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface BooksApiService {
    @GET("volumes")
    suspend fun getBooks(@Query("q") query: String): BookResponse

    @GET("volumes/{id}")
    suspend fun getBook(@Path("id") bookId: String): Book
}