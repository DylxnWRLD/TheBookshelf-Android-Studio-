package com.example.thebookshelf.network

import com.example.thebookshelf.data.Book
import com.example.thebookshelf.data.BookResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface BooksApiService {
    @GET("volumes")
    suspend fun getBooks(
        @Query("q") query: String,
        @Query("key") apiKey: String = "AIzaSyCPaC7MqGIIk-LW4OK8eB7uS_w_ySiExfI"
    ): BookResponse

    @GET("volumes/{id}")
    suspend fun getBook(
        @Path("id") bookId: String,
        @Query("key") apiKey: String = "AIzaSyCPaC7MqGIIk-LW4OK8eB7uS_w_ySiExfI"
    ): Book
}